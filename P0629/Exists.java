import java.util.*;
public class Exists extends ListObj{
    private boolean nested=false;
    public Exists(Obj index,Obj list,Obj expr){
	type="exists";
	children=new ArrayList<Obj>();
	children.add(index);
	children.add(list);
	children.add(expr);
    }
    public Exists(Obj index,Obj list,Obj expr,Obj option){
	type="exists";
	nested=true;
	children=new ArrayList<Obj>();
	children.add(index);
	children.add(list);
	children.add(expr);
	children.add(option);
    }
    public Exists(Obj index,Obj list,Obj expr,boolean nested){
	type="exists";
	this.nested=nested;
	children=new ArrayList<Obj>();
	children.add(index);
	children.add(list);
	children.add(expr);
    }
    public boolean isNested(){
	return nested;
    }
    public boolean hasOption(){
	return numChildren()>=4;
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
    public Obj getOption(){
	return child(3);
    }
    @Override
    public <T> T accept(ASTVisitor<T> visitor){
	return visitor.visit(this);
    }
}
