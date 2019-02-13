package asteroids.Expression;

import asteroids.part3.programs.SourceLocation;

public class ReadParameterExpression extends UndeterminedReturnExpression {

	public ReadParameterExpression(String name, SourceLocation sourceLocation) {
		super(sourceLocation, name);
		if (name == null)
			throw new IllegalArgumentException();
	}
	
	@Override
	public Object getValue() {
		if (this.getFunction().getParamMap().containsKey(this.getName())) {
			return this.getFunction().getParamMap().get(this.getName());
		}

		else {
			return this.getFunction().getParameter(this.getName());
		}
	}

	@Override
	public String toString() {
		return "$ (" + this.getValue().toString() + ")";
	}

}
