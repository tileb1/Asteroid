package asteroids.Expression.Double;

import asteroids.Expression.DoubleReturn;
import asteroids.Expression.Expression;
import asteroids.part3.programs.SourceLocation;

public abstract class DoubleExpression extends Expression implements DoubleReturn {

	public DoubleExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

}
