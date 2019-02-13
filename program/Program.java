package asteroids.program;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import asteroids.Function.Function;
import asteroids.Statement.Statement;
import asteroids.model.Ship;

public class Program {
	
	public Program (List<Function> functionList, Statement main) {
		this.functionList = functionList;
		this.main = main;
		for (int i = 0; i < functionList.size(); i++) {
			this.getFunctionList().get(i).setProgram(this);
			this.nameFunctionMap.put(this.getFunctionList().get(i).getName(), this.getFunctionList().get(i));
		}
		this.main.setProgram(this);
	}
	
	private HashMap<String, Function> nameFunctionMap = new HashMap<String, Function>();
	
	public HashMap<String, Function> getFunctionMap() {
		return this.nameFunctionMap;
	}
	
	public List<Function> getFunctionList() {
		return this.functionList;
	}
	
	private List<Function> functionList;
	
	private Statement main;
	
	private Ship programShip;
	
	public void setProgramShip(Ship ship) {
		if (ship == null)
			throw new IllegalArgumentException();
		this.programShip = ship;
	}
	
	public Ship getProgramShip() {
		return this.programShip;
	}
	
	private HashMap<String, Object> variableMap = new HashMap<>();;
	
	public HashMap<String, Object> getVariableMap() {
		return this.variableMap;
	}
	
	public void addVariable(String string, Object object) {
		this.getVariableMap().put(string, object);
	}
	
	public Statement getMain() {
		return this.main;
	}
	
	private double timeLeftToExecute = 0;
	
	public double getTimeLeftToExecute() {
		return this.timeLeftToExecute;
	}
	
	public void incrementTimeLeftToExecute(double time) {
		this.timeLeftToExecute += time;
	}
	
	public void decrementTimeLeftToExecute(double time) {
		this.timeLeftToExecute -= time;
	}
	
	public List<Object> executeProgram(double time) {
		this.incrementTimeLeftToExecute(time);
		
		if (this.isFinished) {
			this.toggleCheckBoolean();
			this.setIsFinished(false);
		}
		
		this.getMain().execute();
		
		if (!this.isFinished)
			return null;
		
		List<Object> list = new ArrayList<Object>(this.getPrintingList());
		this.printingList.clear();
		return list;
			
	}
	
	private boolean checkBoolean = false;
	
	public void toggleCheckBoolean() {
		this.checkBoolean = !this.checkBoolean;
	}
	
	public boolean getCheckBoolean() {
		return this.checkBoolean;
	}
	
	private List<Object> printingList = new ArrayList<Object>();
	
	public void addToPrintingList(Object object) {
		this.printingList.add(object);
	}
	
	public List<Object> getPrintingList() {
		return this.printingList;
	}
	
	private boolean onBreak = false;
	
	public boolean isOnBreak() {
		return this.onBreak;
	}
	
	public void setOnBreak(boolean bool) {
		this.onBreak = bool;
	}
	
	public Statement getLastStatement() {
		return this.main.getLastStatement();
	}
	
	private boolean isFinished = false;
	
	public void setIsFinished(boolean bool) {
		this.isFinished = bool;
	}
	
}
