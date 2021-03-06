from pulp import *
from allc.node import *
from collections import OrderedDict
import math

LpVariable.__truediv__ = LpVariable.__div__
 
def visit_helper(node, func, visitor=None):
    if not isinstance(node, Node):
        return node
    if visitor:
        return Node(node.op, *[func(arg, visitor) for arg in node.args])
    return Node(node.op, *[func(arg) for arg in node.args])

def remove_double_not(node):
    if isinstance(node, Node) and node.op == NOT \
        and isinstance(node.args[0], Node) and node.args[0].op == NOT:
            return remove_double(node.args[0].args[0])
    return visit_helper(node, remove_double_not)
        
def remove_not(node):
    if isinstance(node, Node) and node.op == NOT:
        arg = node.args[0]
        if not isinstance(arg, Node):
            return 1 - remove_not(arg)
        elif arg.op == AND:
            return Node(OR, *[remove_not(Node(NOT, carg)) for carg in arg.args])
        elif arg.op == OR:
            return Node(AND, *[remove_not(Node(NOT, carg)) for carg in arg.args])
        elif arg.op == NOT:
            return remove_not(arg.args[0])
        elif arg.op in (GE, GT):
            raise Exception('error')
        else:
            return 1 - remove_not(arg)
    return visit_helper(node, remove_not)

def remove_c_eq(node):
    if isinstance(node, Node) and node.op == EQ:
        left = remove_c_eq(node.args[0])
        right = remove_c_eq(node.args[1])
        return Node(AND, left <= right, left >= right)
    return visit_helper(node, remove_c_eq)

def remove_p_eq(node):
    if isinstance(node, Node):
        if node.op == AND:
            return visit_helper(node, remove_p_eq)
        elif node.op == EQ:
            return visit_helper(node, remove_c_eq)
    return remove_c_eq(node)

def remove_c_lt(node):
    if isinstance(node, Node) and node.op == LT:
        left = remove_c_lt(node.args[0])
        right = remove_c_lt(node.args[1])
        return Node(NOT, Node(LE, right, left))
    return visit_helper(node, remove_c_lt)

def remove_p_lt(node):
    if isinstance(node, Node):
        if node.op == AND:
            return visit_helper(node, remove_p_lt)
        elif node.op == LT:
            return visit_helper(node, remove_c_lt)
    return remove_c_lt(node)

def flat_and_or(node):
    if isinstance(node, Node) and node.op in (AND, OR, MIN, MAX):
        n_node = visit_helper(node, flat_and_or)
        n_args = []
        for arg in n_node.args:
            if isinstance(arg, Node) and arg.op == node.op:
                n_args.extend(arg.args)
            else:
                n_args.append(arg)
        return Node(node.op, *n_args)
    return visit_helper(node, clear_and_or)

def clear_and_or(node):
    if isinstance(node, Node) and node.op in (AND, OR):
        if node.op == AND:
            defa, sc_func, no_func = 1, isZero, isOne
        else:
            defa, sc_func, no_func = 0, isOne, isZero
        
        args = [clear_and_or(arg) for arg in node.args]
        nx_args = []
        for arg in args:
            if not no_func(arg):
                nx_args.append(arg)
            elif sc_func(arg):
                return 1 - defa
        if len(nx_args) == 0:
            return defa
        elif len(nx_args) == 1:
            return nx_args[0]
        else:
            return Node(node.op, *nx_args)
    return visit_helper(node, clear_and_or)

