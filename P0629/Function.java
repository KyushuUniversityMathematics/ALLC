import java.util.*;
public class Function extends ListObj{
    public Function(Obj params,Obj block,Environment env){
	this.env=env;
	type="function";
	name="non";
	children=new ArrayList<Obj>();
	children.add(params);
	children.add(block);
    }
    @Override
    public Environment getEnv(){
	return env;
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
