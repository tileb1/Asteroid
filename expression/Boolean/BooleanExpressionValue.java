package asteroids.Expression.Boolean;

import asteroids.part3.programs.SourceLocation;

public class BooleanExpressionValue extends BooleanExpression {
	public BooleanExpressionValue(Boolean value, SourceLocation sourceLocation) {
		super(sourceLocation);
		if (value == null)
			throw new IllegalArgumentException();
		this.value = value;
	}
	
	private Boolean value;
	
	@Override
	public String toString() {
		return "Bool (" + this.getValue().toString() + ")";
	}

	@Override
	public Boolean getValue() {
		return value;
	}

}
