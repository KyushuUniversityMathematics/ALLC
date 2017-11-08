import java.util.*;
public class ForStmt extends ListObj{
    public ForStmt(Obj init,Obj cond,Obj step,Obj stmt){
	type="forstmt";
	children=new ArrayList<Obj>();
	children.add(init);
	children.add(cond);
	children.add(step);
	children.add(stmt);
    }
    public Obj getInit(){
	return children.get(0);
    }
    public Obj getCond(){
	return children.get(1);
    }
    public Obj getStep(){
	return children.get(2);
    }
    public Obj getStmt(){
	return children.get(3);
    }
    @Override
    public <T> T accept(ASTVisitor<T> visitor){
	return visitor.visit(this);
    }
}
