package asteroids.Expression;

public interface BinaryExpression <L extends Expression,R extends Expression> {
	
	public Expression getLeft();
	
	public Expression getRight();

}
