package asteroids.Expression.Double;

import asteroids.Expression.DoubleReturn;
import asteroids.Expression.Expression;
import asteroids.Expression.UnaryExpression;
import asteroids.Function.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.program.Program;


public class ChangeSign<E extends Expression & DoubleReturn> 
	extends DoubleExpression implements UnaryExpression<E> {

	public ChangeSign(E value, SourceLocation sourceLocation) {
		super(sourceLocation);
		if (value == null)
			throw new IllegalArgumentException();
		this.expression = value;
	}
	private E expression;
	
	@Override
	public Double getValue() {
		return -(double)this.expression.getValue();
	}
	
	@Override
	public E getExpression() {
		return expression;
	}
	
	@Override
	public String toString() {
		return "signChange(" +  " - " + this.expression.toString() + ")";
	}

	
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		expression.setProgram(program);
	}
	
	@Override
	public void setFunction(Function func) {
		super.setFunction(func);
		expression.setFunction(func);
	}
}
