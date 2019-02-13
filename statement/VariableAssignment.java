package asteroids.Statement;

import asteroids.Expression.Expression;
import asteroids.Expression.UnaryExpression;
import asteroids.Function.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.program.Program;

public class VariableAssignment extends Statement implements UnaryExpression<Expression>{

	public VariableAssignment(String string, Expression expression, SourceLocation sourceLocation) {
		super(sourceLocation);
		if (expression == null || string == null)
			throw new IllegalArgumentException();
		this.expression = expression;
		this.varName = string;
		this.setChildrenParent();
		
	}
	
	private String varName;
	
	private Expression expression;
	
	@Override
	public Expression getExpression() {
		return expression;
	}
	
	@Override
	public void execute() {
		if (this.isExecutable()) {
			Object theValue = this.getExpression().getValue();
			if (this.getFunction() != null) {
				try {
					if (this.getFunction().getLocalVariableMap().containsKey(this.varName)) {
						if (!this.getFunction().getLocalVariableMap().get(this.varName).getClass().equals(theValue.getClass()))
							throw new IllegalArgumentException();
					}
				} catch(NullPointerException exc) {
					//Current of old value is null
				}
				this.getFunction().addLocalVariable(this.varName, theValue);
			}
			
			else {
				try {
					if (this.getProgram().getVariableMap().containsKey(this.varName)) {
						if (!this.getProgram().getVariableMap().get(this.varName).getClass().equals(theValue.getClass()))
							throw new IllegalArgumentException();
					}
				} catch(NullPointerException exc) {
					//Current of old value is null
				}
				if (this.getProgram().getFunctionMap().containsKey(this.varName))
					throw new IllegalArgumentException();
				this.getProgram().addVariable(this.varName, theValue);
			}
			this.toggleAbstractBoolean();
		}
	}

	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		this.getExpression().setProgram(this.getProgram());
	}
	
	@Override
	public void setFunction(Function func) {
		super.setFunction(func);
		this.getExpression().setFunction(func);
		
	}

	@Override
	public void setChildrenParent() {
		this.expression.setParent(this);
		
	}

	
	

}
