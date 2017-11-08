import java.util.*;
public class Unary extends ListObj{
    public Unary(String name,Obj obj){
	type="unary";
	this.name=name;
	this.children=new ArrayList<Obj>();
	children.add(obj);
    }
    @Override
    public int hashCode(){
	return Objects.hash(type,name);
    }
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o){
	if(o==null)return false;
	if(!(o instanceof Obj))return false;
	Obj o2=(Obj)o;
	if(!o2.isType(type))return false;
	return o2.isName(name);
    }
    @Override
    public String getName(){
	return name;
    }

    public Obj getChild(){
	return children.get(0);
    }
    @Override
    public <T> T accept(ASTVisitor<T> visitor){
	return visitor.visit(this);
    }
}
