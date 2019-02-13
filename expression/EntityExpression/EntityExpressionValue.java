package asteroids.Expression.EntityExpression;

import java.util.Iterator;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;

public class EntityExpressionValue extends EntityExpression{
	public EntityExpressionValue(SourceLocation sourceLocation) {
		super(sourceLocation);
		
	}

	@Override
	public Entity getValue() {
		Iterator<Entity> iter = this.getProgram().getProgramShip().getWorld().getEntitySet().iterator();
		if (iter.hasNext())
			return iter.next();
		else
			return null;
	}
	
	@Override
	public String toString() {
		return "random entity";
	}


}
