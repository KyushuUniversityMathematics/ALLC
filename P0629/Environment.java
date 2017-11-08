import java.util.*;
public class Environment{
    private Environment outer;
    private Map<String,Obj> objs;
    public Environment(){
	this(null);
    }
    public Environment(Environment env){
	objs=new HashMap<String,Obj>();
	outer=env;
    }
    public Obj get(String name){
	Obj v=objs.get(name);
	if(v==null&&outer!=null){
	    return outer.get(name);
	}else{
	    return v;
	}
    }
    public void putNew(String name,Obj obj){
	objs.put(name,obj);
    }
    public void put(String name,Obj obj){
	Environment env=where(name);
	if(env==null){
	    env=this;
	}
	env.putNew(name,obj);
    }
    public Environment where(String name){
	if(objs.get(name)!=null){
	    return this;
	}else if(outer==null){
	    return null;
	}else{
	    return outer.where(name);
	}
    }
}
