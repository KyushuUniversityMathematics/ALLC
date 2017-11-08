import java.util.*;
public class IfStmt extends ListObj{
    public IfStmt(Obj cond,Obj then,Obj els){
	type="ifstmt";
	children=new ArrayList<Obj>();
	children.add(cond);
	children.add(then);
	children.add(els);
    }
    public IfStmt(Obj cond,Obj then){
	type="ifstmt";
	children=new ArrayList<Obj>();
	children.add(cond);
	children.add(then);
    }
    public boolean hasElse(){
	return numChildren()==3;
    }
    public Obj getCond(){
	return children.get(0);
    }
    public Obj getThen(){
	return children.get(1);
    }
    public Obj getElse(){
	return children.get(2);
    }
    @Override
    public <T> T accept(ASTVisitor<T> visitor){
	return visitor.visit(this);
    }
}
