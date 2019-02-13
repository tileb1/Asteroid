package asteroids.Expression.Boolean;

import asteroids.Expression.BinaryExpression;
import asteroids.Expression.Expression;
import asteroids.Function.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.program.Program;

public class EqualsTo extends BooleanExpression implements BinaryExpression<Expression,Expression>{

	public EqualsTo(Expression left, Expression right, SourceLocation sourceLocation) {
		super(sourceLocation);
		if (left == null || right == null)
			throw new IllegalArgumentException();
		this.left = left;
		this.right = right;
	}
	
	private Expression left;
	private Expression right;

	@Override
	public Boolean getValue() {
		return left.getValue() == right.getValue();
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
		return "Equals (" + this.left.toString() + " == " + this.right.toString() + ")";
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
		left.setFunction(func);
		right.setFunction(func);
	}

}
