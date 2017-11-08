import java.util.*;
public class PlEval2 implements ASTVisitor<Obj>{
    private static PlEval2 singleton=new PlEval2();
    private PlEval2(){}
    public static  PlEval2 getInstance(){
	return singleton;
    }
    /*public Obj negate(Obj node){
	if(node.isType("unary")&&node.isName("-")){
	    return node.child(0);
	}else{
	    return new Unary("-",node);
	}
    }*/
    private String isInt="@@@int";
    private Map<String,Obj> terms;
    private Map<String,Obj> lps;
    /*public PlEval2(){
	terms=new HashMap<String,Obj>();
	lps=new HashMap<String,Obj>();
	terms.put(isInt,new IntObj(0));
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
	//return node;
	//return new Unary(node.getName(),node.getChild().accept(this));
    }
    public Obj visit(BinOp node){
	if(!node.isName("+")){
	    throw new LPGException("not compare in PlEval");
	    //return node;
	}
	terms=new HashMap<String,Obj>();
	lps=new HashMap<String,Obj>();
	terms.put(isInt,new IntObj(0));

	for(Obj child:node){
	    if(child.isLp()){
	    	//throw new LPGException("");
		child=new BinOp("*",new IntObj(1),child);
	    }
	    if(child.isType("binop")){
		if(!child.isName("*")){
		    throw new LPGException("???!!!");
		}
		String s=child.child(1).getName();
		if(terms.get(s)==null){
		    terms.put(s,new IntObj(0));
		    lps.put(s,child.child(1));
		}
		terms.put(s,NumCalc.bin("+",terms.get(s),child.child(0)));
	    }else if(child.isNumber()){
		terms.put(isInt,NumCalc.bin("+",terms.get(isInt),child));
	    }else{
		throw new LPGException("???? in PlEval2"+child.getType());
	    }
	}
	List<Obj> ret=new ArrayList<Obj>();
	Obj intCof=null;
	for(String s:terms.keySet()){
	    if(s.equals(isInt)){
		intCof=terms.get(s);
		continue;
	    }
	    Obj o=terms.get(s);
	    Obj v=lps.get(s);
	    /*if(o.isType("int")&&o.getInt()==1){
		ret.add(v);
	    }else if(o.isType("int")&&o.getInt()==0){
		continue;
	    }else{*/
		ret.add(new BinOp("*",o,v));
	    /*}*/
	    //if(v==null)System.out.println("v");
	    //System.out.println(s);
	    //System.out.println(o);
	    //if(v.getName()==null)System.out.println("non name");
	    //System.out.println(o+" "+v.getName());
	}
	ret.add(intCof);
	return new BinOp("+",ret);
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
