import java.util.*;
public class MulEval implements ASTVisitor<Obj>{
    private static MulEval singleton=new MulEval();
    private MulEval(){}
    public static  MulEval getInstance(){
	return singleton;
    }
    /*public Obj negate(Obj node){
	if(node.isType("unary")&&node.isName("-")){
	    return node.child(0);
	}else{
	    return new Unary("-",node);
	}
    }*/
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
	throw new LPGException("not lp");
    }
    public Obj visit(Program node){
	throw new LPGException("not lp");
    }
    public Obj visit(Unary node){
    	throw new LPGException("");
	//return new Unary(node.getName(),node.getChild().accept(this));
    }
    public Obj visit(BinOp node){
	if(!node.isName("*")){
		if(!node.isName("+"))throw new LPGException("");
	    List<Obj> l=new ArrayList<Obj>();
	    for(Obj child:node){
		l.add(child.accept(this));
	    }
	    return new BinOp(node.getName(),l);
	}
	List<Obj> l=new ArrayList<Obj>();
	l.add(new IntObj(1));
	for(Obj child:node){
	    if(child.isLp()){
		l.add(child);
	    }
	    if(child.isNumber()){
		Obj t=l.get(0);
		t=NumCalc.bin("*",t,child);
		l.set(0,t);
	    }
	}
	if(l.size()>2||l.size()==0){
	    throw new LPGException("not 1deg");
	}
	if(l.size()==2){
	    return new BinOp("*",l.get(0),l.get(1));
	}else{
	    return l.get(0);
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
    	throw new LPGException("");
    }
}
