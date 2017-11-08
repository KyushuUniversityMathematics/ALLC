import java.util.*;
public abstract class LeafObj extends Obj{
    private static ArrayList<Obj> empty=new ArrayList<Obj>();
    @Override
    public Obj child(int i){
	throw new IndexOutOfBoundsException();
    }
    @Override
    public int numChildren(){
	return 0;
    }
    @Override
    public Iterator<Obj> children(){
	return empty.iterator();
    }
    /*
    @Override
    public <T> T accept(ASTVisitor<T> visitor){
	return visitor.visit(this);
    }
    */
}
