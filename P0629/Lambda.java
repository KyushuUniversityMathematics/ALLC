import java.util.*;
public class Lambda extends ListObj{
    public Lambda(Obj params,Obj block){
	type="lambda";
	children=new ArrayList<Obj>();
	children.add(params);
	children.add(block);
    }
    public Obj getParams(){
	return child(0);
    }
    public Obj getBlock(){
	return child(1);
    }
    @Override
    public <T> T accept(ASTVisitor<T> visitor){
	return visitor.visit(this);
    }
}
