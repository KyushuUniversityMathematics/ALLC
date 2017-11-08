import java.util.*;
public interface ASTVisitor<T>{
    public T visit(IntObj node);
    public T visit(StrObj node);
    public T visit(DoubleObj node);
    public T visit(LPVariable node);
    public T visit(Identifier node);
    public T visit(Program node);
    public T visit(Unary node);
    public T visit(BinOp node);
    public T visit(IfStmt node);
    public T visit(WhileStmt node);
    public T visit(ForStmt node);
    public T visit(BlockStmt node);
    public T visit(LPDefStmt node);
    public T visit(Params node);
    public T visit(FuncDefStmt node);
    public T visit(FuncCall node);
    public T visit(Args node);
    public T visit(Function node);
    public T visit(Lambda node);
    public T visit(ObjList node);
    public T visit(ListRef node);
    public T visit(ForEachStmt node);
    public T visit(ForAll node);
    public T visit(Exists node);
    public T visit(Cond node);
}
