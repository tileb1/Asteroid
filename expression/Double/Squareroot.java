package asteroids.Expression.Double;

import asteroids.Expression.DoubleReturn;
import asteroids.Expression.Expression;
import asteroids.Expression.UnaryExpression;
import asteroids.Function.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.program.Program;

public class Squareroot<E extends Expression & DoubleReturn> 
	extends DoubleExpression implements UnaryExpression<E>{

	public Squareroot(E value, SourceLocation sourceLocation) {
		super(sourceLocation);
		if (value == null)
			throw new IllegalArgumentException();

		this.expression = value;
	}
	
	private E expression;
	
	@Override
	public Double getValue() {
		return Math.sqrt((double)this.expression.getValue());
	}
	
	@Override
	public E getExpression() {
		return expression;
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
	
	@Override
	public String toString() {
		return " sqrt(" + this.expression.toString() + ")";
	}


}
