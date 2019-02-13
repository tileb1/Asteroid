package asteroids.Expression.EntityExpression;


import asteroids.Expression.EntityReturn;
import asteroids.Expression.Expression;
import asteroids.part3.programs.SourceLocation;

public abstract class EntityExpression extends Expression implements EntityReturn {
	public EntityExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
		
	}

	
}
