package asteroids.Expression.Double;

import asteroids.part3.programs.SourceLocation;

public class DoubleExpressionValue extends DoubleExpression {

	public DoubleExpressionValue(double value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.value = value;
	}
	

	private Double value;

	@Override
	public Double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "double("+this.value.toString()+")";
	}
}
