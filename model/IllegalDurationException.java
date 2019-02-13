package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;

/**
* A class for signaling illegal duration of movements for the class ship.
* 
* @author	 Lebailly Tim
* @author	 Tu Elisabeth
*/
public class IllegalDurationException extends RuntimeException {
	
	/**
	 * Initialize the new illegal duration exception with given value.
	 * 
	 * @param	value
	 * 			The value for this new illegal duration exception.
	 * @post	The value of the new illegal duration exception is equal to the given value.
	 * 			| new.getValue == value
	 */
	public IllegalDurationException(double value) {
		this.value = value;
	}
	
	/**
	 * Return the value registered for this illegal duration exception.
	 */
	@Basic
	public double getValue() {
		return this.value;
	}

	private final double value;
	
	private static final long serialVersionUID = 2003001L; //RANDOM
}

