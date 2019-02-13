package asteroids.model;

import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw; 

/**
 * An abstract class of entities, involving shape, movement, position and interaction with
 * other entities.
 * 
 * @invar	The radius of each entity must be a valid radius.
 * 			|isValidRadius()
 * @invar	The position of each entity must be a valid position
 * 			|canHaveAsPosition()
 * @invar 	The speed of each entity must be a valid speed
 * 			|canHaveAsSpeed()
 * @invar	Each entity that is part of a world, can be in that world
 * 			|canGoToWorld(world)
 * 
 * @author Elisabeth Tu (2eBach CW-WTK) & Tim Lebailly (2eBach CW-ELT)
 * @repository https://elisabethtu@bitbucket.org/springtimeli/ogp.git
 *
 */

public abstract class Entity  {

	/**
	 * Make a new entity with a given position, speed and radius
	 * @param	posX
	 * 			The horizontal position of this entity.
	 * @param	posY 
	 * 			The vertical position of this entity.
	 * @param	speedX
	 * 			The horizontal speed of this entity.
	 * @param	speedY
	 * 			The vertical speed of this entity.
	 * @param	radius
	 * 			The radius of this entity
	 * @pre 	The positions have to be greater than or equal to zero
	 * 			| posX >= 0 && posY >= 0
	 * @pre		If the entity is within a world, the positions have to be within the boundaries of the world.
	 * 			| if (this.getWorld() != null)
	 * 				then posX + 0.99 * this.getRadius()  <= world.getSizeX()
	 * 					&& posY + 0.99 * this.getRadius()  <= world.getSizeY()
	 * @pre		The radius of the entity is larger than the minimum radius allowed.
	 * 			| radius > minRadius
	 * @post	this.getPosition() == new Vector2(posX,posY)
	 * @post	this.getSpeed() == new Vector2(speedX,speedY)
	 * @post	this.getRadius() == radius
	 * @throws	IllegalPositionException
	 * 			|!canHaveAsPosition()
	 * @throws	IllegalRadiusException
	 * 			|!canHaveAsRadius()
	 */
	public Entity(double posX, double posY, double speedX, double speedY, double radius) throws IllegalPositionException, 
			IllegalRadiusException {
		this(posX,posY,speedX,speedY,radius,C);
		
		
	}
	
	
	/**
	 * Make a new entity with a given position, speed, radius and maxSpeed.
	 * @param	posX
	 * 			The horizontal position of this entity.
	 * @param	posY 
	 * 			The vertical position of this entity.
	 * @param	speedX
	 * 			The horizontal speed of this entity.
	 * @param	speedY
	 * 			The vertical speed of this entity.
	 * @param	radius
	 * 			The radius of this entity
	 * @param 	maxSpeed
	 * 			The maximum speed of this entity
	 * @pre 	The positions have to be greater than or equal to zero
	 * 			| posX >= 0 && posY >= 0
	 * @pre		If the entity is within a world, the positions have to be within the boundaries of the world.
	 * 			| if (this.getWorld() != null)
	 * 				then posX + 0.99 * this.getRadius()  <= world.getSizeX()
	 * 					&& posY + 0.99 * this.getRadius()  <= world.getSizeY()
	 * @pre		The radius of the entity is larger than the minimum radius allowed.
	 * 			| radius > minRadius
	 * @post	this.getPosition() == new Vector2(posX,posY)
	 * @post	this.getSpeed() == new Vector2(speedX,speedY)
	 * @post	this.getRadius() == radius
	 * @post	if maxSpeed <= C
	 * 			then this.getMaxSpeed = maxSpeed
	 * @post	if maxSpeed > C
	 * 			then this.getMaxspeed = C
	 * @throws	IllegalPositionException
	 * 			|!canHaveAsPosition()
	 * @throws	IllegalRadiusException
	 * 			|!canHaveAsRadius()
	 */
	public Entity(double posX, double posY, double speedX, double speedY, double radius, double maxSpeed) throws IllegalPositionException, 
		IllegalRadiusException {
		if ( maxSpeed < C)
			this.MaxSpeed = maxSpeed;
		else
			this.MaxSpeed = C;
		setPosition(posX, posY); 
		setSpeed(speedX, speedY);
		
		if (!isValidRadius(radius))
			throw new IllegalRadiusException(radius);
		this.radius = radius;
	

	}
	
	protected Vector2 position;
	
	/**
	 * A method that returns the position of the entity.
	 * @return	@seeImplementation
	 */
	@Basic
	public Vector2 getPosition() {
		return this.position;
	}

