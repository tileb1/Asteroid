package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of collisions involving the colliding entities or boundary and the time left before
 * before the collision.
 * @invar	The time left must be greater than or equal to 0.
 * 			| this.getTime() >= 0
 * @invar	The boundary must be an integer between 0 and 4.
 * 			| this.getBoundary() in [0, 1, 2, 3] || this.getBoundary() == 4
 * @author Tim Lebailly && Elisabeth Tu
 *
 */
@Value
public class Collision {
	
	/**
	 * Initialize a default Collision with both entities null and time positive infinity.
	 * @post	this.entity1 == null
	 * @post	this.entity2 == null
	 * @post	this.time == Double.POSITIVE_INFINITY
	 */
	public Collision () {
		this.entity1 = null;
		this.entity2 = null;
		this.time = Double.POSITIVE_INFINITY;
	}
	/**
	 * Initialize a collision with given entities and time left.
	 * @param	entity1
	 * 			One of the entities involved in the collision.
	 * @param 	entity2
	 * 			One of the entities involved in the collision.
	 * @param 	time
	 * 			The time left before the collision happens.
	 * @pre 	At least one entity must be not null.
	 *  		| entity1 != null || entity2 != null
	 * @pre 	The time must be greater than 0
	 * 			| time >= 0
	 * @post	|new.getTime() == time
	 * @post	|new.getEntity1() == entity1
	 * @post	|new.getEntity2() == entity2
	 */
	public Collision(Entity entity1, Entity entity2, double time)throws IllegalArgumentException {
		if ((entity1== null && entity2 == null) || time <0)
			throw new IllegalArgumentException();
		this.entity1 = entity1;
		this.entity2 = entity2;
		this.time = time;
	}
	
	/**
	 * Initialize a new Collision with given the given entity and boundary
	 * and time left.
	 * @param	entity1
	 * 			The entity involved in the collision.
	 * @param 	time
	 * 			The time left before the collision happens.
	 * @param	boundary
	 * 			The boundary that is involved in the collision."
	 * @pre 	|entity1 != null
	 * @pre 	|0<=boundary<=4
	 * @pre 	|time >=0
	 * @post	|new.getTime() == time
	 * @post	|new.getEntity1() == entity1
	 * @post	|new.getBoundary() == boundary
	 */
	public Collision(Entity entity1, double time, double boundary) throws IllegalArgumentException{
		if (entity1 == null || time < 0 || boundary>4 ||boundary<0)
			throw new IllegalArgumentException();
		this.entity1 = entity1;
		this.time = time;
		this.boundary = boundary;
	}
	
	/**
	 * the first entity involved in the collision.
	 */
	private Entity entity1 = null;
	
	/**
	 * the second entity involved in the collision.
	 */
	private Entity entity2 = null;
	
	/**
	 * The time left before the collision.
	 */
	private double time;
	
	/**
	 * The boundary involved in the collision.
	 */
	private double boundary = 4;
	
	/**
	 * 
	 * @return @seeImplementation
	 */
	public Entity getEntity1() {
		return this.entity1;
	}
	
	/**
	 * 
	 * @return @seeImplementation
	 */
	public Entity getEntity2() {
		return this.entity2;
	}
	
	/**
	 * 
	 * @return @seeImplementation
	 */
	public double getTime() {
		return this.time;
	}
	
	/**
	 * 
	 * @return @seeImplementation
	 */
	public double getBoundary() {
		return this.boundary;
	}
	
	/**
	 * Check whether the collision is equal to the given collision.
	 * @return	True if and only if other is effective and every fields of
	 * 			the objects are the same.
	 */
	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (this.getClass() != other.getClass())
			return false;
		Collision otherCollision = (Collision) other;
		return this.getEntity1() == otherCollision.getEntity1() && this.getEntity2() == otherCollision.getEntity2() 
				&& this.getTime() == otherCollision.getTime() && this.getBoundary() == otherCollision.getBoundary();
	}
	
	/**
	 * Return the hash code for this collision.
	 * @return @seeImplementation
	 */
	@Override
	public int hashCode() {
		return Double.hashCode(this.getTime() + this.boundary) + entity1.hashCode() + entity2.hashCode();
	}
	
	/**
	 * Return a textual representation of this collision.
	 *@return  @seeImplementation
	 */
	@Override
	public String toString() {
		if (this.entity1 == null && this.entity2 == null)
			return "The collision is not effective!";
		if (this.entity2 == null) {
			return "CollisionBoundary(" + this.getEntity1().getPosition().toString() + ", Time: " 
					+ Double.toString(this.time) + ", Boundary: " + Double.toString(this.getBoundary()) + ")";
		}
		else {
			return "CollisionEntity(" + this.getEntity1().getPosition().toString() + ", "
					+ this.getEntity2().getPosition().toString() + ", Time: " + Double.toString(this.time) + ")";
		}
	}
}
