package asteroids.Statement;

import asteroids.part3.programs.SourceLocation;

public class Break extends Statement {

	public Break(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public void execute() {
		boolean inWhile = false;
		Statement a = this;
		while (a.hasParent()) {
			a = a.getParent();
			if (a instanceof While) {
				inWhile = true;
				break;
			}
		}
		if (this.getFunction() != null && !inWhile) {
			a = this.getFunction().getParent();
			while (a.hasParent()) {
				a = a.getParent();
				if (a instanceof While) {
					inWhile = true;
					break;
				}
			}
		}
		if (!inWhile)
			throw new IllegalArgumentException();
		this.getProgram().setOnBreak(true);
		
	}
	
	@Override
	public void setChildrenParent() {
	}

}
