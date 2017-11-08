import java.util.*;
public class SyntaxDebug implements ASTVisitor<Void>{
    public static void main(String[] args)throws Exception{
	String s=args[0];
	Obj p=LPGenerator.getProgram(s);
	SyntaxDebug sd=new SyntaxDebug();
	p.accept(sd);
    }
    int depth=0;
    public String f(){
	String s="";
	for(int i=0;i<depth;i++)s+=" ";
	return s;
    }
    public Void visit(IntObj node){
	System.out.println(f()+node.type+":"+node.getInt());
	return null;
    }
    public Void visit(StrObj node){
	System.out.println(f()+node.type+":"+node.getStr());
	return null;
    }
    public Void visit(DoubleObj node){
	System.out.println(f()+node.type+":"+node.getDouble());
	return null;
    }
    public Void visit(LPVariable node){
	System.out.println(f()+node.type+":"+node.getName());
	return null;
    }
    public Void visit(Identifier node){
	System.out.println(f()+node.type+":"+node.getName());
	return null;
    }
    public Void visit(Program node){
	System.out.println(f()+"program");
	depth++;
	for(Obj child:node){
	    child.accept(this);
	}
	depth--;
	return null;
    }
    public Void visit(Unary node){
	System.out.println(f()+node.type+":"+node.getName());
	depth++;
	node.child(0).accept(this);
	depth--;
	return null;
    }
    public Void visit(BinOp node){
	System.out.println(f()+node.type+":"+node.getName());
	depth++;
	for(Obj child:node){
	    child.accept(this);
	}
	depth--;
	return null;
    }
    public Void visit(IfStmt node){
	System.out.println(f()+node.type+":");
	depth++;
	System.out.println(f()+"cond:");
	node.getCond().accept(this);
	System.out.println(f()+"then:");
	node.getThen().accept(this);
	if(node.hasElse()){
	    System.out.println(f()+"else:");
	    node.getElse().accept(this);
	}
	depth--;
	return null;
    }
    public Void visit(WhileStmt node){
	System.out.println(f()+node.type+":");
	depth++;
	System.out.println(f()+"cond:");
	node.getCond().accept(this);
	System.out.println(f()+"stmt:");
	node.getStmt().accept(this);
	depth--;
	return null;
    }
    public Void visit(ForStmt node){
	System.out.println(f()+node.type+":");
	depth++;
	System.out.println(f()+"init:");
	node.getInit().accept(this);
	System.out.println(f()+"cond:");
	node.getCond().accept(this);
	System.out.println(f()+"step:");
	node.getStep().accept(this);
	System.out.println(f()+"stmt:");
	node.getStmt().accept(this);
	depth--;
	return null;
    }
    public Void visit(BlockStmt node){
	System.out.println(f()+node.type+":");
	depth++;
	for(Obj child:node){
	    child.accept(this);
	}
	depth--;
	return null;
    }
    public Void visit(LPDefStmt node){
	throw new LPGException("not lp");
    }
    public Void visit(Params node){
	throw new LPGException("not lp");
    }
    public Void visit(FuncDefStmt node){
	throw new LPGException("not lp");
    }
    public Void visit(FuncCall node){
	throw new LPGException("not lp");
    }
    public Void visit(Args node){
	throw new LPGException("not lp");
    }
    public Void visit(Function node){
	throw new LPGException("not lp");
    }
    public Void visit(Lambda node){
	throw new LPGException("not lp");
    }
    public Void visit(ObjList node){
	throw new LPGException("not lp");
    }
    public Void visit(ListRef node){
	throw new LPGException("not lp");
    }
    public Void visit(ForEachStmt node){
	throw new LPGException("not lp");
    }
    public Void visit(ForAll node){
	throw new LPGException("not lp");
    }
    public Void visit(Exists node){
	throw new LPGException("not lp");
    }
    public Void visit(Cond node){
    	throw new LPGException("");
    }
}
