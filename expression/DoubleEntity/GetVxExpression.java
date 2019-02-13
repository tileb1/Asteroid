package asteroids.Expression.DoubleEntity;

import asteroids.Expression.EntityReturn;
import asteroids.Expression.Expression;
import asteroids.part3.programs.SourceLocation;

public class GetVxExpression<E extends Expression & EntityReturn> extends EntityCharacteristicExpression<E> {

	public GetVxExpression(E expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	@Override
	public Double getValue() {
		return this.getEntity().getSpeed().getX();
	}

	@Override
	public String toString() {
		return "getSpeedX(" + this.getEntity().toString() + ")";
	}

}
