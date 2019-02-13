package asteroids.Expression.DoubleEntity;

import asteroids.Expression.EntityReturn;
import asteroids.Expression.Expression;
import asteroids.part3.programs.SourceLocation;

public class GetXExpression<E extends Expression & EntityReturn> extends EntityCharacteristicExpression<E> {

	public GetXExpression(E expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	@Override
	public Double getValue() {
		return this.getEntity().getPosition().getX();
	}

	@Override
	public String toString() {
		return "getPosX(" + this.getEntity().toString() + ")";
	}
	
}
