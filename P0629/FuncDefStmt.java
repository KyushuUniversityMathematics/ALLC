import java.util.*;
public class FuncDefStmt extends ListObj{
    public FuncDefStmt(String name,Obj params,Obj block){
	type="funcdef";
	this.name=name;
	children=new ArrayList<Obj>();
	children.add(params);
	children.add(block);
    }
    public String getName(){
	return name;
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
