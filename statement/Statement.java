package asteroids.Statement;

import asteroids.Function.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.program.Program;

public abstract class Statement {
	
	public Statement(SourceLocation sourceLocation) {
		this.location = sourceLocation;
	}
	
	public final SourceLocation location;
	
	public abstract void execute();
	
	private Program program;
	
	public void setProgram(Program program) {
		this.program = program;
	}
	
	public Program getProgram() {
		return this.program;
	}
	
	private boolean asbstractBoolean = false;
	
	public boolean getAbstractBoolean() {
		return this.asbstractBoolean;
	}
	
	public void toggleAbstractBoolean() {
		this.asbstractBoolean = !this.asbstractBoolean;
		if (this == this.getProgram().getLastStatement())
			this.getProgram().setIsFinished(true);
	}
	
	public void setAbstractBoolean(boolean bool) {
		this.asbstractBoolean = bool;
		if (this == this.getProgram().getLastStatement())
			this.getProgram().setIsFinished(true);
	}
	
	public boolean isExecutable() {
		if (this.getFunction() == null) {
			return this.getProgram().getTimeLeftToExecute() >= 0.2 && 
					this.getProgram().getCheckBoolean() == this.getAbstractBoolean() 
					&& !this.getProgram().isOnBreak();
		}

		return !this.getFunction().getStatusReturned() && !this.getProgram().isOnBreak();
	}
	
	private Statement parent = null;
	
	public Statement getParent() {
		return this.parent;
	}
	
	public boolean hasParent() {
		return this.parent != null;
	}
	
	public void setParent(Statement parent) {
		this.parent = parent;
	}
	
	private Function function = null;
	
	public void setFunction(Function func) {
		this.function = func;
	}
	
	public Function getFunction() {
		return this.function;
	}
	
	public Statement getLastStatement() {
		return this;
	}
	
	public abstract void setChildrenParent();
	
}