	/**
	 * A method that sets the position of the entity to the given values.
	 * 
	 * 
	 * @param 	positionX	
	 * 			The horizontal position of the entity.
	 * @param 	positionY 
	 * 			The vertical position of the entity.
	 * 			
	 * @pre 	The given positions must be a real number and not infinite.
	 * 			| canHaveAsPosition(positionX,positionY)
	 * @pre 	If the entity is within a world, it must lie fully within the boundaries of the world.
	 * 			| canHaveAsPosition(positionX,positionY)
	 * 
	 * @post 	| new.getPosition() == [PositionX, positionY]
	 * @throws 	IllegalPositionException
	 * 			|!canHaveAsPosition()
	 */
	@Basic
	public void setPosition(double positionX, double positionY) throws IllegalPositionException {
		
		if (!this.canHaveAsPosition(positionX,positionY))
			throw new IllegalPositionException(positionX + positionY);
		
		if (this.getWorld() != null) {
			this.getWorld().updateEntityMap(new Vector2(positionX, positionY), this);
		}
		
		this.position = new Vector2(positionX, positionY);	
	}
	
	
	/**
	 * Returns whether the given positions are valid positions.
	 * @param positionX
	 * @param positionY
	 * @return False if the given positions are not real numbers or infinite.
	 * 		   | @seeImplementation
	 * @return False if the entity lies within a world and the positions are not within the boundaries of that world.
	 * 		   | if !isInBoundary(this.getWorld(), positionX, positionY) 
	 * 			 then return false
	 * @return True  in all other cases.
	 */
	public boolean canHaveAsPosition(double positionX, double positionY) {
		if (Double.isNaN(positionX) || Double.isNaN(positionY))
			return false;
		if (this.getWorld() != null) {
			if (!this.isInBoundary(this.getWorld(), positionX, positionY) )
				return false;
		}
		return true;
	}
	
	
	
	/**
	 * A constant that describes the speed of light
	 */
	public static final double C = 300000;
	private final double MaxSpeed;
	
	protected Vector2 speed;

	/**
	 * @return Returns the maximum speed of the ship.
	 * 		   |@seeImplementation
	 */
	@Basic @Immutable
	public double getMaxSpeed() {
		return this.MaxSpeed;
	}

	
	/**
	 * A method that returns the speed of the entity.
	 * @return @seeImplementation
	 */
	public Vector2 getSpeed() {
		return this.speed;
	}

	/**
	 * A method that sets the horizontal and vertical speeds of the entity to the given speeds.
	 *
	 * @param	speedX
	 *          The horizontal velocity of the entity.
	 * @param	speedY
	 *          The vertical velocity of this entity.
	 * @post	If the total speed of this entity (= the squared root of the sums of the
	 *       	squares of the two velocities) does not exceed the maximum speed,
	 *       	the new velocities are equal to the given velocities 
	 *       	| if (lengthVector(speedX, speedY) <= MaxSpeed 
	 *       	then new.getSpeed().getX() == speedX
	 *       	&& new.getSpeed().getY() == speedY
	 * @post	If the speed of this entity exceeds the maximum speed, the new
	 *       	velocity equals the maximum speed, with both the x and y component
	 *       	decreasing, so that the direction is kept. 
	 *       	| if !canHaveAsSpeed()
	 *       	then new.getSpeed().getX() == speedX*MaxSpeed/lengthVector(speedX, speedY) && 
	 *       	new.getSpeed().getY() == speedY*MaxSpeed/lengthVector(speedX, speedY)
	 */
	@Basic
	public void setSpeed(double speedX, double speedY) {
		
		if (Double.isInfinite(speedX) || Double.isInfinite(speedY) || Double.isNaN(speedX) || Double.isNaN(speedY)) {
			this.speed = new Vector2(0, 0);
		}
		else {
			double totalSpeed = lengthVector(speedX, speedY);
			if (canHaveAsSpeed(totalSpeed)) {
				this.speed = new Vector2(speedX, speedY);
			} 
			
			else {
				this.speed = new Vector2(speedX * getMaxSpeed() / totalSpeed, speedY * getMaxSpeed() / totalSpeed);
			}
		}

	}
	

	/**
	 * Checks whether the given speed is a valid speed for an entity.
	 * 
	 * @param	speed
	 *          The speed to check.
	 * @return	True if and only if the given speed is smaller than the maximum
	 *        	allowed speed. 
	 *        	| return speed <= getMaxSpeed()
	 * @return	False if the given speed is not a number or infinite..
	 *			| if isInfinite(speed) || Double.isNaN(speed)
	 *			  then return false
	 */
	public boolean canHaveAsSpeed(double speed) {
		if (Double.isInfinite(speed) || Double.isNaN(speed))
			return false;
		return Math.abs(speed) <= getMaxSpeed();
	}
	
