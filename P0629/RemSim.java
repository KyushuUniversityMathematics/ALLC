import java.util.*;
public class RemSim implements ASTVisitor<Obj>{
    private static RemSim singleton=new RemSim();
    private RemSim(){}
    public static RemSim getInstance(){
	return singleton;
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
	Obj c=node.getChild().accept(this);
	if(node.isName("!")){
		throw new LPGException("");
	    //return new BinOp("-",new IntObj(1),c);
	}else{
	    return new Unary(node.getName(),c);
	}
    }
    public Obj visit(BinOp node){
	//num is not always 2.
	//6/21
	if(node.isName("&&")||node.isName("||")){
	    //if && or || then fold again and unfold.
	    Obj r=node.child(0).accept(this);
	    int num=node.numChildren();
	    for(int i=1;i<num;i++){
		r=new BinOp(node.getName(),r,node.child(i).accept(this));
	    }
	    return r;
	}
	Obj left=node.getLeft().accept(this);
	Obj right=node.getRight().accept(this);
	if(node.isName(">=")){
		throw new LPGException("");
	    //return new BinOp("<=",right,left);
	}else if(node.isName(">")){
		throw new LPGException("");
	    //return new BinOp("-",new IntObj(1),new BinOp("<=",left,right));
	}else if(node.isName("<")){
		throw new LPGException("");
	    //return new BinOp("-",new IntObj(1),new BinOp("<=",right,left));
	}else if(node.isName("->")){
		throw new LPGException("");
	    //return new BinOp("||",new BinOp("-",new IntObj(1),left),right);
	}else if(node.isName("==")){
	    return new BinOp("&&",new BinOp("<=",left,right),new BinOp("<=",right,left));
	}else if(node.isName("!=")){
		throw new LPGException("");
	    /*return new BinOp("||",
	    new BinOp("-",new IntObj(1),new BinOp("<=",left,right)),
	    new BinOp("-",new IntObj(1),new BinOp("<=",right,left)));
	    */
	}else{
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
	Obj expr=node.getExpr().accept(this);
	Obj then=node.getThen().accept(this);
	Obj elze=node.getElse().accept(this);
	return new Cond(expr,then,elze);
    }
}
