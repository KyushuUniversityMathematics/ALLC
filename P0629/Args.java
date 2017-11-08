import java.util.*;
public class Args extends ListObj{
    public Args(List<Obj> args){
	type="args";
	children=new ArrayList<Obj>();
	children.addAll(args);
    }
    @Override
    public <T> T accept(ASTVisitor<T> visitor){
	return visitor.visit(this);
    }
}
