import java.util.*;
public class Program extends ListObj{
    private String mize;
    private List<String> librarys;
    public Program(String s,List<Obj> list){
	children=list;
	mize=s;
	type="program";
    }
    public Program(List<String> librarys,String s,List<Obj> list){
	this.librarys=librarys;
	this.mize=s;
	children=list;
	type="program";
    }
    public List<String> getLibs(){
	return librarys;
    }
    public String getMize(){
	return mize;
    }
    @Override
    public String toString(){
	return type;
    }
    @Override
    public <T> T accept(ASTVisitor<T> visitor){
	return visitor.visit(this);
    }
}
