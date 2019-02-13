package asteroids.Statement;

import asteroids.Function.Function;
import asteroids.part3.programs.SourceLocation;

public class ThrustOff extends Statement {

	public ThrustOff(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public void execute() {
		if (this.isExecutable()) {
			this.getProgram().getProgramShip().thrustOff();
			this.toggleAbstractBoolean();
			this.getProgram().decrementTimeLeftToExecute(0.2);
		}
	}
	
	@Override
	public void setFunction(Function func) {
		throw new IllegalArgumentException();
	}
	
	@Override
	public void setChildrenParent() {
	}

}