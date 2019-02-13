package asteroids.model;

/**
 * A class of asteroids, involving their shape and interaction with other Entities.
 * @author Elisabeth Tu (2eBach CW-WTK) & Tim Lebailly (2eBach CW-ELT)
 *
 */
public class Asteroid extends MinorPlanet {
	
	
	/**
	 *  Creates a new Asteroid with the given position, speed and radius.
	 * @param posX
	 * @param posY
	 * @param speedX
	 * @param speedY
	 * @param radius
	 * @effect |super(posX,posY,speedX,speedY,radius)
	 */
	public Asteroid(double posX, double posY, double speedX, double speedY,
			double radius) {
		
		super(posX,posY,speedX,speedY,radius);
	}
	/**
	 *  Creates a new Asteroid with the given position, speed, radius and maxSpeed.
	 * @param posX
	 * @param posY
	 * @param speedX
	 * @param speedY
	 * @param radius
	 * @param maxSpeed
	 * @effect |super(posX,posY,speedX,speedY,radius,maxSpeed)
	 */
	public Asteroid(double posX, double posY, double speedX, double speedY,
			double radius,double maxSpeed) {
		
		super(posX,posY,speedX,speedY,radius,maxSpeed);
	}
	
	private static final double density = 2.65* Math.pow(10, 12);
	
	/**
	 * A method that returns the density of asteroids
	 * @return 	| @seeImplementation
	 */
	@Override
	public double getDensity() {
		return density;
	}

	/**
	 * resolves the collision between an entity and this asteroid
	 * @effect	@seeImplementation
	 */
	@Override
	protected void resolve(Entity entity) {
		if (! entity.isPartOfWorld(this.getWorld()))
			return;
		entity.resolveAsteroid(this);
	}

	/**
	 * Resolves the collision between this asteroid and a ship
	 * @post	ship.isTerminated() == true
	 */
	@Override
	protected void resolveShip(Ship ship) {
		ship.terminate();
	}

	@Override
	public String toString() {
		return "A" + this.position.toString();
	}
	
}
