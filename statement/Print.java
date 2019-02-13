package asteroids.Statement;

import asteroids.Expression.Expression;
import asteroids.Expression.UnaryExpression;
import asteroids.Function.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.program.Program;

public class Print extends Statement implements UnaryExpression<Expression>{

	public Print(Expression expression, SourceLocation sourceLocation){
		super(sourceLocation);
		if (expression == null)
			throw new IllegalArgumentException();
		this.expression = expression;
		this.setChildrenParent();
	}
	
	private Expression expression;
	
	@Override
	public Expression getExpression() {
		return this.expression;
	}

	@Override
	public void execute() {
		if (this.isExecutable()) {
			Object obj = this.expression.getValue();
			this.getProgram().addToPrintingList(obj);
			System.out.print("PRINT STATEMENT OUTPUTS: ");
			System.out.println(this.expression.toString());
			this.toggleAbstractBoolean();
		}
	}

	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		this.expression.setProgram(program);
	}
	
	@Override
	public void setFunction(Function func) {
		throw new IllegalArgumentException();
	}
	
	@Override
	public void setChildrenParent() {
		this.expression.setParent(this);
		
	}

}
