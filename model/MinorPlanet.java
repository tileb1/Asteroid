package asteroids.model;

/**
 * An abstract class of Minorplanets, involving their shape, movement and interaction with other MinorPlanets.
 * @author Elisabeth Tu (2eBach CW-WTK) & Tim Lebailly (2eBach CW-ELT)
 *
 */
public abstract class MinorPlanet extends Entity{
	
	/**
	 * Creates a new minorPlanet with the given position, speed and radius
	 * @param posX
	 * @param posY
	 * @param speedX
	 * @param speedY
	 * @param radius
	 * @effect | super(posX,posY,speedX,speedY,radius)
	 */
	public MinorPlanet(double posX, double posY, double speedX, double speedY, double radius) {
		super(posX,posY,speedX,speedY,radius);
	}
	
	/**
	 * Creates a new minorPlanet with the given position, speed, radius and maxSpeed.
	 * @param posX
	 * @param posY
	 * @param speedX
	 * @param speedY
	 * @param radius
	 * @param maxSpeed
	 * @effect | super(posX,posY,speedX,speedY,radius,maxSpeed)
	 */
	public MinorPlanet(double posX, double posY, double speedX, double speedY, double radius, double maxSpeed) {
		super(posX,posY,speedX,speedY,radius,maxSpeed);
	}
	
	
	public final static double minimumRadius = 5;
	/**
	 * @return	@seeImplementation
	 */
	public boolean isValidRadius(double value){
		return value >= minimumRadius;
	}
	
	/**
	 * @return	@seeImplementation
	 */
	@Override
	public abstract double getDensity();

	/**
	 * @effect	@seeImplementation
	 */
	@Override
	protected void resolveAsteroid(Asteroid asteroid) {
		this.resolveBounceEntity(asteroid);
	}
	
	/**
	 * @effect	@seeImplementation
	 */
	@Override
	protected void resolvePlanetoid(Planetoid planetoid) {
		this.resolveBounceEntity(planetoid);
	}
}
