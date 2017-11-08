import java.util.*;
public class BlockStmt extends ListObj{
    public BlockStmt(List<Obj> list){
	type="blockstmt";
	children=list;
    }
    @Override
    public <T> T accept(ASTVisitor<T> visitor){
	return visitor.visit(this);
    }
}
