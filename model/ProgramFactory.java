package asteroids.model;

import java.util.List;

import asteroids.Expression.*;
import asteroids.Expression.Boolean.*;
import asteroids.Expression.Double.*;
import asteroids.Expression.DoubleEntity.*;
import asteroids.Expression.EntityExpression.*;
import asteroids.Function.Function;
import asteroids.Statement.*;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.SourceLocation;
import asteroids.program.Program;

public  class ProgramFactory implements IProgramFactory<Expression, Statement, Function, Program> {
	public ProgramFactory() {
		
	}
	
	@Override
	public Program createProgram(List<Function> functions, Statement main) {
		return new Program(functions, main);
	}

	@Override
	public Function createFunctionDefinition(String functionName, Statement body, SourceLocation sourceLocation) {
		return new Function(functionName, body, sourceLocation);
	}

	@Override
	public Statement createAssignmentStatement(String variableName, Expression value,
			SourceLocation sourceLocation) {
		return new VariableAssignment(variableName, value, sourceLocation);
	}

	@Override
	public Statement createWhileStatement(Expression condition, Statement body, SourceLocation sourceLocation) {
		if (condition instanceof BooleanExpression)
			return new While<BooleanExpression>((BooleanExpression) condition, body, sourceLocation);
		else
			return new While<UndeterminedReturnExpression>((UndeterminedReturnExpression) condition, body, sourceLocation);
	}

	@Override
	public Statement createBreakStatement(SourceLocation sourceLocation) {
		return new Break(sourceLocation);
	}

	@Override
	public Statement createReturnStatement(Expression value, SourceLocation sourceLocation) {
		return new Return(value, sourceLocation);
	}

