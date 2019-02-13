package asteroids.Expression.Boolean;

import asteroids.Expression.BinaryExpression;
import asteroids.Expression.DoubleReturn;
import asteroids.Expression.Expression;
import asteroids.Function.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.program.Program;

public class LessThan<L extends Expression & DoubleReturn, R extends Expression & DoubleReturn> 
	extends BooleanExpression implements BinaryExpression<L,R> {

	public LessThan(L left, R right,SourceLocation sourceLocation) throws IllegalArgumentException {
		super(sourceLocation);
		if (left == null || right == null)
			throw new IllegalArgumentException();

		this.left =  left;
		this.right =  right;
	}
	
	private L left;
	private R right;
	@Override
	public Boolean getValue() {
		boolean a = ((double) left.getValue()) < ((double) right.getValue());
		return a;
	}
	@Override
	public Expression getLeft() {
		return left;
	}
	@Override
	public Expression getRight() {
		return right;
	}
	
	@Override
	public String toString() {
		return "lessThan (" + this.left.toString() + " < " + this.right.toString() + ")";
	}
	
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		left.setProgram(program);
		right.setProgram(program);
	}
	
	@Override
	public void setFunction(Function func) {
		super.setFunction(func);
		right.setFunction(func);
		left.setFunction(func);
	}
	

}
