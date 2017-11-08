import java.util.*;
public class Identifier extends LeafObj{
    private boolean local;
    public Identifier(String name){
	this(name,false);
    }
    public Identifier(String name,boolean local){
	type="identifier";
	this.name=name;
	this.local=local;
    }
    @Override
    public boolean isLocal(){
	return local;
    }
    @Override
    public String getName(){
	return name;
    }
    @Override
    public String toString(){
	return "("+type+")"+name;
    }
    @Override
    public <T> T accept(ASTVisitor<T> visitor){
	return visitor.visit(this);
    }
}
