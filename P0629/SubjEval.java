public class SubjEval{
    private static PreRem pr=PreRem.getInstance();
    private static AndOrFlat aof=AndOrFlat.getInstance();
    private static TopEval te=TopEval.getInstance();
    public static void Eval(Obj o){
	o.accept(pr).accept(aof).accept(te);
    }
}
