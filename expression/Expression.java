package asteroids.Expression;

import asteroids.Function.Function;
import asteroids.Statement.Statement;
import asteroids.part3.programs.SourceLocation;
import asteroids.program.Program;

public abstract class Expression {
	
	public Expression ( SourceLocation sourceLocation ) {
		this.location = sourceLocation;
	}
	
	public final SourceLocation location;
	
	
	public abstract Object getValue();

	private Program program;
	
	public void setProgram(Program program) {
		this.program = program;
	}
	public Program getProgram() {
		return this.program;
	}
	
	public SourceLocation getSoureLocation() {
		return this.location;
	}
	
	private Function function;
	
	public void setFunction(Function func) {
		this.function = func;
	}
	
	public Function getFunction() {
		return this.function;
	}
	
	private Statement parent;
	
	public Statement getParent() {
		return this.parent;
	}
	
	public void setParent(Statement statement) {
		this.parent = statement;
	}

}
