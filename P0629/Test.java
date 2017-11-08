import java.util.*;
import java.io.*;
public class Test implements ASTVisitor<Obj>{
    private ArrayList<Environment> envs;
    private Set<String> vars;
    private Set<String> freeVars;
    private Set<String> generalVars;
    private Set<String> generalFreeVars;
    private Set<String> binaryVars;
    boolean isLib=false;
    public Test(){
	envs=new ArrayList<Environment>();
	envs.add(new Environment());
	ExprTrans.setEnv(envs.get(0));
	vars=new TreeSet<String>();
	freeVars=new TreeSet<String>();
	generalVars=new TreeSet<String>();
	generalFreeVars=new TreeSet<String>();
	binaryVars=new TreeSet<String>();
	Debug2.setVars(vars,freeVars,generalVars,generalFreeVars,binaryVars);
	//ExprTrans.setBinaryVars(binaryVars);
	//ExprTrans.setFreeVars(freeVars);
    }
    public void getLib(Obj o){
	isLib=true;
	o.accept(this);
	isLib=false;
    }
    public Environment currentEnv(){
	return envs.get(envs.size()-1);
    }
    public void pushEnv(Environment env){
	envs.add(env);
    }
    public Environment popEnv(){
	return envs.remove(envs.size()-1);
    }
    public Environment getGrobal(){
	return envs.get(0);
    }

    public static void main(String[] args)throws Exception{
	Test t=new Test();
	BufferedReader r=new BufferedReader(new FileReader(args[0]));
	StringBuilder source=new StringBuilder();
	String line;
	while((line=r.readLine())!=null){
	    if(!(line.startsWith("#"))){
		source.append(line).append('\n');
	    }
	}
	Obj p=LPGenerator.getProgramFromString(source.toString());
	p.accept(t);
    }
    public Obj visit(IntObj node){
	return node;
    }
    public Obj visit(StrObj node){
	return node;
    }
    public Obj visit(DoubleObj node){
	return node;
    }
    public Obj visit(LPVariable node){
	return node;
    }
    public Obj visit(Identifier node){
	if(currentEnv().get(node.getName())==null){
	    throw new LPGException(node.getName()+"の参照が見つかりません");
	}
	return currentEnv().get(node.getName());
    }
    public Obj visit(Program node){
	List<String> libs=node.getLibs();
	for(String s:libs){
	    s+=".txt";
	    try{
		Obj lib=LPGenerator.getProgram(s);
		getLib(lib);
	    }catch(Exception e){
		throw new LPGException(s+"のインポートに失敗しました");
	    }
	}
	if(isLib){
	    for(Obj o:node){
		o.accept(this);
	    }
	    return null;
	}
	System.out.println(node.getMize());
	System.out.println("@object");
	System.out.println();
	System.out.println("subject to");
	//Obj goal=new LPVariable("free","@object");
	String goal="@object";
	freeVars.add(goal);
	currentEnv().put("@object",new LPVariable("free",goal));
	Obj r=null;
	for(Obj obj:node){
	    r=obj.accept(this);
	}
	//Obj t=r;
	//ExprTrans et=new ExprTrans(new DoubleObj(1000),new DoubleObj(0.01));
	/////////SubjEval.Eval(r);
	//ExprTrans et=ExprTrans.getInstance();
	//System.out.println(r.accept(new RemSim()).accept(new AndOrFlat()).accept(et).accept(new Debug()));
	//r=ExprEval.Eval(r);
	/*
	//r=new BinOp("-",r.child(0),r.child(1));
	r=r.child(0);
	System.out.println(r.accept(new Debug()));
	//r=r.accept(new SubDel());
	r=r.accept(new RemNeg());
	System.out.println(r.accept(new Debug()));
	//r=r.accept(new NegDist());
	//System.out.println(r.accept(new Debug()));
	r=r.accept(new MulDist());
	System.out.println(r.accept(new Debug()));
	//r=r.accept(new NegOut());
	//System.out.println(r.accept(new Debug()));
	r=r.accept(new ExprSort());
	System.out.println(r.accept(new Debug()));
	r=r.accept(new MulEval());
	System.out.println(r.accept(new Debug()));
	//r=r.accept(new PlEval());
	r=r.accept(new PlEval2());
	//r=r.accept(new FinalEval());
	*/
	//System.out.println(r.accept(new Debug2()));
	System.out.println();
	System.out.println("bounds");
	/*for(String o:vars){
	    System.out.println(o);
	}*/
	for(String o:freeVars){
	    System.out.println(o+" free");
	}

	System.out.println();
	System.out.println("general");
	for(String o:generalVars){
	    System.out.println(o);
	}
	for(String o:generalFreeVars){
	    System.out.println(o+" free");
	}

	System.out.println();
	System.out.println("binary");
	for(String o:binaryVars){
	    System.out.println(o);
	}
	System.out.println();
	System.out.println("end");
	return r;
    }
    public Obj visit(Unary node){
	Obj child=node.getChild().accept(this);
	if(child.isNumber()){
	    return NumCalc.unary(node.getName(),child);
	}
	else{
	    return new Unary(node.getName(),child);
	}
	//throw new LPGException("not num");
    }
	