	/**
	 * A method that returns the 2-norm of the given components of a vector.
	 * 
	 * @param	component1
	 *            The first given component of the vector.
	 * @param	component2
	 *            The second given component of the vector.
	 * @return	The square root of the sum of the the two squared speeds. 
	 * 			|result == Math.sqrt(Math.pow(component1, 2) +
	 *         		Math.pow(component2,2))
	 */
	public double lengthVector(double component1, double component2) {
		return Math.sqrt(Math.pow(component1, 2) + Math.pow(component2, 2));
	}
	

	private  final double radius;
		
	/**
	 * A method that returns the radius of the entity.
	 * @return Returns the radius of the entity.
	 * 		   |@seeImplementation
	 */
	@Basic 
	public double getRadius() {
		return this.radius;
	}

//	/**
//	 * A method that sets the radius of the entity to the given value if the value is acceptable.
//	 * 
//	 * @param	radius
//	 * 			The new radius of the entity.
//	 * @pre		the radius must be a valid radius, this means a radius larger than the minimum radius.
//	 * 			|isValidRadius(radius)
//	 * @post	The radius of the entity is equal to the given radius.
//	 * 			| new.getRadius() == radius
//	 * @throws	IllegalArgumentException
//	 * 			The given radius is not a valid radius for a entity.
//	 * 			| ! isValidRadius(radius)
//	 */
//	@Basic @Immutable
//	public void setRadius(double radius) throws IllegalRadiusException {
//		if (!isValidRadius(radius))
//			throw new IllegalRadiusException(radius);
//		this.radius = radius;
//	}

	/**
	 * Checks whether the given radius is a valid radius.
	 * 
	 * @param	value
	 *          The value of the radius to check.
	 * @return	True if and only if the given radius is larger than or equal to
	 *         	the minimum radius allowed. 
	 *         	| result == value >=	getMinimumRadius()
	 */
	public abstract boolean isValidRadius(double value);
		
	/**
	 * A method that returns the density of the entity
	 * @return @seeImplementation
	 */
	public abstract double getDensity();
	
	/**
	 * A method that returns the mass of the entity
	 * @return @seeImplementation
	 */
	@Basic
	public double getMass(){
		return 4.0/3.0 * Math.PI * getDensity() * Math.pow(this.getRadius(), 3);
	}
	
	
	private boolean isTerminated = false;
			
			
	/**
	 *  Method that terminates an entity. 
	 *  @post	|this.isTerminated() == true
	 *	@throws	IllegalStateException
	 */
	@Basic
	public void terminate() throws IllegalStateException {
		if (this.getWorld() != null)
			this.getWorld().removeEntity(this);
			
		this.isTerminated = true;
	}
	
	/**
	 * A method that returns whether or not this entity is terminated
	 * @return True if the entity is terminated.
	 * @return False if the entity is not terminated.
	 * 		   | @seeImplementation
	 */
	public boolean isTerminated(){
		return this.isTerminated;
	}
	
	
	/**
	 * A method that moves the entity over a certain distance. 
	 * This method sets the entity to a new position, which is calculated
	 * with a given duration, the velocity of the entity and its old position.
	 * 
	 * @param	duration
	 *       	The duration the entity moves with its velocity
	 * @pre		The duration has to be a valid duration.
	 * @post	The new position of the entity is the old position added to the velocity vector multiplied 
	 * 			with the given duration
	 * 			| new.getPosition() == {old.getPosition().getX()+ this.getSpeed().getX()*duration, old.getPosition().getY() + this.getSpeed().getY()*duration}
	 * @throws	IllegalDurationException
	 *          The given duration is not a valid duration. 
	 *          | !isValidDuration(duration)
	 */
	public void move(double duration) throws IllegalDurationException, IllegalPositionException {
		if (!isValidDuration(duration))
			throw new IllegalDurationException(duration);
		setPosition(this.getPosition().getX() + this.getSpeed().getX() * duration, 
				this.getPosition().getY() + this.getSpeed().getY() * duration);
	}

	protected World world = null;
	
	/**
	 * A method that sets the given world as the world of this entity
	 * @param 	world
	 * 			The world this entity is being put in
	 * @post	| This.getWorld() == world
	 * 
	 */
	@Raw
	protected void setWorld(World world) {
		this.world = world;
	}
	
