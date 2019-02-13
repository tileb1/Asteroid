package asteroids.Expression.DoubleEntity;

import asteroids.Expression.EntityReturn;
import asteroids.Expression.Expression;
import asteroids.part3.programs.SourceLocation;

public class GetVyExpression<E extends Expression & EntityReturn> extends EntityCharacteristicExpression<E> {

	public GetVyExpression(E expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	@Override
	public Double getValue() {
		return this.getEntity().getSpeed().getY();
	}

	@Override
	public String toString() {
		return "getSpeedY(" + this.getEntity().toString() + ")";
	}

}
