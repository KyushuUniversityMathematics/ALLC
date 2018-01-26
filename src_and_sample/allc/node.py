import pulp
AND = 'and'
OR = 'or'
NOT = 'not'

ADD = 'add'
NEG = 'neg'
POS = 'pos'
SUB = 'sub'
MUL = 'mul'
DIV = 'div'

EQ = 'eq'
LE = 'le'
GE = 'ge'
LT = 'lt'
GT = 'gt'

COND = 'cond'
MIN = 'min'
MAX = 'max'
FLOOR = 'floor'
CEIL = 'ceil'
FLOORDIV = 'floordiv'
MOD = 'mod'

VAR_TYPE = 'var'
A_EXPR_TYPE = 'a_expr'

class Node(object):
    def __init__(self, op, *args):
        self.op = op
        self.args = args

    def __str__(self):
        return self.op + '(' + ', '.join(map(str, self.args)) + ')'

    def __and__(self, other):
        return Node(AND, self, other)

    def __rand__(self, other):
        return self & other

    def __or__(self, other):
        return Node(OR, self, other)

    def __ror__(self, other):
        return self | other

    def __invert__(self):
        return Node(NOT, self)

    def __add__(self, other):
        return Node(ADD, self, other)

    def __radd__(self, other):
        return self + other

    def __neg__(self):
        return Node(NEG, self)
    
    def __pos__(self):
        return Node(POS, self)

    def __sub__(self, other):
        return Node(ADD, self, -other)

    def __rsub__(self, other):
        return - self + other

    def __mul__(self, other):
        return Node(MUL, self, other)

    def __rmul__(self, other):
        return self * other

    def __truediv__(self, other):
        return Node(DIV, self, other)
    
    def __floordiv__(self, other):
        return Node(FLOOR, self / other)
        #return Node(FLOORDIV, self, other)
    
    def __mod__(self, other):
        return self - other * (self // other)
        #return Node(MOD, self, other)
    
    def __eq__(self, other):
        return Node(EQ, self, other)

    def __le__(self, other):
        return Node(LE, self, other)

    def __ge__(self, other):
        #return Node(GE, self, other)
        return Node(LE, other, self)
    
    def __rshift__(self, other):
        return Node(OR, Node(NOT, self), other)
    
    def __rrshift__(self, other):
        return Node(OR, Node(NOT, other), self)
    
    def __lt__(self, other):
        #return Node(NOT, Node(LE, other, self))
        return Node(LT, self, other)
    
    def __gt__(self, other):
        #return Node(NOT, Node(LE, self, other))
        return Node(LT, other, self)
    
    def __ne__(self, other):
        return Node(NOT, self == other)

def and_(*args):
    return Node(AND, *args)

def or_(*args):
    return Node(OR, *args)

def not_(arg):
    return Node(NOT, arg)

def imply(arg1, arg2):
    return Node(OR, ex.Node(NOT, arg1), arg2)

def cond(c, t, f):
    return Node(COND, c, t, f)

def min_(*args):
    return Node(MIN, *args)

def max_(*args):
    return Node(MAX, *args)

def floor(x):
    return Node(FLOOR, x)

def ceil(x):
    return Node(CEIL, x)

def abs_(x):
    return max_(-x, x)

class Variable(Node):
    def __init__(self, name, lowBound = None, upBound = None,
                      cat = pulp.LpContinuous, e = None):
        self.op = VAR_TYPE
        self.args = [pulp.LpVariable(name, lowBound, upBound, cat, e)]
        self.getName = self.args[0].getName
        self.value = self.args[0].value
    
    @staticmethod
    def dicts(name, indexs, lowBound = None, upBound = None, cat = pulp.LpContinuous):
        if len(indexs) == 0:
            return Variable(name, lowBound, upBound, cat)
        d = {}
        head = indexs[0]
        tail = indexs[1:]
        for i in head:
            d[i] = Variable.dicts(name+'_'+str(i), tail, lowBound, upBound, cat)
        return d
'''
def Variable(name, lowBound = None, upBound = None,
                  cat = pulp.LpContinuous, e = None):
    node = Node(VAR_TYPE)
    node.args = [pulp.LpVariable(name, lowBound, upBound, cat, e)]
    node.getName = node.args[0].getName
    node.value = node.args[0].value
    return node
'''

def make_variable(v):
    node = Node(VAR_TYPE)
    node.args = [v]
    return node

if __name__ == '__main__':
    v, w = Variable('v'), Variable('w')
    print(~v<=w+(1&v|w))