	/**
	 * Remove the world of this entity.
	 * @param world
	 */
	@Raw
	protected void removeWorld(World world) {
		this.world = null;
	}
	
	
	/**
	 * Check if this entity is a part of a world.
	 * @param world
	 * @return 	True if the given world is set as the world of the entity.
	 * 			| return this.world == world
	 * @return 	False if the given world is not set as the world of the entity.
	 * 			|!this.world == world
	 */
	public boolean isPartOfWorld(World world) {
		if (this.world == world)
			return true;
		return false;
	}
	
	/**
	 * A method that returns the world of the entity.
	 * @return @seeImplementation
	 */
	public World getWorld() {
		return this.world;
	}
	
	
	/**
	 * A method that checks whether this entity can go the given world.
	 * @param	world
	 * 			The given world.
	 * @return	False if the world of this entity is not null.
	 * 			| if (this.getWorld() != null)
	 * 			 then return false
	 * @return	False if the entity would overlap with an entity within the given world.
	 * 			| if (at least one (Entity entity: world.getEntitySet)
					this.potentialOverlap(entity))
				then return False
	 * @return	False if the entity is not within the boundaries of the world.
	 * 			| if (!this.isInBoundary(world))
	 * 			then return false
	 * 
	 */
	public boolean canGoToWorld(World world) throws NullPointerException {
		Set<Entity> entitySet = world.getEntitySet();
		for (Entity entity: entitySet) {
			if (this.significantPotentialOverlap(entity))
				return false;
		}
		if (this.getWorld() != null)
			return false;
		if (!this.isInBoundary(world))
			return false;
		return true;
	}
	
	
	/**
	 * A method that checks whether this entity is (apparently) within the boundaries of its world.
	 * @param	world
	 * 			The world this entity is in.
	 * @return	True if the entity is within boundaries with its own positions.
	 * 			| return isInBoundary(world,this.getPosition().getX(),this.getPosition().getY());
	 */
	public boolean isInBoundary(World world) {
		return isInBoundary(world,this.getPosition().getX(),this.getPosition().getY());
		}
	
	/**
	 * A method the checks whether this entity would be within boundaries of its world with the given position.
	 * @param	world 
	 * 			The world this entity is in
	 * @param	posX 
	 * 			The hypothetical horizontal position
	 * @param	posY 
	 * 			The hypothetical vertical position
	 * @return 	Whether the given position in addition to the radius of the entity is not larger than the size of the world
	 * 			and the given position is  not smaller than the radius of the entity.
	 * 			|@seeImplementation
	 */
	public boolean isInBoundary(World world, double posX, double posY) {
		if (world.getSizeX() - posX < 0.99 * this.getRadius() ||
				posX < this.getRadius() * 0.99 ||
				world.getSizeY() - posY < 0.99 * this.getRadius() ||
				posY < this.getRadius() * 0.99)
			return false;
		else
			return true;
	}

	/**
	 * A method that checks whether this entity overlaps with another given entity.
	 * 
	 * @param	entity
	 *          The entity with whom the overlap will be defined.
	 * @return	Whether the distance between the entity and the other entity is smaller than 0,
	 * 			and the worlds of the two entities are the same 
	 * 			| return this.getWorld() == entity.getWorld() && getDistanceBetween(entity) <= 0
	 * @throws	NullPointerException
	 *          The other entity is null. 
	 *          | entity == null
	 */
	public boolean overlap(Entity entity) throws NullPointerException {
		return getDistanceBetween(entity) <= 0 && entity.isPartOfWorld(this.getWorld());
	}
	
	/**
	 * Returns whether there is a potential overlap between this entity and a given entity
	 * @param 	entity
	 * 			The entity to check the potential overlap with.
	 * @return	Whether the distance between this entity and the other entity is smaller that 0.
	 * 			|@seeImplementation
	 * @throws	NullPointerException
	 * 			The other entity is null.
	 * 			| entity == null
	 */
	public boolean significantPotentialOverlap(Entity entity) throws NullPointerException {
		double distance = getDistanceBetweenCenter(entity);
		return distance <= 0.99 * (this.getRadius() + entity.getRadius());
	}
	
	/**
	 * Checks whether there is a significant overlap between this entity and a given entity.
	 * @param	entity
	 * 			The entity to check the potential overlap with.
	 * @return	Whether the distance between the two centres of the entities 
	 * 			is smaller than 99% of the sum of the radii of the entities.
	 * 			|@seeImplementation
	 * @throws	NullPointerException
	 * 			The other entity is null.
	 * 			| entity == null
	 */
	public boolean significantOverlap(Entity entity) throws NullPointerException {
		if (!entity.isPartOfWorld(this.getWorld()))
			return false;
		double distance = getDistanceBetweenCenter(entity);
		return distance <= 0.99 * (this.getRadius() + entity.getRadius());
	}

