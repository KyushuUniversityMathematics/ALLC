import java.util.*;
public class ForEachStmt extends ListObj{
    public ForEachStmt(Obj index,Obj list,Obj stmt){
	type="foreachstmt";
	children=new ArrayList<Obj>();
	children.add(index);
	children.add(list);
	children.add(stmt);
    }
    public Obj getIndex(){
	return child(0);
    }
    public Obj getList(){
	return child(1);
    }
    public Obj getStmt(){
	return child(2);
    }
    @Override
    public <T> T accept(ASTVisitor<T> visitor){
	return visitor.visit(this);
    }
}
