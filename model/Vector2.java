package asteroids.model;

import be.kuleuven.cs.som.annotate.Value;

/**
 * A class of vector involving 2 components.
 * @author Tim Lebailly && Elisabeth Tu
 *
 */
@Value
public class Vector2 {
	
	/**
	 * Initialize this vector with the given components.
	 * @param	compX
	 * 			The X-component of this vector.
	 * @param	compY
	 * 			The Y-component of this vector.
	 * @post	new.getX() == compX
	 * @post	new.getY() == compY
	 */
	public Vector2(double compX, double compY) {
		this.componentX = compX;
		this.componentY = compY;
	}
	
	/**
	 * The X-component of this vector.
	 */
	private double componentX;
	
	/**
	 * The Y-component of this vector.
	 */
	private double componentY;
	
	/**
	 * Return the X of this Vector2.
	 * @return @seeImplementation
	 */
	public double getX() {
		return this.componentX;
	}
	
	/**
	 * Return the Y of this Vector2.
	 * @return @seeImplementation
	 */
	public double getY() {
		return this.componentY;
	}
	
	/**
	 * Returns the length of this vector.
	 * 
	 * @return @seeImplementation
	 */
	public double getLength() {
		return Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2));
	}
	
	/**
	 * Check whether this vector is equal to the given vector.
	 * @return	True if and only if other is not null and all the fields of
	 * 			the objects are the same.
	 * 			| return (this.getClass() == other.getClass() && other!= null && this.getX() == other.getX() 
	 * 					&& this.getY() == other.getY() )
	 */
	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (this.getClass() != other.getClass())
			return false;
		Vector2 otherVector = (Vector2) other;
		return this.getX() == otherVector.getX() && this.getY() == otherVector.getY();
	}
	
	/**
	 * Return the hash code for this vector.
	 * @return @seeImplementation
	 */
	@Override
	public int hashCode() {
		return Double.hashCode(this.getX() + this.getY());
	}
	
	/**
	 * Return a textual representation of this vector.
	 * @return @seeImplementation
	 */
	@Override
	public String toString() {
		String x = Double.toString(this.getX());
		String y = Double.toString(this.getY());
		int chop = 5;
		if (x.length() >= chop && y.length() >= chop)
			return "[" + x.substring(0, chop) + ", " + y.substring(0, chop) + "]";
		else
			return "[" + this.getX() + ", " + this.getY() + "]";
	}
}
