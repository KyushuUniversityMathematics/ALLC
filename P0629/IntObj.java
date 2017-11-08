import java.util.*;
public class IntObj extends LeafObj{
    private static IntObj one=new IntObj(1);
    private static IntObj zero=new IntObj(0);
    private static IntObj negative=new IntObj(-1);
    public IntObj(int ivalue){
	type="int";
	this.ivalue=ivalue;
    }
    @Override
    public int hashCode(){
	return Objects.hashCode(ivalue);
    }
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o){
	if(o==null)return false;
	if(!(o instanceof Obj))return false;
	Obj o2=(Obj) o;
	if(!o2.isType("int"))return false;
	return getInt()==o2.getInt();
    }

    public static IntObj getOne(){
	return one;
    }
    public static IntObj getZero(){
	return zero;
    }
    public static IntObj getNegative(){
	return negative;
    }
    public static boolean isFalse(Obj o){
	return o.isType("int")&&o.getInt()==0;
    }
    public static boolean isNotFalse(Obj o){
	return !isFalse(o);
    }
    public static boolean isTrue(Obj o){
	return o.isType("int")&&o.getInt()==1;
    }
    @Override
    public boolean isNumber(){
	return true;
    }
    @Override
    public int getInt(){
	return ivalue;
    }
    @Override
    public double getDouble(){
	return (double)ivalue;
    }
    @Override
    public String toString(){
	return String.valueOf(ivalue);
    }
    @Override
    public <T> T accept(ASTVisitor<T> visitor){
	return visitor.visit(this);
    }
}
