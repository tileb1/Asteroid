package asteroids.Expression.EntityExpression;

import java.util.NoSuchElementException;

import asteroids.model.Entity;
import asteroids.model.Planetoid;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class PlanetoidExpression extends EntityExpression {

	public PlanetoidExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	@Override
	public Entity getValue() {
		Ship ship = this.getProgram().getProgramShip();
		try {
			return ship.getWorld().getEntitySet().stream().filter(x -> x instanceof Planetoid).
					reduce((x, y) -> Double.compare(x.getDistanceBetween(ship), 
							y.getDistanceBetween(ship)) <= 0  ? x : y).get();
		} catch (NoSuchElementException exc) {
			return null;
		}
	}
	
	public String toString() {
		return "closest planetoid";
	}
}
