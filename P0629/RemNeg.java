import java.util.*;
public class RemNeg implements ASTVisitor<Obj>{
    private static RemNeg singleton=new RemNeg();
    public static RemNeg getInstance(){
	return singleton;
    }
    private RemNeg(){}
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
	Obj child=node.getChild().accept(this);
	if(node.isName("-")){
	    return new BinOp("*",IntObj.getNegative(),child);
	}
		throw new LPGException("");
	//return new Unary(node.getName(),child);
    }
    public Obj visit(BinOp node){
	Obj left=node.getLeft().accept(this);
	Obj right=node.getRight().accept(this);
	if(node.isName("-")){
	    return new BinOp("+",left,new BinOp("*",IntObj.getNegative(),right));
	}
	return new BinOp(node.getName(),left,right);
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
