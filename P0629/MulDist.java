import java.util.*;
public class MulDist implements ASTVisitor<Obj>{
    private static MulDist singleton=new MulDist();
    private MulDist(){}
    public static MulDist getInstance(){
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
    	if(!node.isName("*")&&!node.isName("+"))throw new LPGException("");
	Obj left=node.getLeft().accept(this);
	Obj right=node.getRight().accept(this);
	if(!node.isName("*")){
	    return new BinOp(node.getName(),left,right);
	}
	boolean lp=left.isType("binop")&&left.isName("+");
	boolean rp=right.isType("binop")&&right.isName("+");
	if(!lp&&!rp){
	    return new BinOp("*",left,right);
	}else if(lp&&!rp){
	    Obj nl=new BinOp("*",left.child(0),right);
	    Obj nr=new BinOp("*",left.child(1),right);
	    return new BinOp("+",nl.accept(this),nr.accept(this));
	}else/* if(!lp&&rp)*/{
	    Obj nl=new BinOp("*",left,right.child(0));
	    Obj nr=new BinOp("*",left,right.child(1));
	    return new BinOp("+",nl.accept(this),nr.accept(this));
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
