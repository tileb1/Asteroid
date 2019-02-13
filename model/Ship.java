package asteroids.model;


import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import asteroids.program.Program;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of ships, involving shape, movement, position and interaction with
 * other ships.
 * 
 * @invar	The orientation of each ship must be a valid orientation
 * 			|canHaveAsOrientation
 * @invar 	The mass of each ship must be a valid mass.
 * 			|isValidMass()
 * @invar	All bullets boarded on a ship can be on this ship
 * 			| for (bullet in bullets)
 * 				canHaveAsBullet(bullet)
 * 
 * 
 * @author Elisabeth Tu (2eBach CW-WTK) & Tim Lebailly (2eBach CW-ELT)
 * @repository https://elisabethtu@bitbucket.org/springtimeli/ogp.git
 *
 */
public class Ship extends Entity {

	/**
	 * Initialize a new ship with default values for the radius, orientation,
	 * position, speed, mass and maxSpeed.
	 * 
	 * @post   C is set as the maxSpeed of this new ship
	 * 		   | this.getMaxSpeed() == C
	 * @effect 0 is set as the position of this new ship. 
	 * 		   | setPosition(0,0)
	 * @effect 0 is set as the speed of this new ship. 
	 * 		   | setSpeed(0,0)
	 * @effect 0 orientation is set as this orientation of the new ship.
	 *         | setOrientation(0)
	 * @effect The minimal radius is set as this radius of the new ship. 
	 * 		   | setRadius(minRadius)
	 * @effect The minimal mass is set as this mass of the new ship.
	 * 		   | setMass(minMass)
	 */
	public Ship() {
		this(0, 0, 0, 0, 0, minRadius, 0,C);
	}
	
	/**
	 * Initialize a new ship with the given values for position, speed, orientation, radius and mass.
	 * The value of the maxSpeed is set to the default value of C.
	 * 
	 * @param posX
	 *            The horizontal position of this new ship.
	 * @param posY
	 *            The vertical position of this new ship.
	 * @param speedX
	 *            The horizontal speed of this new ship.
	 * @param speedY
	 *            The vertical speed of this new ship.
	 * @param orientation
	 *            The orientation of this new ship.
	 * @param radius
	 *            The radius of this new ship.
	 * @param mass
	 * 			  The mass of this new ship
	 * @post   The maxSpeed of this ship is C.
	 * 		   | this.getMaxSpeed() == C
	 * @effect The given position is set as the position of this new ship. 
	 * 		   | setPosition(posX, posY)
	 * @effect The given speed is set as the speed of this new ship. 
	 * 		   | setSpeed(speedX, speedY)
	 * @effect The given orientation is set as the orientation of this new ship.
	 *         | setOrientation(orientation)
	 * @effect The given radius is set as the radius of this new ship. 
	 * 		   | setRadius(radius)
	 * @effect The given mass is set as the mass of this new ship.
	 * 		   | setMass(mass)
	 * @effect The given mass is set as the total mass of this new ship.
	 * 		   | setTotalMass(mass)]
	 * 
	 */
	public Ship(double posX, double posY, double speedX, double speedY, double orientation, double radius, double mass) throws IllegalRadiusException,
			IllegalPositionException {
		this(posX, posY, speedX, speedY,orientation, radius,mass,C);

	}

