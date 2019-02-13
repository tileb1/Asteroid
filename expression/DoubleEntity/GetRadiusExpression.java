package asteroids.Expression.DoubleEntity;

import asteroids.Expression.EntityReturn;
import asteroids.Expression.Expression;
import asteroids.part3.programs.SourceLocation;

public class GetRadiusExpression<E extends Expression & EntityReturn> extends EntityCharacteristicExpression<E> {

	public GetRadiusExpression(E expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	@Override
	public Double getValue() {
		return this.getEntity().getRadius();
	}

	@Override
	public String toString() {
		return "getRadius(" + this.getEntity().toString() + ")";
	}

}
