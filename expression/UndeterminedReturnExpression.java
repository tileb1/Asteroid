package asteroids.Expression;

import asteroids.part3.programs.SourceLocation;

public abstract class UndeterminedReturnExpression extends Expression implements
			DoubleReturn, BooleanReturn, EntityReturn {

	public UndeterminedReturnExpression(SourceLocation sourceLocation, String name) {
		super(sourceLocation);
		this.name = name;
	}
	
	private String name;
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public  String theReturnType() {
		return "Object";
	}

}