def to_hashable(node):
    if type(node) in (int, float, bool):
        return node
    elif isinstance(node, Node) and node.op in (AND, OR):
        return (node.op, frozenset(map(to_hashable, node.args)))
    elif isinstance(node, Node):
        return (node.op, tuple(map(to_hashable, node.args)))
    elif isinstance(node, LpVariable):
        return (VAR_TYPE, node.name, node.cat, node.getLb(), node.getUb())
    elif isinstance(node, LpAffineExpression):
        l = []
        for k,v in node.items():
            if v == 0:
                continue
            assert isinstance(k, LpVariable)
            l.append( (to_hashable(k), to_hashable(v)) )
        #print(node.constant.op)
        assert not isinstance(node.constant, Node)
        l.append(node.constant)
        #print(l)
        return (A_EXPR_TYPE, frozenset(l))
    else:
        raise Exception('operation is not found')

def get_info(node):
    if type(node) in (int, bool):
        return (node, node, LpInteger)
    elif type(node) == float:
        return (node, node, LpInteger if node.is_integer() else LpContinuous)
    elif isinstance(node, LpVariable):
        return (node.getLb(), node.getUb(), node.cat)
    elif isinstance(node, LpAffineExpression):
        if len(node) == 0:
            return get_info(node.constant)
        lb, ub = node.constant,node.constant
        tp = LpInteger
        for k,v in node.items():
            if type(v) == float and not v.is_integer():
                tp = LpContinuous
            tl,tu,tt = get_info(k)
            if tt == LpContinuous:
                tp = LpContinuous
            if v<0:tl,tu = tu,tl
            if lb is None or tl is None:lb = None
            else:lb += tl*v
            if ub is None or tu is None:ub = None
            else:ub += tu*v
        return (lb, ub, tp)
    else:
        raise Exception('error')

def is_constant(node):
    l,u,_ = get_info(node)
    return l is not None and u is not None and l==u

class TempGenerator:
    def __init__(self, name='TEMP'):
        self.i = -1
        self.name = name
        self.temps = []

    def generate(self, lowBound=None, upBound=None, cat=LpContinuous):
        self.i += 1
        v = LpVariable(self.name+str(self.i), lowBound, upBound, cat)
        self.temps.append(v)
        return v

    def getNumber(self):
        return self.i + 1

