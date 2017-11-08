import java.util.*;
public class TopEval implements ASTVisitor<Obj>{
    private static TopEval singleton=new TopEval();
    private TopEval(){
	et=ExprTrans.getInstance();
	db2=Debug2.getInstance();
	rs=RemSim.getInstance();
	aof=AndOrFlat.getInstance();
    }
    private ExprTrans et;
    private RemSim rs;
    private AndOrFlat aof;
    private Debug2 db2;
    private Obj goNext(Obj o){
	return o.accept(rs).accept(aof).accept(et);
    }
    private void print(Obj o){
	System.out.println(ExprEval.Eval(o).accept(db2));
    }
    public static TopEval getInstance(){
	return singleton;
    }
    public Obj visit(IntObj node){
	if(IntObj.isTrue(node)){
	    //pass
	    return null;
	}else if(IntObj.isFalse(node)){
	    throw new LPGException("結果がfalseになる制約式が見つかりました");
	}
	throw new LPGException("top is int");
    }
    public Obj visit(StrObj node){
	throw new LPGException("not lp");
    }
    public Obj visit(DoubleObj node){
	throw new LPGException("top is double");
    }
    public Obj visit(LPVariable node){
	throw new LPGException("top is lpvar");
    }
    public Obj visit(Identifier node){
	throw new LPGException(node.getName()+"not lp");
    }
    public Obj visit(Program node){
	throw new LPGException("not lp");
    }
    public Obj visit(Unary node){
	if(node.isName("-")){
	    throw new LPGException("top is minus");
	}
	if(node.isName("!")){
	    //6/21
	    //System.out.println("\\top is !");
	    //System.out.println("\\"+node.accept(new Debug()));
	    Obj c=goNext(node.getChild());
	    c=new BinOp("==",c,IntObj.getZero());
	    print(c);
	}else{
		throw new LPGException("s");
	}
	return null;
    }
    public Obj visit(BinOp node){
	    //System.out.println("\\top is "+node.getName()+" num is "+node.numChildren());
	    //System.out.println("\\"+node.accept(new Debug()));
	if(node.isName("<=")||node.isName("==")||node.isName(">=")){
	    Obj left=goNext(node.getLeft());
	    Obj right=goNext(node.getRight());
	    if(node.isName("<=")){
		print(new BinOp("<=",left,right));
	    }else if(node.isName(">=")){
		print(new BinOp(">=",left,right));
	    }else{
		print(new BinOp("==",left,right));
	    }
	    return null;
	}else if(node.isName("||")){
	    //System.out.println("\\top is ||");
	    //int n=node.numChildren();
	    Obj limit=IntObj.getOne();
	    Obj sum=IntObj.getZero();
	    for(Obj c:node){
		c=goNext(c);
		sum=new BinOp("+",sum,c);
	    }
	    print(new BinOp(">=",sum,limit));
	    return null;
	}else if(node.isName("&&")){
	    //System.out.println("\\top is &&");
	    for(Obj c:node){
		c.accept(this);
	    }
	    return null;
	}else{
		if(!node.isName("-"))throw new LPGException("");
	//	System.out.println("\\ top is "+node.getName());
	    Obj o=goNext(node);
	    print(new BinOp("==",o,IntObj.getOne()));
	    return null;
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
    	throw new LPGException("top is cond");
    }
}
