import java.util.*;
public class DoubleObj extends LeafObj{
    public DoubleObj(double dvalue){
	type="double";
	this.dvalue=dvalue;
    }
    @Override
    public int hashCode(){
	return Objects.hashCode(dvalue);
    }
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o){
	if(o==null)return false;
	if(!(o instanceof Obj))return false;
	Obj o2=(Obj)o;
	if(!o2.isType("double"))return false;
	return getDouble()==o2.getDouble();
    }

    @Override
    public boolean isNumber(){
	return true;
    }
    @Override
    public double getDouble(){
	return dvalue;
    }
    @Override
    public String toString(){
	return String.valueOf(dvalue);
    }
    @Override
    public <T> T accept(ASTVisitor<T> visitor){
	return visitor.visit(this);
    }
}
