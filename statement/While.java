package asteroids.Statement;

import asteroids.Expression.BooleanReturn;
import asteroids.Expression.Expression;
import asteroids.Expression.UnaryExpression;
import asteroids.Function.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.program.Program;

public class While<E extends Expression & BooleanReturn>
	extends ComplexStatement implements UnaryExpression<E> {

	public While(E condition, Statement body, SourceLocation sourceLocation) {
		super(sourceLocation);
		if (condition == null || body == null)
			throw new IllegalArgumentException();
		this.condition = condition;
		this.body = body;
		this.setChildrenParent();
	}
	
	private Statement body;
	private E condition;
	
	@Override
	public E getExpression() {
		return condition;
	}


	@Override
	public void execute() {
		if (this.getProgram().isOnBreak())
			return;
		Boolean a = (Boolean) getExpression().getValue();
		while (a && this.getProgram().getTimeLeftToExecute() >= 0.2) {
			this.body.execute();
			if (this.getProgram().isOnBreak()) {
				this.getProgram().setOnBreak(false);
				break;
			}
			a = (Boolean) this.getExpression().getValue();
			if (a && this.getProgram().getTimeLeftToExecute() >= 0.2)
				this.setAbstractBoolean(this.getProgram().getCheckBoolean());
		}
		super.setAbstractBoolean(this.getProgram().getCheckBoolean());
		
	}
	
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		this.body.setProgram(program);
		this.getExpression().setProgram(program);
	}

	@Override
	public Statement getLastStatement() {
		if ((Boolean) this.getExpression().getValue())
			return this.body.getLastStatement();
		else
			return this;
	}

	@Override
	public void setChildrenParent() {
		this.body.setParent(this);
	}
	
	@Override
	public void setFunction(Function func) {
		super.setFunction(func);
		this.body.setFunction(func);
		this.getExpression().setFunction(func);
	}
	
	@Override
	public void toggleAbstractBoolean() {
		super.toggleAbstractBoolean();
		this.body.toggleAbstractBoolean();
	}
	
	@Override
	public void setAbstractBoolean(boolean bool) {
		super.setAbstractBoolean(bool);
		this.body.setAbstractBoolean(bool);
	}

	
}