class LogicTranslator:
    def __init__(self, M=10000, eps=1e-5, temp=None):
        self.M = M
        self.eps = eps
        if temp:
            self.tg = TempGenerator(temp)
        else:
            self.tg = TempGenerator()
        self.exprs = []
        self.table = OrderedDict()
        self.cache = {}
        self.const_cache = set()
        self.top_const = []
        self.temp_const = []

    def __iter__(self):
        return self.exprs.__iter__()

    def getTable(self):
        return self.table
    
    def hasCache(self, node):
        return to_hashable(node) in self.cache
    
    def setCache(self, node, v):
        self.cache[to_hashable(node)] = v
    
    def getCache(self, node):
        return self.cache[to_hashable(node)]
    
    def hasConst(self, node):
        assert not isinstance(to_hashable(node), Node)
        return to_hashable(node) in self.const_cache
    
    def setConst(self, node):
        self.const_cache.add(to_hashable(node))
        
    def addConst(self, const, top=False):
        if isOne(const):
            return
        elif isZero(const):
            raise Exception('false constraint is found')
        else:
            self.exprs.append(const)

    def convert(self, node):
        if isinstance(node, Node):
            if node.op == NOT:
                raise Exception('error')
            elif node.op in (AND, OR):
                n_node = clear_and_or(Node(node.op,*[self.convert(arg) for arg in node.args]))
                if not isinstance(n_node, Node) or n_node.op != node.op:
                    return n_node
                #n_node = Node(node.op, *args)
                if self.hasCache(n_node):
                    return self.getCache(n_node)
                temp = self.tg.generate(cat=LpBinary)
                self.table[temp.name] = n_node#Node(node.op, *args)
                args = n_node.args
                n = len(args)
                if node.op == AND:
                    self.addConst(lpSum(args) >= n * temp)
                    self.addConst(lpSum(args) <= n - 1 + temp)
                else:
                    self.addConst(lpSum(args) >= temp)
                    self.addConst(lpSum(args) <= n * temp)
                self.setCache(n_node, temp)
                return temp
            elif node.op == NEG:
                return - self.convert(node.args[0])
            elif node.op == ADD:
                return self.convert(node.args[0]) + self.convert(node.args[1])
            elif node.op == MUL:
                return self.convert(node.args[0]) * self.convert(node.args[1])
            elif node.op == DIV:
                return self.convert(node.args[0]) / self.convert(node.args[1])
            elif node.op == EQ:
                raise Exception('error')
            elif node.op in (LE, GE):
                if node.op == GE:
                    raise Exception('GE is not available')
                left = self.convert(node.args[0])
                right = self.convert(node.args[1])
                if type(left) in (int, bool, float) and \
                    type(right) in (int, bool, float):
                        return left <= right if node.op == LE else left >= right
                n_node = Node(node.op, left, right)
                if self.hasCache(n_node):
                    return self.getCache(n_node)
                diff = left - right
                if node.op == LE:
                    diff = -diff
                if type(diff) in (int, float, bool):
                    return diff >= 0
                if is_constant(diff):
                    t,_,_ = get_info(diff)
                    return t >= 0
                
                lb, ub, tp = get_info(diff)
                
                if lb is not None and lb >= 0:
                    #print(node,' is true')
                    return 1
                elif ub is not None and ub < 0:
                    #print(node, ' is false')
                    return 0
                    
                maxi = self.M if ub is None else ub
                mini = -self.M if lb is None else lb
                eps = self.eps if tp == LpContinuous else 1
                
                temp = self.tg.generate(cat=LpBinary)
                self.addConst(diff >= (1 - temp) * mini)
                self.addConst(- diff >= eps - temp * (maxi + eps))
                self.table[temp.name] = Node(GE, diff, 0)
                self.setCache(n_node, temp)
                return temp
            elif node.op == COND:
                cond = self.convert(node.args[0])
                then = self.convert(node.args[1])
                elze = self.convert(node.args[2])
                if type(cond) in (int, bool, float):
                    return then if cond else elze
                n_node = Node(COND, cond, then, elze)
                if self.hasCache(n_node):
                    return self.getCache(n_node)
                lb, ub, tp = get_info(elze - then)
                t_lb, t_ub, t_tp = get_info(then)
                e_lb, e_ub, e_tp = get_info(elze)
                
                n_lb = None
                if t_lb is not None and e_lb is not None:
                    n_lb = min(t_lb, e_lb)
                
                n_ub = None
                if t_ub is not None and e_ub is not None:
                    n_ub = max(t_ub, e_ub)
                
                if LpContinuous in (t_tp, e_tp):
                    tp = LpContinuous
                temp = self.tg.generate(n_lb, n_ub, cat=tp)
                
                if lb is None: lb = -self.M
                if ub is None: ub = self.M
                
                self.addConst(lb*(1-cond) <= temp - then)
                self.addConst(temp - then <= ub*(1-cond))
                self.addConst(lb*cond <= elze - temp)
                self.addConst(elze - temp <= ub*cond)
                self.table[temp.name] = Node(COND, cond, then, elze)
                self.setCache(n_node, temp)
                return temp
            elif node.op == MIN:
                args = [self.convert(arg) for arg in node.args]
                if all([type(arg) in (int, bool, float) for arg in args]):
                    return min(args)
                n_node = Node(MIN, *args)
                if self.hasCache(n_node):
                    return getCache(n_node)
                lb, ub, tp = float('inf'), float('inf'), LpInteger
                for arg in args:
                    t_lb, t_ub, t_tp = get_info(arg)
                    if t_lb is not None and lb is not None:
                        lb = min(lb, t_lb)
                    else:
                        lb = None
                    
                    if t_ub is not None and ub is not None:
                        ub = min(ub, t_ub)
                    else:
                        ub = None
                    
                    if t_tp == LpContinuous:
                        tp = LpContinuous
                
                if lb is not None and ub is not None and lb == ub:
                        return lb
                if lb is None: lb = - self.M
                if ub is None: ub = self.M
                
                temp = self.tg.generate(lb, ub, cat=tp)
                
                or_stmt = 0
                for arg in args:
                    self.addConst(temp <= arg)
                    or_stmt = Node(OR, or_stmt, make_variable(temp) >= make_variable(arg))
                self += or_stmt
                self.table[temp.name] = Node(MIN, *args)
                self.setCache(n_node, temp)
                return temp
            elif node.op == MAX:
                args = [self.convert(arg) for arg in node.args]
                if all([type(arg) in (int, bool, float) for arg in args]):
                    return max(args)
                n_node = Node(MAX, *args)
                if self.hasCache(n_node):
                    return getCache(n_node)
                lb, ub, tp = -float('inf'), -float('inf'), LpInteger
                for arg in args:
                    t_lb, t_ub, t_tp = get_info(arg)
                    if t_lb is not None and lb is not None:
                        lb = max(lb, t_lb)
                    else:
                        lb = None
                    
                    if t_ub is not None and ub is not None:
                        ub = max(ub, t_ub)
                    else:
                        ub = None
                    
                    if t_tp == LpContinuous:
                        tp = LpContinuous
                
                if lb is not None and ub is not None and lb == ub:
                        return lb
                if lb is None: lb = - self.M
                if ub is None: ub = self.M
                
                temp = self.tg.generate(lb, ub, cat=tp)
                
                or_stmt = 0
                for arg in args:
                    self.addConst(temp >= arg)
                    or_stmt = Node(OR, or_stmt, make_variable(temp) <= make_variable(arg))
                self += or_stmt
                self.table[temp.name] = Node(MAX, *args)
                self.setCache(n_node, temp)
                return temp
            elif node.op == FLOOR:
                arg = self.convert(node.args[0])
                if type(arg) in (int, bool, float):
                    return math.floor(arg)
                
                n_node = Node(FLOOR, arg)
                if self.hasCache(n_node):
                    return self.getCache(n_node)
                    
                lb, ub, tp = get_info(arg)
                if lb is not None: lb -= 1
                temp = self.tg.generate(lb, ub, cat=LpInteger)
                
                self.addConst(temp <= arg)
                self += make_variable(temp + 1) > make_variable(arg)
                
                self.table[temp.name] = Node(FLOOR, arg)
                self.setCache(n_node, temp)
                return temp
            elif node.op == CEIL:
                arg = self.convert(node.args[0])
                if type(arg) in (int, bool, float):
                    return math.ceil(arg)
                
                n_node = Node(CEIL, arg)
                if self.hasCache(n_node):
                    return self.getCache(n_node)
                    
                lb, ub, tp = get_info(arg)
                if ub is not None: ub += 1
                temp = self.tg.generate(lb, ub, cat=LpInteger)
                
                self.addConst(temp >= arg)
                self += make_variable(temp -1) < make_variable(arg)
                
                self.table[temp.name] = Node(FLOOR, arg)
                self.setCache(n_node, temp)
                return temp
            elif node.op == FLOORDIV:
                raise Exception('Not implemented')
            elif node.op == MOD:
                raise Exception('Not implemented')
            elif node.op == VAR_TYPE:
                return node.args[0]                
            else:
                print(node.op)
                raise Exception('error')
        else:
            return node

    def topAddConst(self, node):
        if isinstance(node, Node):
            if node.op == AND:
                for arg in node.args:
                    self.topAddConst(arg)
            elif node.op == OR:
                args = [self.convert(arg) for arg in node.args]
                self.addConst(lpSum(args) >= 1, True)
            elif node.op == ADD:
                self.addConst(self.convert(node.args[0]) + self.convert(node.args[1]) == 1)
            elif node.op == MUL:
                self.addConst(self.convert(node.args[0]) * self.convert(node.args[1]) == 1)
            elif node.op == DIV:
                self.addConst(self.convert(node.args[0]) / self.convert(node.args[1]) == 1)
            elif node.op == EQ:
                self.addConst(self.convert(node.args[0]) == self.convert(node.args[1]))
            elif node.op == LE:
                self.addConst(self.convert(node.args[0]) <= self.convert(node.args[1]))
            elif node.op == GE:
                raise Exception('error')
                self.addConst(self.convert(node.args[0]) >= self.convert(node.args[1]))
            elif node.op == LT:
                eps = 1
                left = self.convert(node.args[0])
                right = self.convert(node.args[1])
                _, _, ltp = get_info(left)
                _, _, rtp = get_info(right)
                if LpContinuous in (ltp, rtp):
                    eps = self.eps
                self.addConst(left + eps <= right)
            else:
                raise Exception('error')
        elif isinstance(node, LpVariable):
            self.addConst(node == 1)
        elif type(node) in (int, float, bool):
            if node != 1:
                raise Exception('false constraint is found')
        else:
            raise Exception('error')

    def __iadd__(self, const):
        #self.temp_const.append(const)
        #const = self.temp_const.pop()
        #print('before: ',const)
        const = remove_double_not(const)
        const = remove_p_eq(const)
        const = remove_p_lt(const)
        const = remove_not(const)
        const = flat_and_or(const)
        const = clear_and_or(const)
        #print('after: ',const)
        #const = remove_eq(const, True)
        #const = clear_and_or(const)
        self.topAddConst(const)
        #print(self.exprs)
        return self

    def getConsts(self):
        return self.exprs
    
    def writeTable(self, filename):
        with open(filename, 'w') as f:
            for k,v in self.table.items():
                f.write('{} -> {}\n'.format(k, v))
    
    def getTemps(self):
        return self.tg.temps
                

