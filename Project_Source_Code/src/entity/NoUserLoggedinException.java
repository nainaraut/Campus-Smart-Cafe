/**
 * @author naina
 * description: This class is a model to throw the NoUserLoggedinException
 */
package entity;

public class NoUserLoggedinException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoUserLoggedinException () {super ("No User Logged in Yet"); } 
}
