import java.util.*;
public class ExprTrans implements ASTVisitor<Obj>{
    private static ExprTrans singleton=new ExprTrans();
    private ExprTrans(){
	tmpVariables=new HashMap<Obj,Obj>();
    }
    public static ExprTrans getInstance(){
	return singleton;
    }
    public static void setEnv(Environment grobal){
	if(grobal.get(tmpMStr)==null){
	    grobal.put(tmpMStr,new IntObj(1000));
	}
	if(grobal.get(tmpEpsStr)==null){
	    grobal.put(tmpEpsStr,new DoubleObj(0.01));
	}
	singleton.grobal=grobal;
    }
    /*public static void setFreeVars(List<Obj> v){
	singleton.freeVars=v;
    }*/
    /*public static void setBinaryVars(List<Obj> v){
	singleton.binaryVars=v;
    }*/
    private Environment grobal;
    private Debug2 db2=Debug2.getInstance();
    private int tmpindex=0;
    private static String tmpMStr="@tmpM";
    private static String tmpEpsStr="@tmpEps";
    private String tmpS="@tmp";
    private Map<Obj,Obj> tmpVariables;
    //private List<Obj> freeVars;
    //private List<Obj> binaryVars;
    /*public ExprTrans(Environment grobal){
	this.grobal=grobal;
	db2=new Debug2();
	if(grobal.get(tmpMStr)==null){
	    grobal.put(tmpMStr,new IntObj(1000));
	}
	if(grobal.get(tmpEpsStr)==null){
	    grobal.put(tmpEpsStr,new DoubleObj(0.01));
	}
	tmpindex=0;
    }*/
    /*public ExprTrans(DoubleObj tmpM,DoubleObj tmpEps){
	this.tmpM=tmpM;
	this.tmpEps=tmpEps;
	tmpindex=0;
	db2=new Debug2();
    }*/
    private Obj getM(){
	return grobal.get(tmpMStr);
    }
    private Obj getEps(){
	return grobal.get(tmpEpsStr);
    }
    private void print(Obj o){
	o=ExprEval.Eval(o);
	System.out.println(o.accept(db2));
    }
    private Obj gbinary(){
	Obj r=new LPVariable("binary",tmpS+tmpindex);
	//binaryVars.add(r);
	tmpindex++;
	return r;
    }
    private Obj gfree(){
	Obj r=new LPVariable("free",tmpS+tmpindex);
	//freeVars.add(r);
	tmpindex++;
	return r;
    }
    public Obj visit(IntObj node){
	return node;
    }
    public Obj visit(StrObj node){
	throw new LPGException("not lp");
    }
    public Obj visit(DoubleObj node){
	return node;
    }
    public Obj visit(LPVariable node){
	return node;
    }
    public Obj visit(Identifier node){
	throw new LPGException(node.getName()+"not lp");
    }
    public Obj visit(Program node){
	throw new LPGException("not lp");
    }
    public Obj visit(Unary node){
    	throw new LPGException("");
	//return node;
    }
    public Obj visit(BinOp node){
    	//System.out.println("\\call binop "+node.getName());
    	//System.out.println("\\ tt"+node.numChildren()+" "+node.accept(new Debug()));
	Obj gt=tmpVariables.get(node);
	if(gt!=null)return gt;
	if(node.isName("<=")){
	    Obj o=gbinary();
	    Obj left=node.getLeft().accept(this);
	    Obj right=node.getRight().accept(this);
	    print(new BinOp("<=",
	    	new BinOp("-",
		    new BinOp("+",
			left,
			new BinOp("*",getM(),o)),
		    getM()),
		right));
	    
	    //System.out.println(tmpM.getDouble());
	    //System.out.println(left.accept(new Debug()));
	    print(new BinOp(">=",
	    	new BinOp("-",
		    new BinOp("+",
			left,
			new BinOp("*",getM(),o)),
		    getEps()),
		right));
	    tmpVariables.put(node,o);
	    return o;
	}else if(node.isName("||")||node.isName("&&")){
	    if(node.numChildren()==0)throw new LPGException("");
	    Obj num=new IntObj(node.numChildren());
	    Obj sum=IntObj.getZero();
	    //6/23
	    Set<Obj> set=new HashSet<Obj>();
	    for(Obj c:node){
		set.add(c.accept(this));
	    }
	    //***
	    for(Obj c:node){
		sum=new BinOp("+",sum,c.accept(this));
	    }
	    Obj limit;
	    if(node.isName("&&")){
		limit=new IntObj(num.getInt());
	    }else if(node.isName("||")){
		limit=IntObj.getOne();
	    }else{
		throw new LPGException("????");
	    }
	    Obj o=gbinary();
	    print(new BinOp(">=",sum,new BinOp("*",limit,o)));
	    print(new BinOp("<=",sum,
	    new BinOp("+",new BinOp("*",num,o),new BinOp("*",new IntObj(limit.getInt()-1),new BinOp("-",IntObj.getOne(),o)))));
	    tmpVariables.put(node,o);
	    return o;
	}else{
		if(node.isName(">=")||node.isName("=="))throw new LPGException("");
	    Obj left=node.getLeft().accept(this);
	    Obj right=node.getRight().accept(this);
	    return new BinOp(node.getName(),left,right);
	}
    }
    public Obj visit(IfStmt node){
	throw new LPGException("not lp");
    }
    public Obj visit(WhileStmt node){
	throw new LPGException("not lp");
    }
    public Obj visit(ForStmt node){
	throw new LPGException("not lp");
    }
    public Obj visit(BlockStmt node){
	throw new LPGException("not lp");
    }
    public Obj visit(LPDefStmt node){
	throw new LPGException("not lp");
    }
    public Obj visit(Params node){
	throw new LPGException("not lp");
    }
    public Obj visit(FuncDefStmt node){
	throw new LPGException("not lp");
    }
    public Obj visit(FuncCall node){
	throw new LPGException("not lp");
    }
    public Obj visit(Args node){
	throw new LPGException("not lp");
    }
    public Obj visit(Function node){
	throw new LPGException("not lp");
    }
    public Obj visit(Lambda node){
	throw new LPGException("not lp");
    }
    public Obj visit(ObjList node){
	throw new LPGException("not lp");
    }
    public Obj visit(ListRef node){
	throw new LPGException("not lp");
    }
    public Obj visit(ForEachStmt node){
	throw new LPGException("not lp");
    }
    public Obj visit(ForAll node){
	throw new LPGException("not lp");
    }
    public Obj visit(Exists node){
	throw new LPGException("not lp");
    }
    public Obj visit(Cond node){
	Obj gt=tmpVariables.get(node);
	if(gt!=null)return gt;
	Obj expr=node.getExpr().accept(this);
	Obj then=node.getThen().accept(this);
	Obj elze=node.getElse().accept(this);
	Obj o=gfree();
	Obj t1=new BinOp("*",getM(),new BinOp("-",IntObj.getOne(),expr));
	Obj t2=new BinOp("-",o,then);
	print(new BinOp("<=",new Unary("-",t1),t2));
	print(new BinOp("<=",t2,t1));
	Obj t3=new BinOp("*",getM(),expr);
	Obj t4=new BinOp("-",o,elze);
	print(new BinOp("<=",new Unary("-",t3),t4));
	print(new BinOp("<=",t4,t3));
	tmpVariables.put(node,o);
	return o;
    }
}
