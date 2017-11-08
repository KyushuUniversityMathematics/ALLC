import java.util.*;
public class Debug implements ASTVisitor<String>{
    public String visit(IntObj node){
	return String.valueOf(node.getInt());
    }
    public String visit(StrObj node){
	throw new LPGException("not lp");
    }
    public String visit(DoubleObj node){
	return String.valueOf(node.getDouble());
    }
    public String visit(LPVariable node){
	return node.getName();
    }
    public String visit(Identifier node){
	System.out.println(node.getName()+"::::");
	throw new LPGException(node.getName()+"not lp");
    }
    public String visit(Program node){
	throw new LPGException("not lp");
    }
    public String visit(Unary node){
	return "("+node.getName()+" "+node.getChild().accept(this)+")";
    }
    public String visit(BinOp node){
	String s="(";
	int num=node.numChildren();
	for(int i=0;i<num-1;i++){
	    s+=node.child(i).accept(this)+" "+node.getName()+" ";
	}
	s+=node.child(num-1).accept(this)+")";
	/*String left=node.getLeft().accept(this);
	String right=node.getRight().accept(this);
	String op=node.getName();*/
	//if(op.equals("*"))op="";
	//if(op.equals("=="))op="=";
	return s;
    }
    public String visit(IfStmt node){
	throw new LPGException("not lp");
    }
    public String visit(WhileStmt node){
	throw new LPGException("not lp");
    }
    public String visit(ForStmt node){
	throw new LPGException("not lp");
    }
    public String visit(BlockStmt node){
	throw new LPGException("not lp");
    }
    public String visit(LPDefStmt node){
	throw new LPGException("not lp");
    }
    public String visit(Params node){
	throw new LPGException("not lp");
    }
    public String visit(FuncDefStmt node){
	throw new LPGException("not lp");
    }
    public String visit(FuncCall node){
	throw new LPGException("not lp");
    }
    public String visit(Args node){
	throw new LPGException("not lp");
    }
    public String visit(Function node){
	throw new LPGException("not lp");
    }
    public String visit(Lambda node){
	throw new LPGException("not lp");
    }
    public String visit(ObjList node){
	throw new LPGException("not lp");
    }
    public String visit(ListRef node){
	throw new LPGException("not lp");
    }
    public String visit(ForEachStmt node){
	throw new LPGException("not lp");
    }
    public String visit(ForAll node){
	throw new LPGException("not lp");
    }
    public String visit(Exists node){
	throw new LPGException("not lp");
    }
    public String visit(Cond node){
    	throw new LPGException("");
    }
}
