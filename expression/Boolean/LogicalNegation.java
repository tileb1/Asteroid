package asteroids.Expression.Boolean;
import asteroids.Expression.BooleanReturn;
import asteroids.Expression.Expression;
import asteroids.Expression.UnaryExpression;
import asteroids.Function.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.program.Program;

public class LogicalNegation<E extends Expression & BooleanReturn>
	extends BooleanExpression implements UnaryExpression <E>{
	
	
	public LogicalNegation(E given, SourceLocation sourceLocation) {
		super(sourceLocation);
		if (given == null)
			throw new IllegalArgumentException();

		this.booleanToNegate =  given;
	}
	
	private E booleanToNegate;
	
	@Override
	public E getExpression() {
		return this.booleanToNegate;
	}

	@Override
	public Boolean getValue() {
		return !(Boolean)booleanToNegate.getValue();
	}

	@Override
	public String toString() {
		return "negation (" +  " ! " + this.booleanToNegate.toString() + ")";
	}
	
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		booleanToNegate.setProgram(program);
	}
	
	@Override
	public void setFunction(Function func) {
		super.setFunction(func);
		booleanToNegate.setFunction(func);
	}

	
	

}
