import java.util.*;
public class Debug2 implements ASTVisitor<String>{
    private static Debug2 singleton=new Debug2();
    private Set<String> vars;
    private Set<String> freeVars;
    private Set<String> generalVars;
    private Set<String> generalFreeVars;
    private Set<String> binaryVars;
    private Debug2(){}
    public static void setVars(Set<String> vars,Set<String> freeVars,Set<String> generalVars,
    Set<String> generalFreeVars,Set<String> binaryVars){
	singleton.vars=vars;
	singleton.freeVars=freeVars;
	singleton.generalVars=generalVars;
	singleton.generalFreeVars=generalFreeVars;
	singleton.binaryVars=binaryVars;
    }
    public static Debug2 getInstance(){
	return singleton;
    }
    public String visit(IntObj node){
	int i=node.getInt();
	if(i<0){
	    return "- "+String.valueOf(-i);
	}else{
	    return "+ "+String.valueOf(i);
	}
	//return String.valueOf(node.getInt());
    }
    public String visit(StrObj node){
	throw new LPGException("not lp");
    }
    public String visit(DoubleObj node){
	double d=node.getDouble();
	if(d<0){
	    return "- "+String.valueOf(-d);
	}else{
	    return "+ "+String.valueOf(d);
	}
	//return String.valueOf(node.getDouble());
    }
    public String visit(LPVariable node){
	if(node.isType("variable")){
	    vars.add(node.getName());
	}else if(node.isType("free")){
	    freeVars.add(node.getName());
	}else if(node.isType("general")){
	    generalVars.add(node.getName());
	}else if(node.isType("generalfree")){
	    generalFreeVars.add(node.getName());
	}else if(node.isType("binary")){
	    binaryVars.add(node.getName());
	}else{
	    throw new LPGException("not lp variable");
	}
	return node.getName();
    }
    public String visit(Identifier node){
	throw new LPGException(node.getName()+"not lp");
    }
    public String visit(Program node){
	throw new LPGException("not lp");
    }
    public String visit(Unary node){
	throw new LPGException("unary is found");
	//return "("+node.getName()+" "+node.getChild().accept(this)+")";
    }
    public String visit(BinOp node){
	String s="";
	if(node.isName("+")){
	    for(Obj child:node){
		s+=" "+child.accept(this);
	    }
	    return s;
	}else if(node.isName("*")){
	    return node.getLeft().accept(this)+" "+node.getRight().accept(this);
	}else if(node.isName("==")||node.isName("<=")||node.isName(">=")){
	    String op=node.getName();
	    if(op.equals("=="))op="=";
	    return node.getLeft().accept(this)+" "+op+" "+node.getRight().accept(this);
	}else{
	    throw new LPGException("binop is not found");
	}
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
