package asteroids.Expression.DoubleEntity;

import asteroids.Expression.EntityReturn;
import asteroids.Expression.Expression;
import asteroids.part3.programs.SourceLocation;

public class GetYExpression<E extends Expression & EntityReturn> extends EntityCharacteristicExpression<E> {

	public GetYExpression(E expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	@Override
	public Double getValue() {
		return this.getEntity().getPosition().getY();
	}

	@Override
	public String toString() {
		return "getPosY(" + this.getEntity().toString() + ")";
	}
	
}