    public Obj visit(BinOp node){
	if(node.isName("=")){
	    Obj left=node.getLeft();
	    /*if(!left.isType("identifier")){
		throw new LPGException("lvalue should be identifier");
	    }*/
	    if(left.isType("identifier")){
		String name=left.getName();
		/*if(currentEnv().get(name)!=null&&currentEnv().get(name).isLp()){
		    throw new LPGException("lp変数"+currentEnv().get(name).getName()+"に代入操作を適用できません");
		}*/
		if(left.isLp()){
		    throw new LPGException("lp変数"+left.getName()+"に代入操作を適用できません");
		}
		Obj right=node.getRight().accept(this);
		if(left.isLocal()){
		    //System.out.println("local");
		    currentEnv().putNew(name,right);
		}else{
		    currentEnv().put(name,right);
		}
		return right;
	    }else if(left.isType("listref")){
		Obj expr=left.child(0).accept(this);
		Obj index=left.child(1).accept(this);
		if(!expr.isType("objlist")){
		    throw new LPGException("リストではないオブジェクトに添字演算子が呼ばれました");
		}
		Obj right=node.getRight().accept(this);
		expr.set(index.getInt(),right);
		return right;
	    }else{
		throw new LPGException("代入演算子の左に左辺値でないオブジェクトが見つかりました");
	    }
	}
	Obj left=node.getLeft().accept(this);
	Obj right=node.getRight().accept(this);
	if(left.isNumber()&&right.isNumber()){
	    return NumCalc.bin(node.getName(),left,right);
	}else if(left.isType("objlist")&&right.isType("objlist")){
	    List<Obj> nl=new ArrayList<Obj>();
	    for(Obj c:left){
		nl.add(c);
	    }
	    for(Obj c:right){
		nl.add(c);
	    }
	    return new ObjList(nl);
	}
	//6/22
	if(node.isName("&&")){
	    if(IntObj.isFalse(left)||IntObj.isFalse(right)){
		return IntObj.getZero();
	    }else if(IntObj.isTrue(left)){
		return right;
	    }else if(IntObj.isTrue(right)){
		return left;
	    }
	}
	if(node.isName("||")){
	    if(IntObj.isTrue(left)||IntObj.isTrue(right)){
		return IntObj.getOne();
	    }else if(IntObj.isFalse(left)){
		return right;
	    }else if(IntObj.isFalse(right)){
		return left;
	    }
	}
	if(node.isName("->")){
	    if(IntObj.isFalse(left)||IntObj.isTrue(right)){
		return IntObj.getOne();
	    }else if(IntObj.isTrue(left)){
		return right;
	    }else if(IntObj.isFalse(right)){
		if(left.isNumber()){
		    return NumCalc.bin("-",IntObj.getOne(),left);
		}else{
		    return new BinOp("-",IntObj.getOne(),left);
		}
	    }
	}
	return new BinOp(node.getName(),left,right);
	//throw new LPGException("not num");
    }
    public Obj visit(IfStmt node){
	Obj cond=node.getCond().accept(this);
	if(!cond.isType("int")||cond.getInt()!=0){
	    return node.getThen().accept(this);
	}else{
	    if(node.hasElse()){
		return node.getElse().accept(this);
	    }
	    return null;
	}
    }
    public Obj visit(WhileStmt node){
	Obj ret=null;
	while(true){
	    Obj cond=node.getCond().accept(this);
	    if(cond.isType("int")&&cond.getInt()==0)break;
	    ret=node.getStmt().accept(this);
	}
	return ret;
    }
    public Obj visit(ForStmt node){
	Obj ret=null;
	node.getInit().accept(this);
	while(true){
	    Obj cond=node.getCond().accept(this);
	    if(cond.isType("int")&&cond.getInt()==0)break;
	    ret=node.getStmt().accept(this);
	    node.getStep().accept(this);
	}
	return ret;
    }
    public Obj visit(BlockStmt node){
	Obj ret=null;
	for(Obj child:node){
	    ret=child.accept(this);
	}
	return ret;
    }
    public Obj visit(LPDefStmt node){
	Obj child=node.getChild();
	if(currentEnv().get(child.getName())!=null){
	    throw new LPGException(child.getName()+" is already defined");
	}
	//getGrobal().put(child.getName(),child);
	//6/22
	currentEnv().put(child.getName(),child);
	/*if(child.isType("variable")){
	    vars.add(child);
	}else if(child.isType("free")){
	    freeVars.add(child);
	}else if(child.isType("general")){
	    generalVars.add(child);
	}else if(child.isType("generalfree")){
	    generalFreeVars.add(child);
	}else{
	    binaryVars.add(child);
	}*/
	return node.getChild().accept(this);
    }
    public Obj visit(FuncDefStmt node){
	String name=node.getName();
	Obj fun=new Function(node.getParams(),node.getBlock(),currentEnv());
	currentEnv().put(name,fun);
	return null;
    }
    public Obj visit(Params node){
	return null;
    }
    public Obj visit(FuncCall node){
	if(node.getExpr().isType("identifier")&&node.getExpr().isName("print")){
	    Obj a=node.getArgs().child(0).accept(this);
	    System.out.println("\\"+a.accept(new Debug()));
	    return null;
	}else if(node.getExpr().isType("identifier")&&node.getExpr().isName("subject")){
	    Obj args=node.getArgs();
	    for(Obj c:args){
		c=c.accept(this);
		SubjEval.Eval(c);
	    }
	    return null;
	}else if(node.getExpr().isType("identifier")&&node.getExpr().isName("len")){
	    Obj a=node.getArgs().child(0).accept(this);
	    if(!a.isType("objlist")){
		throw new LPGException("len関数を適用できるのはリストだけです");
	    }
	    return new IntObj(a.numChildren());
	}else if(node.getExpr().isType("identifier")&&node.getExpr().isName("sqrt")){
	    Obj a=node.getArgs().child(0).accept(this);
	    return new DoubleObj(Math.sqrt(a.getDouble()));
	}else if(node.getExpr().isType("identifier")&&node.getExpr().isName("sin")){
	    Obj a=node.getArgs().child(0).accept(this);
	    return new DoubleObj(Math.sin(a.getDouble()));
	}else if(node.getExpr().isType("identifier")&&node.getExpr().isName("cos")){
	    Obj a=node.getArgs().child(0).accept(this);
	    return new DoubleObj(Math.cos(a.getDouble()));
	}else if(node.getExpr().isType("identifier")&&node.getExpr().isName("tan")){
	    Obj a=node.getArgs().child(0).accept(this);
	    return new DoubleObj(Math.tan(a.getDouble()));
	}
	Obj expr=node.getExpr().accept(this);
	Obj params=expr.child(0);//getParams
	Obj stmt=expr.child(1);//getStmtaaaaaaggetBlock
	Obj args=node.getArgs().accept(this);
	//Environment env=new Environment(getGrobal());
	Environment env=new Environment(expr.getEnv());
	if(params.numChildren()!=args.numChildren()){
	    throw new LPGException("numbers of params and args are different");
	}
	for(int i=0;i<params.numChildren();i++){
	    env.putNew(params.child(i).getName(),args.child(i));
	}
	pushEnv(env);
	Obj ret=stmt.accept(this);
	popEnv();
	return ret;
    }
    public Obj visit(Args node){
	List<Obj> ret=new ArrayList<Obj>();
	for(Obj arg:node){
	    ret.add(arg.accept(this));
	}
	return new Args(ret);
    }
    public Obj visit(Function node){
	return node;
    }
    public Obj visit(Lambda node){
	return new Function(node.getParams(),node.getBlock(),currentEnv());
    }
    public Obj visit(ObjList node){
	List<Obj> nl=new ArrayList<Obj>();
	for(Obj c:node){
	    nl.add(c.accept(this));
	}
	return new ObjList(nl);
    }
    public Obj visit(ListRef node){
	Obj expr=node.getExpr().accept(this);
	Obj index=node.getIndex().accept(this);
	if(expr.isLp()){
	    String t=expr.getType();
	    String name=expr.getName();
	    String newName=name+"["+index.getInt()+"]";
	    Obj lv=new LPVariable(t,newName);
	    /*if(currentEnv().get(newName)==null){
		getGrobal().put(newName,lv);
		if(t.equals("variable")){
		    vars.add(lv);
		}else if(t.equals("free")){
		    freeVars.add(lv);
		}else if(t.equals("general")){
		    generalVars.add(lv);
		}else if(t.equals("generalfree")){
		    generalFreeVars.add(lv);
		}else{
		    binaryVars.add(lv);
		}
	    }*/
	    return lv;
	}
	return expr.child(index.getInt());
    }
    public Obj visit(ForEachStmt node){
	Obj index=node.getIndex();
	Obj list=node.getList().accept(this);
	Obj stmt=node.getStmt();
	if(!index.isType("identifier")){
	    throw new LPGException("for文のインデックスはidentifierしか使えません");
	}
	if(!list.isType("objlist")){
	    throw new LPGException("for文の in の後にリストでないオブジェクトが見つかりました");
	}
	String indexName=index.getName();
	Obj r=null;
	for(Obj c:list){
	    currentEnv().putNew(indexName,c);
	    r=stmt.accept(this);
	}
	return r;
    }
    public Obj visit(ForAll node){
	Obj index=node.getIndex();
	Obj list=node.getList().accept(this);
	Obj expr=node.getExpr();
	if(!index.isType("identifier")){
	    throw new LPGException("forall演算子のインデックスにはidentifierしか使えません");
	}
	if(!list.isType("objlist")){
	    throw new LPGException("forall演算子の in の後にリストでないオブジェクトが見つかりました");
	}
	String indexName=index.getName();
	if(list.numChildren()==0){
	    return new IntObj(1);
	}
	Obj s=list.child(0);
	currentEnv().putNew(indexName,s);
	Obj r=expr.accept(this);
	for(int i=1;i<list.numChildren();i++){
	    Obj c=list.child(i);
	    currentEnv().putNew(indexName,c);
	    r=new BinOp("&&",r,expr.accept(this));
	}
	return r;
    }
    public Obj visit(Exists node){
	Obj index=node.getIndex();
	Obj list=node.getList().accept(this);
	Obj expr=node.getExpr();
	if(!index.isType("identifier")){
	    throw new LPGException("exists演算子のインデックスにはidentifierしか使えません");
	}
	if(!list.isType("objlist")){
	    throw new LPGException("exists演算子の in の後にリストでないオブジェクトが見つかりました");
	}
	String indexName=index.getName();
	if(list.numChildren()==0){
	    return IntObj.getZero();
	}
	String op=node.isNested()?"+":"||";
	Obj s=list.child(0);
	currentEnv().putNew(indexName,s);
	Obj r=expr.accept(this);
	for(int i=1;i<list.numChildren();i++){
	    Obj c=list.child(i);
	    currentEnv().putNew(indexName,c);
	    r=new BinOp(op,r,expr.accept(this));
	}
	if(node.hasOption()){
	    Obj option=node.getOption().accept(this);
	    if(!option.isType("function")&&!option.isType("int")){
		throw new LPGException("existsのオプションに使用できるのは整数か関数です");
	    }
	    if(option.isType("int")){
		if(r.isNumber()){
		    return NumCalc.bin("==",option,r);
		}else{
		    return new BinOp("==",option,r);
		}
	    }
	    List<Obj> arg=new ArrayList<Obj>();
	    arg.add(r);
	    Obj func=new FuncCall(option,new Args(arg));
	    return func.accept(this);
	}
	return r;
    }
    public Obj visit(Cond node){
	Obj expr=node.getExpr().accept(this);
	Obj then=node.getThen().accept(this);
	Obj elze=node.getElse().accept(this);
	if(IntObj.isTrue(expr)){
	    return then;
	}else if(IntObj.isFalse(expr)){
	    return elze;
	}
	return new Cond(expr,then,elze);
    }
}
