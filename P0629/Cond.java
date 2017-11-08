import java.util.*;
public class Cond extends ListObj{
    private boolean hashed=false;
    private int code=0;
    public Cond(Obj expr,Obj then,Obj elze){
	type="condop";
	name="condop";
	children=new ArrayList<Obj>();
	children.add(expr);
	children.add(then);
	children.add(elze);
    }
    public Obj getExpr(){
	return child(0);
    }
    public Obj getThen(){
	return child(1);
    }
    public Obj getElse(){
	return child(2);
    }

    @Override
    public int hashCode(){
	if(hashed)return code;
	int result=17;
	result*=31;
	result+=name.hashCode();
	for(Obj c:children){
	    result*=31;
	    result+=c.hashCode();
	}
	return code=result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o){
	if(o==null)return false;
	if(!(o instanceof Obj))return false;
	Obj o2=(Obj)o;
	if(!o2.isType(type))return false;
	//if(!o2.isName(name))return false;
	//if(numChildren()!=o2.numChildren())return false;
	//int n=numChildren();
	int n=3;
	for(int i=0;i<n;i++){
	    if(!child(i).equals(o2.child(i)))return false;
	}
	return true;
    }

    @Override
    public String getName(){
	return name;
    }
    @Override
    public <T> T accept(ASTVisitor<T> visitor){
	return visitor.visit(this);
    }
}
