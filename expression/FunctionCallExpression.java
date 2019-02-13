package asteroids.Expression;

import java.util.ArrayList;
import java.util.List;

import asteroids.Function.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.program.Program;

public class FunctionCallExpression extends UndeterminedReturnExpression {

	public FunctionCallExpression(String functionName, List<Expression> actualArgs, SourceLocation sourceLocation) {
		super(sourceLocation, functionName);
		if (functionName == null)
			throw new IllegalArgumentException();
		this.listArgument = actualArgs;
	}
	
	private List<Expression> listArgument;
	
	@Override
	public String toString() {
		return this.getName() + "(" + this.listArgument.toString() + ")";
	}
	
	public List<Expression> getArguments() {
		return this.listArgument;
	}

	public Object getValue() {
		this.evaluateParameterList();
		this.getProgram().getFunctionMap().get(this.getName()).addListToStack(this.parameterListEvaluated);
		this.getProgram().getFunctionMap().get(this.getName()).setCurrentCall(this);
		return this.getProgram().getFunctionMap().get(this.getName()).execute();
	}
	
	private List<Object> parameterListEvaluated = null;
	
	private void evaluateParameterList() {
		List<Object> a = new ArrayList<Object>();
		for (int i = 0; i < this.listArgument.size(); i++) {
			a.add(this.listArgument.get(i).getValue());
		}
		this.parameterListEvaluated = a;
	}
	
	@Override
	public void setFunction(Function func) {
		super.setFunction(func);
		for (int i = 0; i < this.listArgument.size(); i++) {
			this.listArgument.get(i).setFunction(func);
		}
	}
	
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		for (int i = 0; i < this.listArgument.size(); i++) {
			this.listArgument.get(i).setProgram(program);
		}
	}

}
