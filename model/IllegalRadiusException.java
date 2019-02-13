package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * A class for signaling illegal radius for the class ship.
 * 
 * @author	 Lebailly Tim
 * @author	 Tu Elisabeth
 */
public class IllegalRadiusException extends RuntimeException {
	
	/**
	 * Initialize the new illegal radius exception with given value.
	 * 
	 * @param	value
	 * 			The value for this new illegal radius exception.
	 * @post	The value of the new illegal radius exception is equal to the given value.
	 * 			| new.getValue == value
	 */
	public IllegalRadiusException(double value) {
		this.value = value;
	}
	
	/**
	 * Return the value registered for this illegal radius exception.
	 */
	@Basic
	public double getValue() {
		return this.value;
	}

	private final double value;
	
	private static final long serialVersionUID = 2003001L; //RANDOM
}
