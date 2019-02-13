package asteroids.Statement;

import asteroids.Expression.DoubleReturn;
import asteroids.Expression.Expression;
import asteroids.Expression.UnaryExpression;
import asteroids.Function.Function;
import asteroids.part3.programs.SourceLocation;

public class Turn<E extends Expression & DoubleReturn> extends Statement implements UnaryExpression<E>{

	public Turn(E angle, SourceLocation sourceLocation) {
		super(sourceLocation);
		if (angle == null)
			throw new IllegalArgumentException();
		this.expression =  angle;
		this.setChildrenParent();
		}
	
	private E expression;

	@Override
	public E getExpression() {
		return expression;
	}

	@Override
	public void execute() {
		if (this.isExecutable()) {
			if ((double)this.getExpression().getValue() >= 2*Math.PI)
				throw new IllegalArgumentException();
			this.getProgram().getProgramShip().turn((double)this.getExpression().getValue());
			this.toggleAbstractBoolean();
			this.getProgram().decrementTimeLeftToExecute(0.2);
		}
	}
	
	@Override
	public void setFunction(Function func) {
		throw new IllegalArgumentException();
	}
	
	@Override
	public void setChildrenParent() {
		this.getExpression().setParent(this);
		
	}

	
}
