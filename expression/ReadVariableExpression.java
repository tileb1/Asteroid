package asteroids.Expression;

import asteroids.part3.programs.SourceLocation;

public class ReadVariableExpression extends UndeterminedReturnExpression {

	public ReadVariableExpression(String varName, SourceLocation sourceLocation) {
		super(sourceLocation, varName);
		if (varName == null)
			throw new IllegalArgumentException();
	}

	@Override
	public Object getValue() {
		if (this.getFunction() != null) {
			if (this.getFunction().getLocalVariableMap().containsKey(this.getName()))
				return this.getFunction().getLocalVariableMap().get(this.getName());
		}
		if (this.getProgram().getVariableMap().containsKey(this.getName()))
			return this.getProgram().getVariableMap().get(this.getName());
		// Should not be thrown
		throw new IllegalArgumentException();
	}

	@Override
	public String toString() {
		return "variable (" + this.getValue().toString() + ")";
	}
	
}
