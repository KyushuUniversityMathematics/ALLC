import java.util.*;
public class Params extends ListObj{
    public Params(List<Obj> params){
	type="params";
	children=new ArrayList<Obj>();
	children.addAll(params);
    }
    @Override
    public <T> T accept(ASTVisitor<T> visitor){
	return visitor.visit(this);
    }
}