	/**
	 * A method that returns the distance between this entity and another given entity.
	 * 
	 * @param 	entity
	 *        	The other entity used to calculate the distance between.
	 * @return	the distance between the two entities if the distance between the two centres is larger
	 * 			than radius of the smallest ship.
	 * 			| if getDistanceBetweenCenter(entity) > Min(this.getRadius(), entity.getRadius())
	 * 				then result == distanceBetweenCenter - this.getRadius() - entity.getRadius()
	 * 
	 * @return 	If the distance between the two centres is not larger than the radius of the smallest ship,
	 * 			 the method returns the negative distance minus two times the largest radius.
	 * 			| if getDistanceBetweenCenter(entity) <= Min(this.getRadius(), entity.getRadius())
	 * 				then result == -getDistanceBetweenCenter(entity) + shortRadius - longRadius
	 * @throws	NullPointerException
	 *          The other entity is not effective. 
	 *          | entity == null
	 */
	public double getDistanceBetween(Entity entity) throws NullPointerException {
		if (this.equals(entity))
			return 0;
		double longRadius, shortRadius;
		if (this.getRadius() >= entity.getRadius()) {
			longRadius = this.getRadius();
			shortRadius = entity.getRadius();
		}
		else {
			shortRadius = this.getRadius();
			longRadius = entity.getRadius();
		}
		double distanceBetweenCenter = getDistanceBetweenCenter(entity);
		
		double distanceToReturn = distanceBetweenCenter - shortRadius - longRadius;
		if (distanceBetweenCenter > shortRadius)
			return distanceToReturn;
		else
			return - distanceToReturn - 2 * longRadius;
	}
	
	/**
	 * A method to calculate the distance between the centres of this entity and a given entity.
	 * @param	entity
	 * 			the entity to calculate the distance with.
	 * @return	The length of the vector of the difference between horizontal positions and the difference between vertical positions.
	 * 			|@seeImplementation
	 */
	public double getDistanceBetweenCenter(Entity entity) {
		double distanceCenterX = Math.abs(this.getPosition().getX() - entity.getPosition().getX());
		double distanceCenterY = Math.abs(this.getPosition().getY() - entity.getPosition().getY());
		return lengthVector(distanceCenterX, distanceCenterY);
	}

	/**
	 * A method to calculate how much time it takes in order for this entity to collide
	 * with another given entity. The time returned is always a positive number.
	 * 
	 * @param	entity
	 * 			The entity used to calculate the collision time	
	 * @return 	If the two entities are not within the same world or this entity is not within a world,
	 * 			the collision with the two entities and the value of positive infinity is returned.
	 * 			|if (!this.isPartOfWorld(entity.getWorld()) || this.getWorld() == null)
					then result == new Collision(this, entity, Double.POSITIVE_INFINITY);
	 * @return	If existent, the collision of this entity, the other entity 
	 * 			and the solution of a quadratic equation,with the coefficients being:
	 * 			The difference of the speeds of the entities squared,
	 * 			the difference of the speeds of the entities times the difference of the positions times 2,
	 * 			the difference of positions of the entities squared.
	 * 			| @seeImplementation
	 * 
	 * @return 	If the equation has no solution, the ships will never 
	 * 			collide and the collision with the two entities and the value of infinity is returned.
	 * 			| @seeImplementation
	 * 
	 * @throws	NullPoinodterException
	 * 			The other entity is null. 
	 *          | otherEntity == null
	 * @throws	IllegalArgumentException
	 * 			The two entities are overlapping.
	 * 			| this.significantOverlap(entity)
	 */
	public Collision getCollisionWithEntity(Entity entity) throws NullPointerException, IllegalStateException {
		if (this.significantOverlap(entity))
			throw new IllegalStateException();
		
		if (!this.isPartOfWorld(entity.getWorld()) || this.getWorld() == null)
			return new Collision(this, entity, Double.POSITIVE_INFINITY);
	
		if ((entity.getSpeed().getX() - this.getSpeed().getX()) * (entity.getPosition().getX() - this.getPosition().getX())
				+ (entity.getSpeed().getY() - this.getSpeed().getY()) * (entity.getPosition().getY() - this.getPosition().getY()) >= 0)
			return new Collision(this, entity, Double.POSITIVE_INFINITY);
	
		double a = Math.pow(this.getSpeed().getX() - entity.getSpeed().getX(), 2)
				+ Math.pow(this.getSpeed().getY() - entity.getSpeed().getY(), 2);
		double b = 2 * (this.getSpeed().getX() - entity.getSpeed().getX()) * (this.getPosition().getX() - entity.getPosition().getX())
				+ 2 * (this.getSpeed().getY() - entity.getSpeed().getY()) * (this.getPosition().getY() - entity.getPosition().getY());
		double c = Math.pow(this.getPosition().getX() - entity.getPosition().getX(), 2)
				+ Math.pow(this.getPosition().getY() - entity.getPosition().getY(), 2)
				- Math.pow(this.getRadius() + entity.getRadius(), 2);
		double rho = Math.pow(b, 2) - 4 * a * c;
	
		if (rho <= 0)
			return new Collision(this, entity, Double.POSITIVE_INFINITY);
	
		else {
			double time = (-b - Math.sqrt(rho)) / 2 / a;
			if (time < 0)
				return new Collision(this, entity, 0);
			return new Collision(this, entity, time);
			
		}
			
	}

