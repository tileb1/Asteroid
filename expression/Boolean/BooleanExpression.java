package asteroids.Expression.Boolean;

import asteroids.Expression.BooleanReturn;
import asteroids.Expression.Expression;
import asteroids.part3.programs.SourceLocation;

 public abstract class BooleanExpression extends Expression implements BooleanReturn {

	public BooleanExpression(SourceLocation sourceLocation) {
		super(sourceLocation);	
	}

}
