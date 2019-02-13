package asteroids.Expression.Double;

import asteroids.Expression.Expression;
import asteroids.part3.programs.SourceLocation;

public class GetDirectionSelf extends Expression {

	public GetDirectionSelf(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Double getValue() {
		return this.getProgram().getProgramShip().getOrientation();
	}

	@Override
	public String toString() {
		return"self orientation";
	}

}
