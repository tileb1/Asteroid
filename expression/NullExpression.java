package asteroids.Expression;

import asteroids.part3.programs.SourceLocation;

public class NullExpression extends Expression implements DoubleReturn, BooleanReturn, EntityReturn {

	public NullExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Object getValue() {
		return null;
	}

	@Override
	public String toString() {
		return "Null";
	}
	
	@Override
	public  String theReturnType() {
		return "Null";
	}

}