	/**
	 * Initialize a new ship with the given values.
	 * 
	 * @param posX
	 *            The horizontal position of this new ship.
	 * @param posY
	 *            The vertical position of this new ship.
	 * @param speedX
	 *            The horizontal speed of this new ship.
	 * @param speedY
	 *            The vertical speed of this new ship.
	 * @param orientation
	 *            The orientation of this new ship.
	 * @param radius
	 *            The radius of this new ship.
	 * @param mass
	 * 			  The mass of this new ship
	 * @param maxSpeed
	 * 			  The maximum speed of this ship
	 * @post   The given maxSpeed is set as the maxSpeed, if this speed is smaller than C
	 * 		   | if	(maxSpeed<=C)
	 * 				then this.getMaxspeed() == maxSpeed
	 * @post   If the given maxSpeed is larger than C, C is set as the maxSpeed of this ship'
	 * 		   | if (maxSpeed > C)
	 * 				then this.getMaxSpeed() == C
	 * @effect The given position is set as the position of this new ship. 
	 * 		   | setPosition(posX, posY)
	 * @effect The given speed is set as the speed of this new ship. 
	 * 		   | setSpeed(speedX, speedY)
	 * @effect The given orientation is set as the orientation of this new ship.
	 *         | setOrientation(orientation)
	 * @effect The given radius is set as the radius of this new ship. 
	 * 		   | setRadius(radius)
	 * @effect The given mass is set as the mass of this new ship.
	 * 		   | setMass(mass)
	 * @effect The given mass is set as the total mass of this new ship.
	 * 		   | setTotalMass(mass)]
	 * 
	 */
	public Ship(double posX, double posY, double speedX, double speedY, double orientation, double radius, double mass, double maxSpeed) throws IllegalRadiusException,
			IllegalPositionException {
		super(posX, posY, speedX, speedY, radius, maxSpeed);
		setOrientation(orientation);
		setMass(mass);

	}

	/**
	 * Sets the position of this ship to the given positions,
	 * as well as the bullets on the ship
	 * @param 	positionX	
	 * 			The horizontal position of the entity.
	 * @param 	positionY 
	 * 			The vertical position of the entity.
	 * 			
	 * @pre 	The given positions must be a real number and not infinite.
	 * 			| canHaveAsPosition(positionX,positionY)
	 * @pre 	If the ship is within a world, it must lie fully within boundaries with these positions
	 * 			| canHaveAsPosition(positionX,positionY)
	 * @post	The positions of all the bullets on the ship are set to the given positions.
	 * 			| for (bullet in this.getBullets())
	 * 				new.bullet.getPosition() == [posX, posY]
	 * @post 	The new positions of the ship are set to the given positions.
	 * 			| new.getPosition() == [PositionX, positionY]
	 * @throws 	IllegalPositionException
	 * 			|!canHaveAsPosition()
	 */
	@Override
	public void setPosition(double posX, double posY) {
		super.setPosition(posX, posY);
		
		Set<Bullet> bulletSet = this.getBullets();
		if (bulletSet != null && !bulletSet.isEmpty()) {
			for (Bullet bullet : bulletSet) {
				bullet.position = new Vector2(this.getPosition().getX(), this.getPosition().getY());
			}
		}	}
	
	/**
	 *A method to set the speed of this ship and all the bullets
	 *it contains to a given horizontal and vertical speed.
	 * @param	speedY
	 *          The vertical velocity of this ship.
	 * @post	If the total speed of this ship (= the squared root of the sums of the
	 *       	squares of the two velocities) does not exceed the maximum speed,
	 *       	the new velocities are equal to the given velocities 
	 *       	| if (lengthVector(speedX, speedY) <= MaxSpeed 
	 *       	then new.getSpeed().getX() == speedX
	 *       	&& new.getSpeed().getY() == speedY
	 * @post	If the speed of this ship exceeds the maximum speed, the new
	 *       	velocity equals the maximum speed, with both the x and y component
	 *       	decreasing, so that the direction is kept. 
	 *       	| if !canHaveAsSpeed()
	 *       	then new.getSpeed().getX() == speedX*MaxSpeed/lengthVector(speedX, speedY) && 
	 *       	new.getSpeed().getY() == speedY*MaxSpeed/lengthVector(speedX, speedY)
	 * @post	The speed of all the bullets on this ship are the same as the speed of the ship itself.
	 * 			| for each (bullet in bullet)
	 * 				new.this.getSpeed() == new.bullet.getSpeed() 
	 */
	@Override
	public void setSpeed(double speedX, double speedY) {
		super.setSpeed(speedX, speedY);
		Set<Bullet> bulletSet = this.getBullets();
		if (bulletSet != null && !bulletSet.isEmpty()) {
			for (Bullet bullet : bulletSet) {
				bullet.speed = new Vector2(this.getSpeed().getX(), this.getSpeed().getY());
			}
		}
	}
	
