import java.util.*;
public class ObjList extends ListObj{
    public ObjList(List<Obj> list){
	type="objlist";
	children=list;
    }
    public Obj get(int i){
	return child(i);
    }
    public int size(){
	return numChildren();
    }
    @Override
    public Obj set(int i,Obj o){
	children.set(i,o);
	return o;
    }
    @Override
    public <T> T accept(ASTVisitor<T> visitor){
	return visitor.visit(this);
    }
}
