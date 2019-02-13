package asteroids.Expression.EntityExpression;

import java.util.NoSuchElementException;
import java.util.Set;

import asteroids.model.Entity;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class ShipExpression extends EntityExpression{

	public ShipExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Entity getValue() {
		Ship ship = this.getProgram().getProgramShip();
		Set<Entity> set = ship.getWorld().getEntitySet();
		set.remove(ship);
		try {
			return set.stream().filter(x -> x instanceof Ship).
					reduce((x, y) -> Double.compare(x.getDistanceBetween(ship), 
							y.getDistanceBetween(ship)) <= 0  ? x : y).get();
		} catch (NoSuchElementException exc) {
			return null;
		}
	}
	
	public String toString() {
		return "closest ship";
	}
}
