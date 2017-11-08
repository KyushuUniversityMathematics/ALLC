import java.util.*;
public class ExprEval{
	//private Environment grobal=null;
	//private static ExprTrans et=new ExprTrans(new DoubleObj(1000),new DoubleObj(0.01));
    private static RemNeg rn=RemNeg.getInstance();
    private static MulDist md=MulDist.getInstance();
    private static ExprSort es=ExprSort.getInstance();
    private static MulEval me=MulEval.getInstance();
    private static PlEval2 pe=PlEval2.getInstance();
    private ExprEval(){}
    public static Obj Eval(Obj expr){
	if(!expr.isType("binop")){
	    throw new LPGException("aaaaaa");
	}
	if(!(expr.isName("==")||expr.isName("<=")||expr.isName(">="))){
	    throw new LPGException("aaaaaa");
	}
	Obj left=expr.child(0);
	Obj right=expr.child(1);
	left=new BinOp("-",left,right);
	//System.out.println(left.accept(new Debug()));
	left=left.accept(rn);
	//System.out.println(left.accept(new Debug()));
	left=left.accept(md);
	//System.out.println(left.accept(new Debug()));
	left=left.accept(es);
	//System.out.println(left.accept(new Debug()));
	left=left.accept(me);
	//System.out.println(left.accept(new Debug()));
	left=left.accept(pe);
	//	System.out.println(left.accept(new Debug()));
	if(left.numChildren()<=1||!left.isType("binop")||!left.isName("+")){
	    throw new LPGException("no lp variable");
	}
	Obj nr=left.child(left.numChildren()-1);
	nr=NumCalc.bin("-",new IntObj(0),nr);
	List<Obj> nl=new ArrayList<Obj>();
	for(int i=0;i<left.numChildren()-1;i++){
	    nl.add(left.child(i));
	}
	return new BinOp(expr.getName(),new BinOp("+",nl),nr);
    }
}