	private double Orientation;
	
	/**
	 * A method that returns the orientation of the ship
	 * @return	The orientation of the ship
	 * 			result >=0
	 * 			result < 2*PI
	 */
	@Basic
	public double getOrientation() {
		return this.Orientation;
	}

	/**
	 * A method that sets the orientation of the ship to the given orientation.
	 * 
	 * @param	orientation
	 *			The orientation of the ship 
	 * @pre		the given orientation must be a value between 0 and 2* PI
	 * 			| isValidOrientation(orientation)
	 * @post	the orientation is set to the given orientation
	 * 			| new.getOrientation() = orientation
	 * @effect	The orientation is set to given orientation
	 * 			| this.Orientation = orientation
	 * 
	 */
	public void setOrientation(double orientation) {
		assert canHaveAsOrientation(orientation);
		this.Orientation = orientation;
	}

	/**
	 * Checks whether the given orientation is valid
	 * 
	 * @param	orientation
	 * 			the given orientation
	 * @return 	True if and only if the orientation is between 0 and 2*PI
	 * 			| @seeImplementation
	 * @return	False if the orientation is not a real number or infinite.
	 * 			| @seeImplementation
	 */
	public static boolean canHaveAsOrientation(double orientation) {
		if (Double.isInfinite(orientation) || Double.isNaN(orientation))
			return false;
		return (orientation >= 0) && (orientation < Math.PI * 2);
	}
	
	private static double minRadius = 10;
	
	/**
	 * A method that returns the minimum radius for ships
	 * @return 	The minimum radius for ships
	 * 			| @seeImplementation
	 */
	public static double getMinimumRadius() {
		return minRadius;
	}

	/**
	 * A method that checks whether the radius for the ship is valid
	 * 
	 * @return	False if the radius is not a real number or infinite.
	 * 			| @seeImplementation
	 * @return 	True if and only if the radius is larger than the minimum radius.
	 * 			| @seeImplementation
	 */
	public boolean isValidRadius(double radius){
		if (Double.isInfinite(radius) || Double.isNaN(radius))
			return false;
		return radius >= getMinimumRadius();
	}
	
	private double totalMass;
	/**
	 * The density of the ship
	 */
	public final double density = 1.42 * Math.pow(10, 12);
	
	private double shipMass;
	
	
	
	/**
	 * A method that returns the density of this ship
	 * @return	returns the density of this ship
	 * 			| return this.density
	 */
	@Override
	public double getDensity() {
		return this.density;
	}
	
	/**
	 * A method that returnsn the minimal mass allowed
	 * @return 	Return the density times PI times 4 time the radius to the power of 3, divided by 3.
	 * 			|result == 4.0/3.0 * Math.PI * getDensity() * Math.pow(this.getRadius(), 3);
	 */
	public double getMinMass() {
		return super.getMass();
	}
	
	/**
	 * Returns the mass of the ship
	 * @return @seeImplementation
	 */
	public double getMass() {
		return this.shipMass;
	}
	
	/**
	 * Set the mass of the ship to the given mass. If the given mass is not valid, the mass is set to the minimal mass allowed
	 * @param	mass	
	 * 			the given mass
	 * @post	If the given mass is a valid mass, the mass is set to the given mass..
	 * 		 	| if isValidMass(mass)
	 *        		then this.getMass() == mass
	 * @post 	If the mass is not a valid mass, the mass is set to the minimum mass allowed.
	 * 		 	| if !isValidMass(mass)
	 * 				then new.getMass() == minMass 
	 * 	@post	the total mass of the ship is updated with the ship's new mass.
	 * 			| new.getTotalMass() == old.getTotalMass()- old.getMass() + new.getMass()

	 * 
	 */
	public void setMass(double mass) {
			
		if (isValidMass(mass)) {
			this.replaceShipMassInTotalMass(mass);
			this.shipMass = mass;
		}
			
		else {
			this.replaceShipMassInTotalMass(this.getMinMass());
			this.shipMass = this.getMinMass();
		}
	}
	
	
	/**
	 * Returns the total mass of the ship. This implies the mass of the ship in addition to the mass of the bullets on the ship.
	 * @return The total mass of the ship.
	 */
	@Override
	public double getTotalMass() {
		return this.totalMass;
	}
	