	@Override
	public Statement createIfStatement(Expression condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {
		if (condition instanceof BooleanExpression)
			return new IfElse<BooleanExpression>((BooleanExpression) condition, ifBody, elseBody, sourceLocation);
		else
			return new IfElse<UndeterminedReturnExpression>((UndeterminedReturnExpression) condition, ifBody, elseBody, sourceLocation);
	}

	@Override
	public Statement createPrintStatement(Expression value, SourceLocation sourceLocation) {
		return new Print(value, sourceLocation);
	}

	@Override
	public Statement createSequenceStatement(List<Statement> statements, SourceLocation sourceLocation) {
		return new Sequence(statements, sourceLocation);
	}

	@Override
	public Expression createReadVariableExpression(String variableName, SourceLocation sourceLocation) {
		return new ReadVariableExpression(variableName, sourceLocation);
	}

	@Override
	public Expression createReadParameterExpression(String parameterName, SourceLocation sourceLocation) {
		return new ReadParameterExpression(parameterName, sourceLocation);
	}

	@Override
	public Expression createFunctionCallExpression(String functionName, List<Expression> actualArgs,
			SourceLocation sourceLocation) {
		return new FunctionCallExpression(functionName, actualArgs, sourceLocation);
	}

	@Override
	public Expression createChangeSignExpression(Expression expression, SourceLocation sourceLocation) {
		if (expression instanceof DoubleExpression) {
			return new ChangeSign<DoubleExpression>((DoubleExpression) expression, sourceLocation);
		}
		else
			return new ChangeSign<UndeterminedReturnExpression>((UndeterminedReturnExpression) expression, sourceLocation);
	}

	@Override
	public Expression createNotExpression(Expression expression, SourceLocation sourceLocation) {
		if (expression instanceof BooleanExpression) {
			return new LogicalNegation<BooleanExpression>((BooleanExpression) expression, sourceLocation);
		}
		else
			return new LogicalNegation<UndeterminedReturnExpression>((UndeterminedReturnExpression) expression, sourceLocation);
	}

	@Override
	public Expression createDoubleLiteralExpression(double value, SourceLocation location) {
		return new DoubleExpressionValue(value,  location);
	}

	@Override
	public Expression createNullExpression(SourceLocation location) {
		return new NullExpression(location);
	}

	@Override
	public Expression createSelfExpression(SourceLocation location) {
		return new PlayerExpression(location);
	}

	@Override
	public Expression createShipExpression(SourceLocation location) {
		return new ShipExpression(location);
	}

	@Override
	public Expression createAsteroidExpression(SourceLocation location) {
		return new AsteroidExpression(location);
	}

	@Override
	public Expression createPlanetoidExpression(SourceLocation location) {
		return new PlanetoidExpression(location);
	}

	@Override
	public Expression createBulletExpression(SourceLocation location) {
		return new BulletExpression(location);
	}

	@Override
	public Expression createPlanetExpression(SourceLocation location) {
		return new MinorPlanetExpression(location);
	}

	@Override
	public Expression createAnyExpression(SourceLocation location) {
		return new EntityExpressionValue(location);
	}

	@Override
	public Expression createGetXExpression(Expression e, SourceLocation location) {
		if (e instanceof EntityExpression) {
			return new GetXExpression<EntityExpression>((EntityExpression) e, location);
		}
		else
			return new GetXExpression<UndeterminedReturnExpression>((UndeterminedReturnExpression) e, location);
	}

	@Override
	public Expression createGetYExpression(Expression e, SourceLocation location) {
		if (e instanceof EntityExpression) {
			return new GetYExpression<EntityExpression>((EntityExpression) e, location);
		}
		else
			return new GetYExpression<UndeterminedReturnExpression>((UndeterminedReturnExpression) e, location);
	}

	@Override
	public Expression createGetVXExpression(Expression e, SourceLocation location) {
		if (e instanceof EntityExpression) {
			return new GetVxExpression<EntityExpression>((EntityExpression) e, location);
		}
		else
			return new GetVxExpression<UndeterminedReturnExpression>((UndeterminedReturnExpression) e, location);
	}

	@Override
	public Expression createGetVYExpression(Expression e, SourceLocation location) {
		if (e instanceof EntityExpression) {
			return new GetVyExpression<EntityExpression>((EntityExpression) e, location);
		}
		else
			return new GetVyExpression<UndeterminedReturnExpression>((UndeterminedReturnExpression) e, location);
	}

	@Override
	public Expression createGetRadiusExpression(Expression e, SourceLocation location) {
		if (e instanceof EntityExpression) {
			return new GetRadiusExpression<EntityExpression>((EntityExpression) e, location);
		}
		else
			return new GetRadiusExpression<UndeterminedReturnExpression>((UndeterminedReturnExpression) e, location);
	}

	@Override
	public Expression createLessThanExpression(Expression e1, Expression e2, SourceLocation location) {
		if (e1 instanceof DoubleExpression && e2 instanceof DoubleExpression) {
			return new LessThan<DoubleExpression, DoubleExpression>((DoubleExpression) e1, (DoubleExpression)e2, location);
		}
		if (e1 instanceof DoubleExpression && e2 instanceof UndeterminedReturnExpression) {
			return new LessThan<DoubleExpression, UndeterminedReturnExpression>((DoubleExpression)e1, (UndeterminedReturnExpression)e2, location);
		}
		if (e1 instanceof UndeterminedReturnExpression && e2 instanceof DoubleExpression) {
			return new LessThan<UndeterminedReturnExpression, DoubleExpression>((UndeterminedReturnExpression)e1, (DoubleExpression)e2, location);
		}
		else
			return new LessThan<UndeterminedReturnExpression, UndeterminedReturnExpression>((UndeterminedReturnExpression)e2, (UndeterminedReturnExpression)e1, location);
	}

	@Override
	public Expression createEqualityExpression(Expression e1, Expression e2, SourceLocation location) {
		return new EqualsTo(e1, e2, location);
	}

	@Override
	public Expression createAdditionExpression(Expression e1, Expression e2, SourceLocation location) {
		if (e1 instanceof DoubleExpression && e2 instanceof DoubleExpression) {
			return new Addition<DoubleExpression, DoubleExpression>((DoubleExpression)e1, (DoubleExpression)e2, location);
		}
		if (e1 instanceof DoubleExpression && e2 instanceof UndeterminedReturnExpression) {
			return new Addition<DoubleExpression, UndeterminedReturnExpression>((DoubleExpression)e1, (UndeterminedReturnExpression)e2, location);
		}
		if (e1 instanceof UndeterminedReturnExpression && e2 instanceof DoubleExpression) {
			return new Addition<DoubleExpression, UndeterminedReturnExpression>((DoubleExpression)e2, (UndeterminedReturnExpression)e1, location);
		}
		else
			return new Addition<UndeterminedReturnExpression, UndeterminedReturnExpression>((UndeterminedReturnExpression)e2, (UndeterminedReturnExpression)e1, location);
	}

	@Override
	public Expression createMultiplicationExpression(Expression e1, Expression e2, SourceLocation location) {
		if (e1 instanceof DoubleExpression && e2 instanceof DoubleExpression) {
			return new Multiplication<DoubleExpression, DoubleExpression>((DoubleExpression)e1, (DoubleExpression)e2, location);
		}
		if (e1 instanceof DoubleExpression && e2 instanceof UndeterminedReturnExpression) {
			return new Multiplication<DoubleExpression, UndeterminedReturnExpression>((DoubleExpression)e1, (UndeterminedReturnExpression)e2, location);
		}
		if (e1 instanceof UndeterminedReturnExpression && e2 instanceof DoubleExpression) {
			return new Multiplication<DoubleExpression, UndeterminedReturnExpression>((DoubleExpression)e2, (UndeterminedReturnExpression)e1, location);
		}
		else
			return new Multiplication<UndeterminedReturnExpression, UndeterminedReturnExpression>((UndeterminedReturnExpression)e2, (UndeterminedReturnExpression)e1, location);
	}

	@Override
	public Expression createSqrtExpression(Expression e, SourceLocation location) {
		if (e instanceof DoubleExpression) {
			return new Squareroot<DoubleExpression>((DoubleExpression) e, location);
		}
		else
			return new Squareroot<UndeterminedReturnExpression>((UndeterminedReturnExpression) e, location);
	}

	@Override
	public Expression createGetDirectionExpression(SourceLocation location) {
		return new GetDirectionSelf(location);
	}

	@Override
	public Statement createThrustOnStatement(SourceLocation location) {
		return new ThrustOn(location);
	}

	@Override
	public Statement createThrustOffStatement(SourceLocation location) {
		return new ThrustOff(location);
	}

	@Override
	public Statement createFireStatement(SourceLocation location) {
		return new Fire(location);
	}

	@Override
	public Statement createTurnStatement(Expression angle, SourceLocation location) {
		if (angle instanceof DoubleExpression)
			return new Turn<DoubleExpression>((DoubleExpression) angle, location);
		else
			return new Turn<UndeterminedReturnExpression>((UndeterminedReturnExpression) angle, location);
	}

	@Override
	public Statement createSkipStatement(SourceLocation location) {
		return new Skip(location);
	}



}	