	/**
	 * A method that returns the position in which this entity will collide with another entity.
	 * @param	collision
	 * 			The collision with the time and other entity with which the collision will take place
	 * @return	If the collision will take place, the method returns the distance between the two centres 
	 * 			of the ships at the time of collision, multiplied by the ratio of the two radii,
	 * 			added to the  position of this entity at the time of collision.
	 * 			| result == [ this.getPosition().getX() + time * this.getSpeed().getX()
	 * 							- otherEntity.getPosition().getX() + time * otherEntity.getSpeed().getX()
	 * 							* this.getRadius() / (otherEntity.getRadius() + this.getRadius())
	 * 							+ this.getPosition().getX() + time * this.getSpeed().getX() ,
	 * 							this.getPosition().getY() + time * this.getSpeed().getY()
	 * 							- otherEntity.getPosition().getY() + time * otherEntity.getSpeed().getY()
	 * 							* this.getRadius() / (otherEntity.getRadius() + this.getRadius())
	 * 							+ this.getPosition().getY() + time * this.getSpeed().getY() ]
	 * 	@result	If the collision will never take place, the method returns the value Null
	 * 			| if (getTimeToCollision(otherEntity) == infinity)
	 * 				then collisionPosition = Null
	 * @throws	NullPointerException
	 * 			The other entity is null. 
	 *          | otherEntity == null
	 */
	public double[] getCollisionPosition(Collision collision) throws NullPointerException, 
			IllegalArgumentException {
		double time = collision.getTime();
		Entity otherEntity = collision.getEntity2();
		if (time == Double.POSITIVE_INFINITY)
			return null;
		double[] positionPrime = { this.getPosition().getX() + time * this.getSpeed().getX(),
				this.getPosition().getY() + time * this.getSpeed().getY() };
		double[] positionotherEntity = { otherEntity.getPosition().getX() + time * otherEntity.getSpeed().getX(),
				otherEntity.getPosition().getY() + time * otherEntity.getSpeed().getY() };
		double[] primeTootherEntityVector = { positionotherEntity[0] - positionPrime[0],
				positionotherEntity[1] - positionPrime[1] };
		double primeTootherEntityRadiusRatio = this.getRadius() / (otherEntity.getRadius() + this.getRadius());
		double[] collisionPosition = { primeTootherEntityVector[0] * primeTootherEntityRadiusRatio + positionPrime[0],
				primeTootherEntityVector[1] * primeTootherEntityRadiusRatio + positionPrime[1] };
		return collisionPosition;
	}
	
	
	
//	  0
//	|----|
//3	|    |  1
//	|----|
//	   2
////////////////////TOT HIER HELEMAAL IN ORDE	
	/**
	 * A method to calculate the time it takes for this entity to collide with a boundary of the world it is in,
	 * as well as the boundary itself. 0 is considered the top boundary, 1 the right, 2 the bottom and 3 the left boundary.
	 * @return	if the entity is not within a world or has no speed, a new Collision with this entity, positive infinity 
	 * 			as time and the non-existing wall 4.
	 * 			| @seeImplementation
	 * @return	if this entity moves left and up the collision with the lowest boundaryCollisionTime 
	 * 			between left and upper border is returned
	 * 			| getLowestBoundryCollisionTime({3,0})
	 * @return	if this entity moves left and down the collision with the lowest boundaryCollisionTime 
	 * 			between left and lower border is returned
	 * 			| getLowestBoundryCollisionTime({3,2})
	 * @return	if this entity moves right and up the collision with the lowest boundaryCollisionTime 
	 * 			between left and upper border is returned
	 * 			| getLowestBoundryCollisionTime({1,0})
	 * @return	if this entity moves right and down the collision with the lowest boundaryCollisionTime 
	 * 			between right and lower border is returned
	 * 			| getLowestBoundryCollisionTime({1,2})
	 * 
	 * @throws IllegalStateException
	 * 			throws this exception when this entity is not within the boundaries of the world.

	 * @throws NullPointerException
	 */
	public Collision getCollisionBoundary() throws IllegalStateException, 
				NullPointerException {
		if (this.getWorld() == null || (this.getSpeed().getX() == 0 && this.getSpeed().getY() == 0))
			return new Collision(this, Double.POSITIVE_INFINITY, 4);
		if (!this.isInBoundary(world))
			throw new IllegalStateException();

		double[] boundaryList = new double[] {3, 2};
		
		if (this.getSpeed().getX() >= 0) {
			boundaryList[0] = 1; 
			}
		
		if (this.getSpeed().getY() >= 0) {
			boundaryList[1] = 0; 
			}
		
		return getLowestBoundaryCollisionTime(boundaryList);
	}
	
	
	/**
	 * Returns the Collision with the lowest time, calculated with the given boundaryList.
	 * @param 	boundaryList the list with the boundaries where the Collisions have to be calculated.
	 * 			
	 * @return  The Collision with the lowest time, calculated with the given boundaryList.
	 * 			| return new Collision(this, @seeImplementation, @seeImplementation)
	 * 
	 * @throws 	IllegalArgumentException
	 */
	private Collision getLowestBoundaryCollisionTime(double[] boundaryList) throws IllegalArgumentException{
		if (boundaryList.length != 2 || (boundaryList[0] != 3 && boundaryList[0]!=1) || (boundaryList[1] != 0 && boundaryList[1]!=2)){
			throw new IllegalArgumentException();
		}
		double xCorner = 0;
		double yCorner = 0;
		if (this.getSpeed().getX() >= 0) {
			xCorner = this.getWorld().getSizeX();
		}
		if (this.getSpeed().getY() >= 0) {
			yCorner = this.getWorld().getSizeY();
		}
		double timeX = Math.abs((Math.abs(this.getPosition().getX() - xCorner) - this.getRadius()) / this.getSpeed().getX());
		double timeY = Math.abs((Math.abs(this.getPosition().getY() - yCorner) - this.getRadius()) / this.getSpeed().getY());
		if (timeX > timeY) {
			return new Collision(this, timeY, boundaryList[1]);
		}
		else
			return new Collision(this, timeX, boundaryList[0]);
	}
		
	
//	  0
//	|----|
//3	|    |  1
//	|----|
//	   2
	/**
	 * A method that calculates the position of the given Collision.
	 * @param	collision
	 * @return	null when this entity is not within a world or the time of the Collision is infinite.
	 * 			| if (this.getWorld() == null || collision.getTime == infinity)
	 * `			then return null
	 * @return	The position where the collision will take place.
	 * 			| @seeImplementation
	 * 
	 * @throws IllegalStateException
	 * 			throws this exception when this entity is not within the boundaries of the world.
	 */
	public double[] getCollisionPositionBoundary(Collision collision) throws IllegalStateException {
		if (this.getWorld() == null)
			return null;
		if (!this.isInBoundary(this.getWorld()))
			throw new IllegalStateException();
		double time = collision.getTime();
		if (Double.isInfinite(time))
			return null;
		double[] cornerValue = new double[] {this.getWorld().getSizeY(), this.getWorld().getSizeX(), 0, 0};
		int boundary = (int)collision.getBoundary();
		if (boundary == 1 || boundary == 3)
			return new double[] {cornerValue[boundary], this.getPosition().getY() + time * this.getSpeed().getY()};
		else
			return new double[] {this.getPosition().getX() + time * this.getSpeed().getX(), cornerValue[boundary]};
	}
	
	
	
	
	/**
	 * Resolves the collision between this entity and a given boundary.
	 * @param	boundary
	 * 			The boundary taking part in the collision
	 * 
	 * @post	If the boundary is the left or right boundary, the horizontal speed of this entity is reversed.
	 * 			| if (boundary == 3 || boundary == 1)
					then new.getSpeed().getX()=- old.getSpeed().getX();
	 * @post	If the boundary is the top or bottom boundary, the vertical speed of this entity is reversed.
	 * 			| if (boundary == 2 || boundary == 0)
	 * 				then then new.getSpeed().getY()=- old.getSpeed().Y();
	 */
	protected void resolve(double boundary) {
		if (boundary == 3 || boundary == 1)
			this.setSpeed(-this.getSpeed().getX(), this.getSpeed().getY());
		else 
			this.setSpeed(this.getSpeed().getX(), -this.getSpeed().getY());
	}
	