	/**
	 * A method that sets the total mass of the ship with a given mass
	 * @param	mass The given mass for the ship.
	 * @post	If the mass is a valid mass, the mass is set to the given mass
	 * 		 	| if isValidMass()
	 *        		then new.getTotalMass() = mass
	 * @post	If the mass is not a valid mass, the mass is set to the minimum mass allowed.
	 * 		 	| if !isValidMass()
	 * 				then new.getTotalMass() = minMass
	 *       
	 */
	public void setTotalMass(double mass) {
		if (isValidMass(mass))
			this.totalMass = mass;
		else
			this.totalMass = this.getMinMass();
		}
	
	/**
	 * A method that replaces the mass of the ship with the given mass in the total mass of the ship.
	 * 
	 * @param	mass
	 * 			The new mass of the ship
	 * @post	The total mass of the ship is the original total mass, subtracted by the original ship's mass
	 * 			added by the given mass
	 * 			| new.getTotalMass() == old.getTotalMass()- old.getMass() + mass

	 * 
	 */
	@Raw
	private void replaceShipMassInTotalMass(double mass) {
			this.totalMass-= this.getMass();
			setTotalMass( this.getTotalMass() + mass);
	}


	/**
	 * A method that checks whether the given mass is a valid mass
	 * @param	mass 
	 * 		  	The given mass.
	 * @return	True if mass >= minMass
	 * @return	False if mass < minMass
	 */
	private boolean isValidMass(double mass) {
		if (Double.isNaN(mass) || Double.isInfinite(mass))
			return false;
		return mass >= this.getMinMass();
	}
	
	/**
	 * A method to terminate a ship and all its bullets.
	 * @post	if the entity is a ship, all the bullets on the ship are terminated.
	 * 			| for (bullet in this.getBullets())
	 *  			bullet.isTerminated() == true;
	 * @post	this.isTerminated() == true
	 */
	@Override
	public void terminate() {
		clearAllBullets();
		super.terminate();
		
	}
/**
	   * A method that removes all the bullets from the ship, and terminates them.
	   * @post	The bullets inside the set of bullets are all terminated.
	   * 		|for bullet in bullets
	   * 			bullet.isTerminated() = True
	   * @post	The set of bullets is empty.
	   * 		|this.getBullets()isEmpty()
	   * 
	   */
	  public void clearAllBullets() {
		  if (!this.getBullets().isEmpty() ) {
			  Iterator<Bullet> iterator = this.getBullets().iterator();
			  
			    while (iterator.hasNext()) {
			        Bullet bullet = iterator.next();
			        bullet.removeFromShip();
			        bullet.terminate();
			    }
			    this.bullets.clear(); 
		  }
	  }
		
	/**
	 * Evolves this ship with a given amount of time.
	 * 
	 * @effect	if the thruster is on, this ship is thrust with the ship's acceleration.
	 * 			| if (this.isThrusterOn())
					this.thrust(this.getAcceleration())
	 * @effect	The ship is moved with the given amount of time
	 * 			| this.move(time)
	 * @effect	The ship executes its program (if the program exists) for the given amount of time
	 * 			
	 * 
	 */
	@Override  
	public void evolve(double time) {
		super.evolve(time);
		if (this.getProgram()!= null)
			this.getProgram().executeProgram(time);
		if (this.isThrusterOn())
			this.thrust(this.getAcceleration());
	}

