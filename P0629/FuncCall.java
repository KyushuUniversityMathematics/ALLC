import java.util.*;
public class FuncCall extends ListObj{
    public FuncCall(Obj expr,Obj args){
	type="funccall";
	children=new ArrayList<Obj>();
	children.add(expr);
	children.add(args);
    }
    public Obj getExpr(){
	return child(0);
    }
    public Obj getArgs(){
	return child(1);
    }
    @Override
    public <T> T accept(ASTVisitor<T> visitor){
	return visitor.visit(this);
    }
}