	/**
	 * Evolves this entity with a given amount of time
	 * @param	time
	 * 			The amount of time the entity is evolved with
	 * @effect	The entity is moved with the given amount of time
	 * 			| @seeImplementation
	 */
	@Raw
	protected void evolve(double time) {
		this.move(time);
	}
	
	/**
	 * Checks whether the given value for the duration is in the allowed range.
	 * This method is meant to check whether the duration by which the entity is moved is valid.
	 * @param	duration
	 *          The value of the duration to check.
	 * @return	False if the duration is not a real number or infinite.
	 * 			| if (Double.isInfinite(duration) || Double.isNaN(duration))
					then return false
	 * @return	True if and only if the given duration is greater than 0. 
	 * 			| result == duration >= 0
	 */
	public static boolean isValidDuration(double duration) {
		if (Double.isInfinite(duration) || Double.isNaN(duration))
			return false;
		return duration >= 0;
	}
	
	
	/**
	 * Resolves the collision with this entity and the given entity.
	 * 
	 * @param	entity
	 * 			The entity this entity collides with
	 * @post	
	 */
	protected abstract void resolve(Entity entity);
	
	
	/**
	 * Resolves the collision between this entity and a bullet.
	 * @param	bullet
	 * @post	| !bullet.isPartOfWorld(old.bullet.getWorld())
	 */
	protected void resolveBullet(Bullet bullet) {
		this.resolveTwoExplosion(bullet);
	}
	
	
	/**
	 * Resolves the collision between this entity and a bullet.
	 * @param ship
	 */
	protected abstract void resolveShip(Ship ship);
	
