package asteroids.Expression.Double;

import asteroids.Expression.BinaryExpression;
import asteroids.Expression.DoubleReturn;
import asteroids.Expression.Expression;
import asteroids.Function.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.program.Program;

public class Multiplication<L extends Expression & DoubleReturn, R extends Expression & DoubleReturn> 
	extends DoubleExpression implements BinaryExpression <L,R>{

	public Multiplication(L left, R right,SourceLocation sourceLocation) {
		super(sourceLocation);
		if (left == null || right == null)
			throw new IllegalArgumentException();
		this.left = left;
		this.right = right;
	}
	
	private L left;
	private R right;

	@Override
	public Double getValue() {
		return (double)this.left.getValue() * (double)this.right.getValue();
	}
	
	@Override
	public L getLeft() {
		return left;
	}

	@Override
	public R getRight() {
		return right;
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
	
	@Override
	public String toString() {
		return "mult(" + this.left.toString() + " * " + this.right.toString() + ")";
	}

}
