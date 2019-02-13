package asteroids.Statement;

import asteroids.Expression.BooleanReturn;
import asteroids.Expression.Expression;
import asteroids.Expression.UnaryExpression;
import asteroids.Function.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.program.Program;

public class IfElse<E extends Expression & BooleanReturn> extends ComplexStatement implements UnaryExpression<E> {

	public IfElse(E condition, Statement ifBody, Statement elseBody, 
			SourceLocation sourceLocation) {
		super(sourceLocation);
		if (ifBody == null || condition == null)
			throw new IllegalArgumentException();
		this.condition = condition;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
		this.setChildrenParent();
		}
	
	private Statement ifBody;
	private Statement elseBody;
	private E condition;

	@Override
	public E getExpression() {
		return condition;
	}
	
	@Override
	public void execute() {
		if ((Boolean)this.getExpression().getValue())
			ifBody.execute();
		else if (this.elseBody != null)
			elseBody.execute();
		super.setAbstractBoolean(this.getProgram().getCheckBoolean());
		
	}
	
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		if (this.ifBody != null)
			this.ifBody.setProgram(program);
		if (this.elseBody != null)
			this.elseBody.setProgram(program);
		this.condition.setProgram(program);
	}

	@Override
	public Statement getLastStatement() {
		if (this.elseBody == null) {
			if ((Boolean)this.getExpression().getValue())
				return this.ifBody;
			else
				return this;
		}
		else {
			if ((Boolean)this.getExpression().getValue())
				return this.ifBody;
			else
				return this.elseBody;
		}
	}

	@Override
	public void setAbstractBoolean(boolean bool) {
		super.setAbstractBoolean(bool);
		this.ifBody.setAbstractBoolean(bool);
		if (this.elseBody != null)
			this.elseBody.setAbstractBoolean(bool);
	}
	
	@Override
	public void toggleAbstractBoolean() {
		this.ifBody.toggleAbstractBoolean();
		if (this.elseBody != null)
			this.elseBody.toggleAbstractBoolean();
	}

	@Override
	public void setChildrenParent() {
		if (this.elseBody != null)
			this.elseBody.setParent(this);
		this.ifBody.setParent(this);
	}
	
	@Override
	public void setFunction(Function func) {
		super.setFunction(func);
		this.ifBody.setFunction(func);
		if (this.elseBody != null)
			this.elseBody.setFunction(func);
		this.getExpression().setFunction(func);
	}

	

}
