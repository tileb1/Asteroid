package asteroids.Statement;

import asteroids.Expression.Expression;
import asteroids.Expression.UnaryExpression;
import asteroids.Function.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.program.Program;

public class Return extends Statement implements UnaryExpression<Expression>{

	public Return(Expression expression, SourceLocation sourceLocation) {
		super(sourceLocation);
		if (expression == null)
			throw new IllegalArgumentException();
		this.expression = expression;
		this.setChildrenParent();
	}
	
	private Expression expression;

	@Override
	public Expression getExpression() {
		return expression;
	}

	@Override
	public void execute() {
		Object a = this.getExpression().getValue();
		this.getFunction().setObjectToReturn(a);
	}

	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		this.getExpression().setProgram(this.getProgram());
	}
	
	@Override
	public void setFunction(Function func) {
		super.setFunction(func);
		this.expression.setFunction(func);
	}
	
	@Override
	public void setChildrenParent() {
		this.expression.setParent(this);
		
	}

}
