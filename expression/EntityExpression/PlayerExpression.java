package asteroids.Expression.EntityExpression;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;

public class PlayerExpression extends EntityExpression {

	public PlayerExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	@Override
	public Entity getValue() {
		return this.getProgram().getProgramShip();
	}
	
	public String toString() {
		return "self";
	}

}
