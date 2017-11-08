import java.util.*;
public class ListRef extends ListObj{
    public ListRef(Obj expr,Obj index){
	type="listref";
	children=new ArrayList<Obj>();
	children.add(expr);
	children.add(index);
    }
    public Obj getExpr(){
	return child(0);
    }
    public Obj getIndex(){
	return child(1);
    }
    @Override
    public <T> T accept(ASTVisitor<T> visitor){
	return visitor.visit(this);
    }
}
