/**
 * 
 */
package UI;

/**
 * This interface allows its implemented classes to defines a method reset() that allows such class
 * to reinitialize its state variables to its initial values. 
 *
 */
public interface Reinitializable  {
	/**
	 * call this method to reinitialize the object. 
	 */
	abstract void reset();
	
}
