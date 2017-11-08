import java.util.*;
public class WhileStmt extends ListObj{
    public WhileStmt(Obj cond,Obj stmt){
	type="whilestmt";
	children=new ArrayList<Obj>();
	children.add(cond);
	children.add(stmt);
    }
    public Obj getCond(){
	return children.get(0);
    }
    public Obj getStmt(){
	return children.get(1);
    }
    @Override
    public <T> T accept(ASTVisitor<T> visitor){
	return visitor.visit(this);
    }
}
