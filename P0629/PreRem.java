import java.util.*;
public class PreRem implements ASTVisitor<Obj>{
    private static PreRem singleton=new PreRem();
    private PreRem(){}
    public static PreRem getInstance(){
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
	    return new BinOp("-",IntObj.getOne(),c);
	}else{
	    return new Unary(node.getName(),c);
	}
    }
    public Obj visit(BinOp node){
	Obj left=node.getLeft().accept(this);
	Obj right=node.getRight().accept(this);
	if(node.isName(">=")){
	    return new BinOp("<=",right,left);
	}else if(node.isName(">")){
	    return new BinOp("-",IntObj.getOne(),new BinOp("<=",left,right));
	}else if(node.isName("<")){
	    return new BinOp("-",IntObj.getOne(),new BinOp("<=",right,left));
	}else if(node.isName("->")){
	    return new BinOp("||",new BinOp("-",IntObj.getOne(),left),right);
	}else if(node.isName("==")){
	    return new BinOp("==",left,right);
	}else if(node.isName("!=")){
	    return new BinOp("||",
	    new BinOp("-",IntObj.getOne(),new BinOp("<=",left,right)),
	    new BinOp("-",IntObj.getOne(),new BinOp("<=",right,left)));
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