	/**
	 * Turn the ship with a given angle
	 * 
	 * @param	angle
	 *          The angle this ship is turned with
	 * @pre		The new orientation must be a valid orientation.
	 * 			| canHaveAsOrientation(this.getOrientation() + angle)
	 * @post	the new orientation of the ship equals the angle added to the original orientation. 
	 * 			|new.getOrientation = old.Orientation() + angle
	 * @effect	The ship is turned with the given angle.
	 * 			| setOrientation(getOrientation() + angle)
	 */
	public void turn(double angle) {
		assert canHaveAsOrientation(this.getOrientation() + angle);
		setOrientation(getOrientation() + angle);
	}

	/**
	 * Change the speed of the ship depending on the orientation and a factor alfa.
	 * 
	 * 
	 * @param	alfa
	 *          The factor of influence of the orientation for the new
	 *          velocities
	 * @post	If the total speed of the new velocity is within boundaries, and
	 *       	alfa is greater or equal to 0. the new horizontal and vertical
	 *       	velocities are set to the old velocities added to respectively the
	 *       	cosine and sine of alfa. 
	 *       	| if alfa>=0
	 *       		then new.getSpeed().getX() == old.getSpeed().getX() +alfa*cos(getOrientation()) 
	 *       		&& new.getSpeed().getX() == old.getSpeed().getY() + alfa*cos(getOrientation())
	 * @post 	If alfa is smaller than 0, alfa is replaced by zero and the speed
	 *       	is kept.
	 *       	| if alfa < 0 
	 *       		then alfa == 0
	 *       
	 * @post 	If the total speed of the new velocity exceeds the maximum speed,
	 *       	the horizontal and vertical velocities are scaled to the maximum
	 *       	so that the direction of the velocity is kept 
	 *       	| if (lengthVector(this.getSpeed().getX()+alfa*cos(getOrientation()), this.getSpeed().getY()(sin(getOrientation())*alfa) > getMaxSpeed()
	 *       	then new.getSpeed().getX() = (alfa*cos(getOrientation())+this.getSpeed().getX())*MaxSpeed/
	 *       	lengthVector(alfa*cos(getOrientation())+this.getSpeed().getX(), *sin(getOrientation())+this.getSpeed().getY())
	 *       	and new.getSpeed().getY() =(alfa*sin(getOrientation())+this.getSpeed().getY())*MaxSpeed/lengthVector(this.getSpeed().getX()+alfa*cos(getOrientation()),
	 *       	this.getSpeed().getY()+alfa*sin(getOrientation()))
	 *       
	 * @effect 	The new speed is set to the original speed in addition to alfa multiplied with the cos or sin
	 * 			of the orientation of the ship for the horizontal and vertical speeds respectively.
	 * 			|setSpeed(getSpeedX()+alfa*Math.cos(getOrientation()),getspeedY()+alfa*Math.sin(getOrientation))
	 * 
	 */
	public void thrust(double alfa) {
		
		if (alfa < 0) {
			alfa = 0;
		}
		setSpeed(this.getSpeed().getX() + alfa * Math.cos(getOrientation()), this.getSpeed().getY() + alfa * Math.sin(getOrientation()));
	}

	
	private Set<Bullet> bullets = new HashSet<Bullet>();
	
	/**
	 * A method that returns the entire set of bullets on the ship
	 * @return	The set of bullets on the ship.
	 * 			| return this.bullets
	 * 			
	 */
	public Set<Bullet> getBullets() {
		Set<Bullet> a = new HashSet<>();
		if (this.bullets != null) {
			a.addAll(this.bullets);
			return a;
		}
		else
			return null;
	  }
	
