import java.util.*;
public class LPDefStmt extends ListObj{
    public LPDefStmt(String ctype,String name){
	type="lpdef";
	LPVariable lv=new LPVariable(ctype,name);
	children=new ArrayList<Obj>();
	children.add(lv);
    }
    public Obj getChild(){
	return child(0);
    }
    @Override
    public <T> T accept(ASTVisitor<T> visitor){
	return visitor.visit(this);
    }
}
