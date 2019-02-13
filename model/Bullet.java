package asteroids.model;

import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
* A class of Bullets, involving shape, movement, position and interaction with
* other ships and bullets.
* 
* @invar	The bullet has bounced less than the maximum amount of times during its life in this world.
* 			|getMaxBouncewall() > getBounceWall()
* @invar 	If the bullet is inside a world, it is not part of a ship, and vice versa.
* 			|(this.getWorld()!= null && this.getOwnerShip() != null) == false
* * 
 * @author Elisabeth Tu (2eBach CW-WTK) & Tim Lebailly (2eBach CW-ELT)
 * @repository https://elisabethtu@bitbucket.org/springtimeli/ogp.git
 *
 */
public class Bullet extends Entity {
	


	/**
	 * Initialize a new Bullet with the given values and a default value of C for the maxSpeed of this Bullet.
	* @param posX
	 *            The horizontal position of the new Bullet.
	 * @param posY
	 *            The vertical position of the new Bullet.
	 * @param speedX
	 *            The horizontal speed of the new Bullet.
	 * @param speedY
	 *            The vertical speed of the new Bullet.
	 * @param radius
	 * 			  The radius of the new Bullet
	 * @post   The maxSpeed of this ship is C.
	 * 		   | this.getMaxSpeed() == C
	 * @effect The given position is set as the position of the new Bullet. 
	 * 		   | setPosition(posX, posY)
	 * @effect The given speed is set as the speed of the new Bullet. 
	 * 		   | setSpeed(speedX, speedY)
	 * @effect The given radius is set as the radius of the new ship. 
	 * 		   | setRadius(radius)
	 */
	public Bullet(double posX, double posY, double speedX, double speedY, double radius) {
		super(posX, posY, speedX, speedY, radius);
	}
	
	/**
	 * Initialize a new Bullet with the given values.
	* @param posX
	 *            The horizontal position of this new Bullet.
	 * @param posY
	 *            The vertical position of this new Bullet.
	 * @param speedX
	 *            The horizontal speed of this new Bullet.
	 * @param speedY
	 *            The vertical speed of this new Bullet.
	 * @param radius
	 * 			  The radius of the new Bullet
	 * @param maxSpeed
	 * 			  The maximum speed of this Bullet
	 * @post   The given maxSpeed is set as the maxSpeed, if this speed is smaller than C
	 * 		   | if	(maxSpeed<=C)
	 * 				then this.getMaxspeed() == maxSpeed
	 * @post   If the given maxSpeed is larger than C, C is set as the maxSpeed of this ship'
	 * 		   | if (maxSpeed > C)
	 * @effect The given position is set as the position of the new Bullet. 
	 * 		   | setPosition(posX, posY)
	 * @effect The given speed is set as the speed of the new Bullet. 
	 * 		   | setSpeed(speedX, speedY)
	 * @effect The given radius is set as the radius of the new ship. 
	 * 		   | setRadius(radius)
	 */
	public Bullet(double posX, double posY, double speedX, double speedY, double radius, double maxSpeed) {
		super(posX, posY, speedX, speedY, radius, maxSpeed);
	}
	
	
	private int bounceWall = 0;
	
	/**
	 * 
	 * @return @seeImplementation
	 */
	public int getBounceWall() {
		return this.bounceWall;
	}
	
	/**
	 * Adds 1 to the bouncewall.
	 * @post @seeImplementation
	 */
	private void incrementBounceWall() {
		this.bounceWall = this.getBounceWall() + 1;
	}
	
	/**
	 * Resets the amount that this bullet has bounce on a wall to 0.
	 * @post @seeImplementation
	 */
	public void resetBounceWall(){
		this.bounceWall = 0;
	}
	
	
	private static int maxBounceWall = 3;
	
	/**
	 * Returns the maximum amount of times a bullet can bounce against a boundary.
	 * @return @seeImplementation
	 */
	public int getMaxBouceWall() {
		return maxBounceWall;
	}
	
	/**
	 * Sets the maximum amount of times a bullet can bounce against a boundary.
	 * @param max
	 * @post @seeImplementation
	 */
	public void setMaxBounceWall(int max) {
		maxBounceWall = max;
	}
	
	
	private static double minRadius = 1;
	
	/**
	 * Returns the minimum radius allowed.
	 * @return @seeImplementation
	 */
	public static double getMinimumRadius() {
		return Bullet.minRadius;
	}
	
	@Override
	public boolean isValidRadius(double value){
		return value >= getMinimumRadius();
	}
	
	private static double density = 7.8E12;
	
	/**
	 * A method that returns the density of bullets.
	 * @return @seeImplementation
	 */
	@Override @Immutable
	public double getDensity() {
		return density;
	}
	