	/**
	 * A method that returns the number of bullets on the ship
	 * @return 	The amount of bullets on the ship.
	 * 			|bullets.size()
	 */
	public int getNbOfBullets() {
		return bullets.size();
	}

	
	/**
	 * Adds a bullet to the ship and calls upon the method in bullet to add this ship as its owner.
	 * @param	bullet
	 * 			The given bullet to be added to the ship
	 * @pre 	The bullet has to be allowed to have this ship as an owner.
	 * 			| bullet.canHaveAsBullet()
	 * @post 	The bullet is added to the ship
	 * 		 	| this.bullets.contains(bullet)
	 * @throws	NullPointerException 
	 * 			The bullet is null.
	 * @throws 	IllegalArgumentException 
	 * 			This ship can not have the given bullet on its ship.
	 * 			| !canHaveAsBullet(bullet))
	
	 */
	public void addBulletToShip(Bullet bullet)throws NullPointerException, IllegalArgumentException {
		if (canHaveAsBullet(bullet)) {
			bullet.setShipAsOwner(this);
			bullets.add(bullet);
			this.setTotalMass(this.getTotalMass() + bullet.getMass());

			bullet.resetBounceWall();
		}
		else {
			  throw new IllegalArgumentException();
		  }
		  
	  }
	
	
	/**
	 * Adds a given collection of bullets to the ship.
	 * @param	collection 
	 * 			The collection containing all the bullets to be added to the ship.
	 * @pre		each bullet has to be able to have the ship as an owner.
	 * 			| for bullet in collection
	 * 				canHaveAsBullet(bullet))
	 * @post 	for bullet in collection
	 * 			|this.bullets.contains(bullet
	 * @throws	NullPointerException 
	 * 			A bullet is null.
	 * @throws 	IllegalArgumentException 
	 * 			A bullet is not a valid bullet for the ship.
	 * 			| !canHaveAsBullet(bullet))
	 * )
	 */
	public void addCollectionBulletsToShip(Collection<Bullet> collection) throws IllegalArgumentException,
			NullPointerException {
		for(Bullet bullet: collection){
			if (bullet == null)
				throw new NullPointerException();
			if (!canHaveAsBullet(bullet))
				throw new IllegalArgumentException();
		}
		for(Bullet bullet: collection){
			addBulletToShip(bullet);
		}
	}
	  /**
	   * A method to check whether a bullet is allowed to be put on the ship.
	   * @param 	bullet The bullet to add to the ship.
	   * @return 	False if the bullet is inside a world.
	   * 		 	| if bullet.getWorld()!= null
	   * 				then return False
	   * @return	 False if the bullet is already on another ship (or this ship).
	   * 		 	| if bullet.getOwnerShip() != null
	   * 				then return False
	   * @return	False if the bullet is not completely within the ship, if the bullet was not fired
	   * 			by this ship in the first place.
	   * 			| if (!bullet.isWithinShip(this) && !bullet.getFiredBy().equals(this))
	   * 				then return False
	   */
	  public boolean canHaveAsBullet(Bullet bullet) {
	      if (bullet.isTerminated()|| bullet == null) {
	        return false; }
	      if (bullet.getFiredBy()!= null) {
	    	  if (bullet.getFiredBy().equals(this)) 
	    		  return true; } 
	      if (bullet.isWithinShip(this) && bullet.getWorld() == null && bullet.getOwnerShip() == null) {
	    	  return true;
	      }

			return false;
	  }
	  
	  
	  
	  /**
	   * The speed a ship fires a bullet with
	   */
	 protected final static double fireSpeed = 250;
	 
	 /**
	  * The safety factor for firing bullets
	  */
	 public static final double safetyFactor = 1.02;

