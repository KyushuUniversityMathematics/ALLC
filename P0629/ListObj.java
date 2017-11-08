import java.util.*;
public abstract class ListObj extends Obj{
    protected List<Obj> children;
    /*public ListObj(List<Obj> list){
	children=list;
    }*/
    @Override
    public Obj child(int i){
	return children.get(i);
    }
    @Override
    public int numChildren(){
	return children.size();
    }
    @Override
    public Iterator<Obj> children(){
	return children.iterator();
    }
    /*@Override
    public <T> T accept(ASTVisitor<T> visitor){
	return visitor.visit(this);
    }*/
}
