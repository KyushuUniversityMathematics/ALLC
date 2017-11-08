import java.util.*;
public abstract class Obj implements Iterable<Obj>{
    protected String type;
    protected String str,name;
    protected int ivalue;
    protected double dvalue;
    protected Environment env;
    //private Env env;

    public abstract Obj child(int i);
    public abstract int numChildren();
    public abstract Iterator<Obj> children();
    
    public Environment getEnv(){
	throw new LPGException("do not have environment");
    }
    public boolean isLocal(){
	throw new LPGException("not identifier");
    }
    public boolean isType(String s){
	return type.equals(s);
    }
    public boolean isName(String s){
	return name!=null&&name.equals(s);
    }
    public boolean isNumber(){
	return false;
    }
    public boolean isLp(){
	return false;
    }
    public String getType(){
	return type;
    }
    public String getStr(){
	throw new LPGException("not str");
    }
    public String getName(){
	throw new LPGException("not name");
    }
    public int getInt(){
	throw new LPGException("not int");
    }
    public double getDouble(){
	throw new LPGException("not double");
    }
    public Obj set(int i,Obj o){
	throw new LPGException("not list");
    }
    /*public Env getEnv(){
	throw new LPGException("not env");
    }
    */

    public Iterator<Obj> iterator(){
	return children();
    }
    public abstract <T> T accept(ASTVisitor<T> visitor);
}