def isOne(node):
    return type(node) in (int, float, bool) and node == 1

def isZero(node):
    return type(node) in (int, float, bool) and node == 0

def toLpV(node):
    if type(node) in (int, bool, float):
        return node
    elif isinstance(node, Node):
        if node.op == ADD:
            return toLpV(node.args[0]) + toLpV(node.args[1])
        elif node.op == MUL:
            return toLpV(node.args[0]) * toLpV(node.args[1])
        elif node.op == SUB:
            return toLpV(node.args[0]) - toLpV(node.args[1])
        elif node.op == DIV:
            return toLpV(node.args[0]) / toLpV(node.args[1])
        elif node.op == NEG:
            return - toLpV(node.args[0]) 
        elif node.op == POS:
            return toLpV(node.args[0])
        elif node.op == LE:
            return toLpV(node.args[0]) <= toLpV(node.args[1])
        elif node.op == GE:
            return toLpV(node.args[0]) >= toLpV(node.args[1])
        elif node.op == EQ:
            return toLpV(node.args[0]) == toLpV(node.args[1])
        elif node.op == VAR_TYPE:
            return node.args[0]
        else:
            raise Exception('cannot convert to Lp')
    else:
        raise Exception('cannot convert to Lp ')

LpP_default_iadd = LpProblem.__iadd__

def LpP_compatible_iadd(self, node):
    if isinstance(node, tuple):
        if isinstance(node[0], Node):
            if len(node) != 2:
                raise Exception('error')
            node = (toLpV(node[0]), node[1])
    if isinstance(node, Node):
        node = toLpV(node)
    return LpP_default_iadd(self, node)

LpProblem.__iadd__ = LpP_compatible_iadd

if __name__ == '__main__':
    a = LpVariable('a', cat=LpBinary)
    b = LpVariable('b', cat=LpBinary)
    c = LpVariable('c', cat=LpBinary)

    llp = LogicTranslator()
    llp += ~(a>=5)

    for e in llp.getConsts():
        print(e)
