import java.util.*;
public class ForAll extends ListObj{
    public ForAll(Obj index,Obj list,Obj expr){
	type="forall";
	children=new ArrayList<Obj>();
	children.add(index);
	children.add(list);
	children.add(expr);
    }
    public Obj getIndex(){
	return child(0);
    }
    public Obj getList(){
	return child(1);
    }
    public Obj getExpr(){
	return child(2);
    }
    @Override
    public <T> T accept(ASTVisitor<T> visitor){
	return visitor.visit(this);
    }
}