	 /**
	  * A method that fires a bullet from the ship, if the ship currently within a world and the ship contains bullets.
	  * It removes the bullet from the ship, adds the bullet to the world of the ship and sets its speed to the firespeed.
	  * @post	The bullet is no longer on the ship
	  * 		|!this.bullets.contains(firedBullet)
	  * @post	The new position of the bullet is set in front of the ship with an extra safetyfactor
	  * 		|new.firebBullet.getPosition() == @seeImplemetation
	  * @post	The fired bullet is added to the world.
	  * 		|firedBullet = this.getWorld().getEntityAt(firedBullet.getPosition().getX(), firedBullet.getPostionY())
	  * @post	The speed of the bullet is set to fireSpeed
	  * 		|firedBullet.newSpeed == @seeImplementation
	  * @post	The ship is set to the ship that fired the bullet.
	  * 		|firedBullet.getFiredBy() == this
	  */
	 public void fireBullet() {
		 if (!bullets.isEmpty() && this.getWorld() != null) {
			 Bullet firedBullet = bullets.iterator().next();
			 double newPosX = this.getPosition().getX() + safetyFactor * (this.getRadius() + firedBullet.getRadius()) * Math.cos(this.getOrientation());
			 double newPosY = this.getPosition().getY() + safetyFactor * (this.getRadius() + firedBullet.getRadius()) * Math.sin(this.getOrientation());
			 this.removeBullet(firedBullet);
			 firedBullet.setPosition(newPosX, newPosY);
			 try {
			 firedBullet.setFiredBy(this);
			 Set<Entity> entitySet = this.getWorld().getEntitySet();
			 for (Entity entityInWorld : entitySet) {
				 if (entityInWorld.significantPotentialOverlap(firedBullet)) {
					 entityInWorld.terminate();
					 firedBullet.terminate();
					 return;
				 }
			 }
			 this.getWorld().addEntity(firedBullet);
			 firedBullet.setSpeed(fireSpeed * Math.cos(this.getOrientation()), fireSpeed * Math.sin(this.getOrientation()));
			 }
			 catch (IllegalStateException exc) {
				 //this.addBulletToShip(firedBullet);
				 firedBullet.terminate();
				 return;
			 }
		 }
	 }
	  
	  
	  
	  
	  /**
	   * A method that removes the given bullet from the ship and calls upon the method in bullet to remove the ship as its owner,
	   * it also subtracts the mass of the bullet from the total mass of the ship.
	   * @param bullet
	   * 		The bullet to remove from the ship.
	   * 
	   * @pre	The bullet has to be on the ship
	   * 		|bullets.contains(bullet)
	   * @post	The bullet is removed from the ship.
	   * 		|!bullet.contains(bullet)
	   * @post	The total mass of the ship is the original total mass minus the mass of the bullet.
	   * 		|newTotalMass = @seeImplementation
	   * @IllegalArgumentException	
	   * 		The bullet is not part of the ship.
	   * 
	   */
	  public void removeBullet(Bullet bullet)throws NullPointerException, IllegalArgumentException{
		  if (bullets.contains(bullet)) {
			  bullets.remove(bullet);
		  	  bullet.removeFromShip();
		  	  this.setTotalMass(this.getTotalMass() - bullet.getMass());
		  }
		  else
			  throw new IllegalArgumentException();
		  
	  }
	  private boolean thrustOn = false;
	/**
	 * the thrustforce of ships
	 */
	public static final double thrustForce = 1.1 * Math.pow(10, 18);
	private double acceleration;

	/**
	 * turns the thruster on and sets the acceleration to the thrustForce divided by the total mass of the ship.
	 * @post The thruster is turned on.
	 * 		 |this.isThrustOn() = True
	 * @post The acceleration of the ship is the thrustForce divided by the total mas of the ship.
	 * 		 |@seeimplementation
	 * 
	 */
	public void thrustOn(){
		this.thrustOn = true;
		this.acceleration =  thrustForce/this.getTotalMass();
	}
	
	/**
	 * Turns the thruster off.
	 * @post The thruster is off.
	 * 		 |!this.isThrustOn() = False
	 */
	public void thrustOff(){
		this.thrustOn = false;
		this.acceleration = 0;
	}
	
	/**
	 * A method that returns true if and only if the thruster is active.
	 * @return	True if the thruster is turned on.
	 * 		  	| result = thrustOn
	 * @return	False if the thruster is turned off.
	 * 		    | result = thrustOn
	 */
	public boolean isThrusterOn(){
		return thrustOn;
	}

	
	/**
	 * A method that returns the acceleration of the ship.
	 * @return	The acceleration of the ship.
	 * 			|this.acceleration
	 * 			
	 * 
	 */
	public double getAcceleration(){
		return  this.acceleration;
	}

