import java.util.*;
public class BinOp extends ListObj{
    private boolean hashed=false;
    private int code=0;
    public BinOp(String name,List<Obj> list){
	type="binop";
	children=list;
	this.name=name;
    }
    public BinOp(String name,Obj... objs){
	type="binop";
	children=Arrays.asList(objs);
	this.name=name;
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
	if(!o2.isName(name))return false;
	if(numChildren()!=o2.numChildren())return false;
	int n=numChildren();
	for(int i=0;i<n;i++){
	    if(!child(i).equals(o2.child(i)))return false;
	}
	return true;
    }

    public Obj getLeft(){
	return child(0);
    }
    public Obj getRight(){
	return child(1);
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
