package asteroids.Statement;

import java.util.List;

import asteroids.Function.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.program.Program;

public class Sequence extends ComplexStatement {

	public Sequence(List<Statement> statementList, SourceLocation sourceLocation) {
		super(sourceLocation);
		if (statementList == null)
			throw new IllegalArgumentException();
		this.statementList = statementList;
		this.setChildrenParent();
	}

	List<Statement> statementList;
	
	public List<Statement> getStatementList() {
		return this.statementList;
	}
	
	@Override
	public void execute() {
		for (int i = 0; i < this.getStatementList().size(); i++) {
			this.getStatementList().get(i).execute();
		}
	}
	
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		for (int i = 0; i < this.getStatementList().size(); i++) {
			this.getStatementList().get(i).setProgram(program);
		}
	}

	@Override
	public Statement getLastStatement() {
		return this.statementList.get(this.statementList.size() - 1);
	}
	
	@Override
	public void toggleAbstractBoolean() {
		for (int i = 0; i < this.getStatementList().size(); i++) {
			this.getStatementList().get(i).toggleAbstractBoolean();
		}
	}
	
	@Override
	public void setAbstractBoolean(boolean bool) {
		for (int i = 0; i < this.getStatementList().size(); i++) {
			this.getStatementList().get(i).setAbstractBoolean(bool);
		}
	}

	@Override
	public void setChildrenParent() {
		for (int i = 0; i < this.getStatementList().size(); i++) {
			this.getStatementList().get(i).setParent(this);
		}
	}
	
	@Override
	public void setFunction(Function func) {
		super.setFunction(func);
		for (int i = 0; i < this.getStatementList().size(); i++) {
			this.getStatementList().get(i).setFunction(func);
		}
	}
	
}
