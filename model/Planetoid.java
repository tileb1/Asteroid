package asteroids.model;

import java.util.Random;

/**
 * A class of Planetoids involving their shape, behaviour and interaction with other Entities.
 * @invar 	getRadius() <= getOriginalRadius()
 * 
 
 * 
 * @author Elisabeth Tu (2eBach CW-WTK) & Tim Lebailly (2eBach CW-ELT)
 *
 */
public class Planetoid extends MinorPlanet{
	
	/**
	 * Creates a new Planetoid with the given position, speed and radius and travelled distance.
	 * It also sets the maxSpeed of this Planetoid to the default value C.
	 * @param posX
	 * @param posY
	 * @param speedX
	 * @param speedY
	 * @param radius
	 * @param distanceTravelled
	 * @effect 	| Planetoid(posX,posY,speedX,speedY,radius,C)
	 * 
	 */
	public Planetoid(double posX, double posY, double speedX, double speedY,
			double radius, double distanceTravelled) {
		this(posX,posY,speedX,speedY, radius,distanceTravelled,C);
	}
	/**
	 * Creates a new Planetoid with the given position, speed and radius, travelled distance and maxSpeed.
	 * @param posX
	 * @param posY
	 * @param speedX
	 * @param speedY
	 * @param radius
	 * @param distanceTravelled
	 * @effect 	| super(posX,posY,speedX,speedY,radius,maxSpeed)
	 * @post 	|this.getTravelledDistance() == distanceTravelled
	 * @post	this.getRadius() == radius - this.getTravelledDistance() * 0.000001
	 */
	public Planetoid(double posX, double posY, double speedX, double speedY,
			double radius, double distanceTravelled, double maxSpeed) {
		super(posX,posY,speedX,speedY, radius,maxSpeed);
		if(distanceTravelled >= 0)
			this.travelledDistance = distanceTravelled;
		this.setRadius(radius - this.getTravelledDistance() * 0.000001);
	}
	
	
	private double shrunkRadius;
	
	/**
	 * @return 	@seeImplementation
	 */
	@Override
	public double getRadius() {
		return this.shrunkRadius;
	}
	
	/**
	 * 
	 * @return @seeImplementation
	 */
	public double getOriginalRadius() {
		return super.getRadius();
	}

	
	/**
	 * A method to set the radius of the planetoid to the given radius.
	 * @param radius
	 * @post @seeImplementation
	 */
	public void setRadius(double value) {
		if (value < minimumRadius)
			this.terminate();
		else
			this.shrunkRadius = value;
	}
	
	private static final double density = 0.917* Math.pow(10, 12);
	
	/**
	 * A method that returns the density of planetoids
	 * @return @seeImplementation
	 */
	@Override
	public double getDensity() {
		return density;
	}
	
	
	/**
	 * a method to terminate this planetoid and create 2 children if necessary
	 * 
	 * @post 	this.isTerminated == true
	 * @effect	if (this.getWorld()!= null && this.getRadius() >= 30)
	 * 				then createChildren(this.getWorld())
	 */
	@Override
	public void terminate() {
		World theWorld = this.getWorld();
		super.terminate();
		if (theWorld!= null && this.getRadius() >= 30) {
			createChildren(theWorld);
		}
	}

	/**
	 * A method that creates two children (child1 and child2) asteroids for this planetoid.
	 * 
	 * @param	world	the world this planetoid was in.
	 * @post	|child1.getWorld() == child2.getWorld() == theWorld
	 * @post	|child1.getSpeed() == -child2.getSpeed()
	 * @post	|child1.getRadius() == child2.getRadius()
	 * @post	|child1.getDistanceBetween(child2) == 0
	 * 
	 */
	private void createChildren(World world) {
		Vector2[] posAndSpeed =createChildrenPosAndSpeed();
		Asteroid child1 = new Asteroid(this.getPosition().getX() + posAndSpeed[0].getX() * this.getRadius() / 2,
				this.getPosition().getY() + posAndSpeed[0].getY() * this.getRadius() / 2,
				posAndSpeed[1].getX(), posAndSpeed[1].getY(), this.getRadius() / 2);
		
		Asteroid child2 = new Asteroid(this.getPosition().getX() - posAndSpeed[0].getX() * this.getRadius() / 2,
				this.getPosition().getY() - posAndSpeed[0].getY() * this.getRadius() / 2,
				-posAndSpeed[1].getX(), -posAndSpeed[1].getY(), this.getRadius() / 2);
		world.addEntity(child1);
		world.addEntity(child2);
	}

	/**
	 * A method to calculate a direction and speed for one of the children of this planetoid
	 *@return |Vector2[] {direction,speed}
	 *			with direction == Vector2 ( random, sqrt(1 - random^2))
	 *			and speed = Vector2 (1.5* this.getSpeed().getLength() * random, 1.5 * this.getSpeed().getLength() * direction.getY()
	 *			0<= random <=1
	 * 		
	 */
	private Vector2[]  createChildrenPosAndSpeed() {
		Random r = new Random();
		double randomDirectionX = r.nextDouble();
		double randomDirectionY = Math.sqrt(1 - Math.pow(randomDirectionX, 2));
		if (r.nextDouble() > 0.5)
			randomDirectionX *= -1;
		if (r.nextDouble() <= 0.5)
			randomDirectionY *= -1;
		double speedChild1X = 1.5 * this.getSpeed().getLength() * randomDirectionX;
		double speedChild1Y = 1.5 * this.getSpeed().getLength() * randomDirectionY;
		return new Vector2[]{new Vector2(randomDirectionX,randomDirectionY),new Vector2(speedChild1X,speedChild1Y)};
	}

	private double travelledDistance = 0;
	
	/**
	 * Returns the distance travelled by this planetoid.
	 * @return @seeImplementation
	 */
	public double getTravelledDistance() {
		return this.travelledDistance;
	}
	
	/**
	 * A method to add a certain distance to the distance travelled by this planetoid.
	 * @param distance
	 * @post | new.getTravelledDistance() = old.getTravelledDistance() + distance
	 * 
	 */
	private void incrementTravelledDistance(double distance) {
		this.travelledDistance += distance;
	}
	
	/**
	 * A method to evolve this planetoid with a given amount of time,
	 * as well ass diminishing the radius of this planetoid according to the 
	 * distance travelled, thus diminishing the mass of the planetoid as well.
	 * @param 	duration 	the duration for which the planetoid is evolved.
	 * @post	new.getRadius() == this.getOriginalRadius() - 0.000001 * this.getTravelledDistance()
	 * @effect	incrementTravelledDistance(@seeImplementation) 
	 * @effect	setMass()
	 * @effect	move(duration)
	 * 
	 */
	@Override
	public void evolve(double duration) {
		Vector2 oldPosition = this.getPosition();
		super.evolve(duration);
		double distanceTravelled =Math.abs( oldPosition.getLength() - this.getPosition().getLength());
		
		this.incrementTravelledDistance(distanceTravelled);
		
		this.setRadius(this.getOriginalRadius() - 0.000001 * this.getTravelledDistance());
		
	}

	
	/**
	 * Resolves the collision between this planetoid and another entity.
	 * 
	 * @effect	@seeImplementation
	 */
	@Override
	protected void resolve(Entity entity) {
		if (! entity.isPartOfWorld(this.getWorld()))
			return;
		entity.resolvePlanetoid(this);
		
	}
	/**
	 * @effect	@seeImplementation
	 */
	@Override
	protected void resolveShip(Ship ship) {
		ship.setPositionRandom();
	}

	@Override
	public String toString() {
		return "P" + this.position.toString();
	}
	
	
	
}
