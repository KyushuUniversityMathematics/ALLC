import java.util.*;
public class AndOrFlat implements ASTVisitor<Obj>{
    private static AndOrFlat singleton=new AndOrFlat();
    private AndOrFlat(){}
    public static AndOrFlat getInstance(){
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
	return node;
    }
    public Obj visit(BinOp node){
	if(!node.isName("&&")&&!node.isName("||")){
	    return new BinOp(node.getName(),node.getLeft().accept(this),node.getRight().accept(this));
	}else{
	    String op=node.getName();
	    List<Obj> list=new ArrayList<Obj>();
	    Obj left=node.getLeft().accept(this);
	    Obj right=node.getRight().accept(this);
	    if(left.isType("binop")&&left.isName(op)){
		for(Obj c:left){
		    list.add(c);
		}
	    }else{
		list.add(left);
	    }
	    if(right.isType("binop")&&right.isName(op)){
		for(Obj c:right){
		    list.add(c);
		}
	    }else{
		list.add(right);
	    }
	    return new BinOp(op,list);
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
