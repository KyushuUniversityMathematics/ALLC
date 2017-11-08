import java.util.*;
public class LPVariable extends LeafObj{
    public LPVariable(String type,String name){
	this.type=type;
	this.name=name;
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
	if(!o2.isLp())return false;
	return o2.isType(type)&&o2.isName(name);
    }
    public boolean isLp(){
	return true;
    }
    public String getName(){
	return name;
    }
    public String toString(){
	return "("+type+")"+name;
    }
    public <T> T accept(ASTVisitor<T> visitor){
	return visitor.visit(this);
    }
}
