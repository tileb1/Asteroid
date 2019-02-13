package asteroids.Expression.EntityExpression;

import java.util.NoSuchElementException;
import java.util.function.Predicate;

import asteroids.model.Bullet;
import asteroids.model.Entity;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class BulletExpression extends EntityExpression {

	public BulletExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	

	@Override
	public Entity getValue() {
		Ship ship = this.getProgram().getProgramShip();
		try {
			return this.getProgram().getProgramShip().getWorld().getEntitySet().stream().
					filter(new Predicate<Object>() { 
						@Override
						public boolean test(Object o) {
							if (o instanceof Bullet)
								return (((Bullet)o).getFiredBy() == ship);
							return false;
						}
					}).
					reduce((x, y) -> Double.compare(x.getDistanceBetween(ship), 
							y.getDistanceBetween(ship)) <= 0  ? x : y).get();
		} catch (NoSuchElementException exc) {
			return null;
		}
	}

	
	public String toString() {
		return "bullet from ship";
	}

}
