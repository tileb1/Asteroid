package asteroids.Statement;

import asteroids.part3.programs.SourceLocation;

public abstract class ComplexStatement extends Statement {

	public ComplexStatement(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	@Override
	public boolean isExecutable() {
		return true;
	}


}
