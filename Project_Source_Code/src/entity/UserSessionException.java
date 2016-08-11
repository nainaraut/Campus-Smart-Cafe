/**
 * @author naina
 * description: This class is a model to throw the UserSessionException
 */
package entity;

public class UserSessionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CampusCardUser currUser;
	public UserSessionException () {super ("UserSessionException");}
	public UserSessionException (CampusCardUser user) {
		currUser = user; 
	}
    public String toString(){
       return("User " + currUser.getFirstName() + " already logged in");
    }
}
