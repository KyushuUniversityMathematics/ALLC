import java.util.*;
public class StrObj extends LeafObj{
    public StrObj(String str){
	type="string";
	this.str=str;
    }
    @Override
    public String getStr(){
	return str;
    }
    @Override
    public String toString(){
	return str;
    }
    @Override
    public <T> T accept(ASTVisitor<T> visitor){
	return visitor.visit(this);
    }
}