	/**
	 * Resolves the collision between this entity and an asteroid.
	 * @param asteroid
	 */
	protected abstract void resolveAsteroid(Asteroid asteroid);
	
	/**
	 * Resolves the collision between this entity and a planetoid.
	 * @param planetoid
	 */
	protected abstract void resolvePlanetoid(Planetoid planetoid);
	
	/**
	 * Resolves a collision by terminating this entity and the given entity.
	 * @param	entity
	 * @post	both entities are terminated.
	 * 			| this.isTerminated() && bullet.isTerminated()
	 * @effect	| @seeImplemenation
	 */
	protected void resolveTwoExplosion(Entity entity) {
		this.terminate();
		entity.terminate();
	}
	
	
	/**
	 * Resolves a collision by making the two entities bounce off of each other.
	 * 
	 * @param	entity
	 * @post	The two entities are moving away from each other
	 * 			| (new.this.getSpeed.getX() THEY CANT BOTH BET TRAVELING IN THE SAME DIRECTION!!
	 */
	///////////////////////////////TOOOOOOODDDDDDDDDDDDDOOOOOOOOOOOOOOOOOO////////////
	protected void resolveBounceEntity(Entity entity) {
		double sumRadii = this.getRadius() + entity.getRadius();
		double J = 2 * this.getTotalMass() * entity.getTotalMass() *
				( (this.getSpeed().getX() - entity.getSpeed().getX()) * (this.getPosition().getX() - entity.getPosition().getX()) +
				  (this.getSpeed().getY() - entity.getSpeed().getY()) * (this.getPosition().getY() - entity.getPosition().getY()) ) /
				sumRadii / (this.getTotalMass() + entity.getTotalMass());
		double Jx = J * (this.getPosition().getX() - entity.getPosition().getX()) / sumRadii;
		double Jy = J * (this.getPosition().getY() - entity.getPosition().getY()) / sumRadii;
		entity.setSpeed(entity.getSpeed().getX() + Jx / entity.getTotalMass(), 
					    entity.getSpeed().getY() + Jy / entity.getTotalMass());
		this.setSpeed(this.getSpeed().getX() - Jx / this.getTotalMass(), 
			    	  this.getSpeed().getY() - Jy / this.getTotalMass());
	}
	
	/**
	 * A method that returns the total mass of this entity
	 * @return	the total mass of this entity.
	 * 			result >= this.getMass()
	 */
	public double getTotalMass() {
		return this.getMass();
	}

	
}