	/**
	 * Resolves the collision with this ship and another entity.
	 * @param	entity
	 * 			the entity with which this ship is in collision with.
	 * @post	If the entity is not within the same world as this ship, there is no collision to be resolved.
	 * 			| if(@seeImplementation)
	 * 				return
	 * @effect	the entity resolves this collision with this ship
	 * 			| @seeImplementation
	 * 
	 */
	@Override
	protected void resolve(Entity entity) {
		if (! entity.isPartOfWorld(this.getWorld()))
			return;
		entity.resolveShip(this);
		
	}
	
	/**
	 * Resolves the collision between this ship and a bullet.
	 * @param	bullet
	 * 			The bullet with which this ship is colliding
	 * @post	If the bullet was fired by this ship, the bullet is put back on the ship and the bounceWall is reset
	 * 			|if (bullet.getFiredBy() == this) {
					this.contains(bullet) && bullet.getBounceWall() == 0
	 * @effect	If the bullet was not fired by this ship, both the bullet and ship are terminated.
	 * 			| if (bullet.getFiredBy() != this)
	 * 				this.resolveTwoExplosion(bullet)
	 * 			
	 */
	@Override
	protected void resolveBullet(Bullet bullet) {
		if (bullet.getFiredBy() == this) {
			this.getWorld().removeEntity(bullet);
			this.addBulletToShip(bullet);
			bullet.resetBounceWall();
		}
		else
			super.resolveBullet(bullet);
	}

	/**
	 * Resolves the collision between this ship and another ship
	 * @param	ship
	 * 			The ship with which the collision occurs
	 * @effect	The two ships bounce of each other
	 * 			|this.resolveBounceEntity(ship)
	 */
	@Override
	protected void resolveShip(Ship ship) {
		this.resolveBounceEntity(ship);
	}

	/**
	 * Resolves the collision between this ship and an asteroid
	 * @param	asteroid
	 * 			The asteroid with which the collision occurs
	 * @post	This ship is terminated
	 * 			|this.isTerminated() == true
	 */
	@Override
	protected void resolveAsteroid(Asteroid asteroid) {
		this.terminate();
	}

	/**
	 * Resolves the collision between this ship and a planetoid
	 * @param	planetoid
	 * 			The planetoid with which the collision occurs
	 * @effect	the position of this ship is set to a random position
	 * 			| @seeImplementation
	 */
	@Override
	protected void resolvePlanetoid(Planetoid planetoid) {
		this.setPositionRandom();
	}
	
	/**
	 * Sets the position of this ship to a random position within the boundaries of the world it is in
	 * @post	If in the new position, this ship overlaps with another entity in the same world, the ship is terminated
	 * 			| if ( for any (entity in entitySet) 
	 * 					this.overlap(entity)
	 * 				then this.isTerminated() == true
	 * @post	the new position of this ship is set to a random position within the boundaries of the world it is in.
	 * 			| new.getPosition() == @seeImplementation
	 */
	protected void setPositionRandom() {
		Random r = new Random();
		double randomX = this.getRadius() + (this.getWorld().getSizeX() - 2 * this.getRadius()) * r.nextDouble();
		double randomY = this.getRadius() + (this.getWorld().getSizeY() - 2 * this.getRadius()) * r.nextDouble();
		this.setPosition(randomX, randomY);
		Set<Entity> entitySet = this.getWorld().getEntitySet();
		entitySet.remove(this);
		for (Entity entity : entitySet) {
			if (this.overlap(entity)) {
				this.terminate();
				return;
			}
		}
	}

	@Override
	public String toString() {
		return "S" + this.position.toString();
	}
	
	private Program program;
	
	/**
	 * Sets the program of the ship to the given program
	 * @param 	program
	 * @post	The program of the ship is set to the given program
	 * 			| this.program == program
	 */			
	public void setProgram(Program program) {
		this.program = program;
		this.getProgram().setProgramShip(this);
		
	}
	
	/**
	 * Returns the program of the ship
	 * @return @seeImplementation
	 */
	public Program getProgram() {
		return this.program;
	}
	
}