	/**
	 * Terminates  this bullet. If the bullet is on board of a ship or within a world, it is first removed, after which
	 * this bullet is terminated.
	 * @post	|this.getWorld() == null
	 * @post	|this.getOwnerShip == null
	 * @post	|this.isTerminated() == true
	 * 
	 */
	@Override
	public void terminate() {
		if (getOwnerShip() != null) 
			getOwnerShip().removeBullet(this);
		super.terminate();
		
	}
	
	/**
	 * Returns whether this bullet is able to go to the given world.
	 * @return	False if the world of this bullet is not null.
	 * 			|this.getWorld() != null)
	 * @return	False if the distance between this bullet and another entity within the given world is smaller than 0.
	 * 			|if( at least one (Entity entity: world.getEntitySet)
					this.potentialOverlap(entity))
				 then return false
	 * @return	False if the bullet is not within boundaries of the world.
	 * 			|return this.isInBoundary(world))
	 * 			 
	 * 
	 * @return	False if the owner ship is not null
	 * 			|return (this.getOwnerShip() == null)
	 */
	@Override
	public boolean canGoToWorld(World world) {
		if(super.canGoToWorld(world))
			return this.getOwnerShip() == null;
		return false;
	}
	
	private Ship shipOwner = null;
	private Ship firedBy = null;
	
	/**
	 * Returns the ship where the bullet is placed on.
	 * @return @seeImplementation
	 */
	public Ship getOwnerShip(){
		return this.shipOwner;
	}
	/**
	 * Returns the ship that fired the bullet.
	 * @return @seeImplementation
	 */
	public Ship getFiredBy(){
		return this.firedBy;
	}

	public boolean isWithinShip(Ship ship) {
		return this.getDistanceBetweenCenter(ship) <= ship.getRadius()-this.getRadius();
	}
	
	/**
	 * Set the given ship as the owner of the bullet.
	 * @param	ship	 The ship where the bullet is put on.
	 * @post	new.getPosition() = ship.getPosition()
	 * @post	new.getSpeed() = ship.getSpeed()
	 * 
	 */
	@Raw
	protected void setShipAsOwner(Ship ship) throws NullPointerException, 
			IllegalPositionException {
		this.shipOwner = ship;
		this.setPosition(ship.getPosition().getX(), ship.getPosition().getY());
		this.setSpeed(ship.getSpeed().getX(),ship.getSpeed().getY());
	}
	
	
	/**
	 * A method that sets the given ship to the ship that fired this bullet.
	 * @param ship
	 * @post @seeImplementation
	 */
	@Raw
	protected void setFiredBy(Ship ship){
		this.firedBy = ship;

	}
	
	/**
	 * Removes the bullet from the ship.
	 * @post @seeImplementation
	 */
	@Raw
	protected void removeFromShip(){
		this.shipOwner = null;
	}

	
	/**
	 * Resolves the collision between this bullet and an entity.
	 * @post	@seeImplementation
	 * @effect	@seeImplementation
	 */
	@Override
	protected void resolve(Entity entity) {
		if (! entity.isPartOfWorld(this.getWorld()))
			return;
		entity.resolveBullet(this);
	}

	/**
	 * Resolves the collision between this bullet and a ship.
	 * @effect @seeImplementation
	 */
	@Override
	protected void resolveShip(Ship ship) {
		ship.resolveBullet(this);
	}

	/**
	 * Resolves the collision between this bullet and an asteroid.
	 * @effect @seeImplementation
	 */
	@Override
	protected void resolveAsteroid(Asteroid asteroid) {
		this.resolveTwoExplosion(asteroid);
	}

	/**
	 * Resolves the collision between this bullet and a planetoid.
	 * @effect @seeImplementation
	 */
	@Override
	protected void resolvePlanetoid(Planetoid planetoid) {
		this.resolveTwoExplosion(planetoid);
	}

	/**
	 * Resolves the collision between this bullet and a given boundary.
	 * @param	boundary
	 * 			The boundary taking part in the collision
	 * @post	If the bullet has bounced on the maximum allowed walls, the bullet is terminated.
	 * 			| if if (this.getBounceWall() == (this.getMaxBouceWall()))
	 * 				then this.isTerminated() == true;
	 * @post	| if (this.getBounceWall() != (this.getMaxBouceWall())
	 * 				new.getBounceWall() == old.getBounceWall() + 1
	 * @post	If the boundary is the left or right boundary, the horizontal speed of this entity is reversed.
	 * 
	 * 			| if (boundary == 3 || boundary == 1)
					then new.getSpeed().getX()=- old.getSpeed().getX();
	 * @post	If the boundary is the top or bottom boundary, the vertical speed of this entity is reversed.
	 * 			| if (boundary == 2 || boundary == 0)
	 * 				then then new.getSpeed().getY()=- old.getSpeed().Y();
	 * 
	 */
	@Override
	protected void resolve(double boundary) {
		super.resolve(boundary);
		this.incrementBounceWall();
		if (this.getBounceWall() == this.getMaxBouceWall())
				this.terminate();
	}
	
	@Override
	public String toString() {
		return "B" + this.position.toString();
	}
	

}
