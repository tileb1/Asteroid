package asteroids.Expression.DoubleEntity;

import asteroids.Expression.EntityReturn;
import asteroids.Expression.Expression;
import asteroids.Expression.UnaryExpression;
import asteroids.Expression.Double.DoubleExpression;
import asteroids.Function.Function;
import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;
import asteroids.program.Program;

public abstract class EntityCharacteristicExpression  <E extends Expression & EntityReturn>
	extends DoubleExpression implements UnaryExpression <E>{

	public EntityCharacteristicExpression(E expression, SourceLocation sourceLocation) {
		super(sourceLocation);
		if (expression == null)
			throw new IllegalArgumentException();
		
		this.expression = expression;
		}
	
	private E expression;
	
	@Override
	public E getExpression() {
		return expression;
	}
	
	public Entity getEntity() {
		return (Entity) this.expression.getValue();
	}
	
	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		expression.setProgram(program);
	}
	
	@Override
	public void setFunction(Function func) {
		super.setFunction(func);
		expression.setFunction(func);
	}

}
 