public class NumCalc{
    static Obj bin(String op,Obj left,Obj right){
	if(left.isType("double")&&right.isType("int")){
	    right=new DoubleObj((double)right.getInt());
	}
	if(left.isType("int")&&right.isType("double")){
	    left=new DoubleObj((double)left.getInt());
	}
	if(left.isType("double")&&right.isType("double")){
	    if(op.equals("+")){
		return new DoubleObj(left.getDouble()+right.getDouble());
	    }else
	    if(op.equals("-")){
		return new DoubleObj(left.getDouble()-right.getDouble());
	    }else
	    if(op.equals("*")){
		return new DoubleObj(left.getDouble()*right.getDouble());
	    }else
	    if(op.equals("/")){
		return new DoubleObj(left.getDouble()/right.getDouble());
	    }else
	    if(op.equals("==")){
		return new IntObj(left.getDouble()==right.getDouble()?1:0);
	    }else
	    if(op.equals("!=")){
		return new IntObj(left.getDouble()!=right.getDouble()?1:0);
	    }
	    if(op.equals("<=")){
		return new IntObj(left.getDouble()<=right.getDouble()?1:0);
	    }else
	    if(op.equals(">=")){
		return new IntObj(left.getDouble()>=right.getDouble()?1:0);
	    }else
	    if(op.equals("<")){
		return new IntObj(left.getDouble()<right.getDouble()?1:0);
	    }else
	    if(op.equals(">")){
		return new IntObj(left.getDouble()>right.getDouble()?1:0);
	    }else
	    throw new LPGException("operatiorn "+op+" is not found");
	}else if(left.isType("int")&&right.isType("int")){
	    if(op.equals("+")){
		return new IntObj(left.getInt()+right.getInt());
	    }else
	    if(op.equals("-")){
		return new IntObj(left.getInt()-right.getInt());
	    }else
	    if(op.equals("*")){
		return new IntObj(left.getInt()*right.getInt());
	    }else
	    if(op.equals("//")){
		return new IntObj(left.getInt()/right.getInt());
	    }else
	    if(op.equals("/")){
		//6/24
		return new DoubleObj(left.getDouble()/right.getDouble());
	    }
	    if(op.equals("%")){
		return new IntObj(left.getInt()%right.getInt());
	    }else
	    if(op.equals("==")){
		return new IntObj(left.getInt()==right.getInt()?1:0);
	    }else
	    if(op.equals("!=")){
		return new IntObj(left.getInt()!=right.getInt()?1:0);
	    }else
	    if(op.equals("<=")){
		return new IntObj(left.getInt()<=right.getInt()?1:0);
	    }else
	    if(op.equals(">=")){
		return new IntObj(left.getInt()>=right.getInt()?1:0);
	    }else
	    if(op.equals("<")){
		return new IntObj(left.getInt()<right.getInt()?1:0);
	    }else
	    if(op.equals(">")){
		return new IntObj(left.getInt()>right.getInt()?1:0);
	    }else
	    if(op.equals("&&")){
		return new IntObj(left.getInt()!=0&&right.getInt()!=0?1:0);
	    }else
	    if(op.equals("||")){
		return new IntObj(left.getInt()!=0||right.getInt()!=0?1:0);
	    }
	    throw new LPGException("operation "+op+" is not found");
	}
	throw new LPGException("not num operation");
    }
    static Obj unary(String op,Obj child){
	if(child.isType("double")){
	    if(op.equals("-")){
		return new DoubleObj(-child.getDouble());
	    }else if(op.equals("+")){
		return new DoubleObj(child.getDouble());
	    }
	    throw new LPGException("operation "+op+" is not found");
	}else if(child.isType("int")){
	    if(op.equals("-")){
		return new IntObj(-child.getInt());
	    }else if(op.equals("+")){
		return new IntObj(child.getInt());
	    }else if(op.equals("!")){
		return new IntObj(child.getInt()==0?1:0);
	    }
	    throw new LPGException("operation "+op+" is not found");
	}
	throw new LPGException("not num operation");
    }
}

