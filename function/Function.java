package asteroids.Function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import asteroids.Expression.FunctionCallExpression;
import asteroids.Statement.Statement;
import asteroids.part3.programs.SourceLocation;
import asteroids.program.Program;

public class Function {
	public Function(String functionName, Statement body, SourceLocation sourceLocation) {
		this.location = sourceLocation;
		this.functionName = functionName;
		this.body = body;
		this.body.setFunction(this);
	}
	
	public String functionName;
	
	public Statement body;
	
	public final SourceLocation location;
	
	public Object execute() {
		this.body.execute();
		if (!this.returnedValueChanged)
			throw new IllegalArgumentException();
		this.returnedValueChanged = false;
		this.removeLastElementFromStack();
		return this.objectToReturn;
	}
	
	private Program program;
	
	public void setProgram(Program program) {
		this.program = program;
		this.body.setProgram(program);
	}
	
	public Program getProgram() {
		return this.program;
	}
	
	public Statement getBody() {
		return this.body;
	}
	
	public String getName() {
		return this.functionName;
	}
	
	public Statement getParent() {
		return this.currentCall.getParent();
	}
	
	private FunctionCallExpression currentCall = null;
	
	public void setCurrentCall(FunctionCallExpression expression) {
		this.currentCall = expression;
	}
	
	private Object objectToReturn = null;
	
	public void setObjectToReturn(Object obj) {
		this.objectToReturn = obj;
		this.stackStatusReturned.remove(this.stackStatusReturned.size() - 1);
		this.stackStatusReturned.add(true);
		this.returnedValueChanged = true;
	}
	
	private boolean returnedValueChanged = false;
	
	public boolean getStatusReturned() {
		return this.stackStatusReturned.get(this.stackStatusReturned.size() - 1);
	}
	
	private List<Boolean> stackStatusReturned = new ArrayList<Boolean>();
	
	/* -------------------------------- Parameters -----------------------------------------------------*/
	
	private List<List<Object>> stackParameterList = new ArrayList<List<Object>>();
	
	private List<HashMap<String, Object>> stackParameterMap = new ArrayList<HashMap<String, Object>>();
	
	public void addListToStack(List<Object> list) {
		this.stackParameterList.add(list);
		this.stackParameterMap.add(new HashMap<String, Object>());
		this.stackLocalVariableMap.add(new HashMap<String, Object>());
		this.stackStatusReturned.add(false);
	}

	public HashMap<String, Object> getParamMap() {
		return this.stackParameterMap.get(this.stackParameterMap.size() - 1);
	}
	
	public Object getParameter(String name) {
		Object obj = this.stackParameterList.get(this.stackParameterList.size() - 1).get(0);
		this.stackParameterList.get(this.stackParameterList.size() - 1).remove(0);
		this.stackParameterMap.get(this.stackParameterMap.size() - 1).put(name, obj);
		return obj;
	}
	
	public void removeLastElementFromStack() {
		this.stackParameterList.remove(this.stackParameterList.size() - 1);
		this.stackParameterMap.remove(this.stackParameterMap.size() - 1);
		this.stackLocalVariableMap.remove(this.stackLocalVariableMap.size() - 1);
		this.stackStatusReturned.remove(this.stackStatusReturned.size() - 1);
	}
	
	/* -------------------------------- Local variables -----------------------------------------------------*/
	
	
	private List<HashMap<String, Object>> stackLocalVariableMap = new ArrayList<HashMap<String, Object>>();
	
	public void addLocalVariable(String name, Object object) {
		this.stackLocalVariableMap.get(this.stackLocalVariableMap.size() - 1).put(name, object);
	}
	
	public HashMap<String, Object> getLocalVariableMap() {
		return this.stackLocalVariableMap.get(this.stackLocalVariableMap.size() - 1);
	}
	
	
}
