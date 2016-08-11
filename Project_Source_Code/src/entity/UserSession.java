package entity;

import java.sql.SQLException;

import validator.UserValidator;


public class UserSession {
	static CampusCardUser currUser = null;
	static UserSession userSession = null;
	UserValidator userValidator;
	
	public static UserSession getInstance(){
		if(userSession == null){
			userSession = new UserSession();
		}
		return userSession;
	}
	
	//login the system if the userid and password matches
	 public boolean userLogin(int id, String password) throws NumberFormatException, SQLException, UserSessionException {
		if (currUser != null) {
			throw new UserSessionException(UserSession.currUser); 
		}
		userValidator = new UserValidator(); 
		boolean userExist = userValidator.validateUserLogin(id, password); 
		if (userExist) return true; 
		return false; 
	 }
	 
	 //logout the system with currSession.currUser set to NULL 
	 public void userLogout() throws NoUserLoggedinException{
		 if (currUser == null) {
			 throw new NoUserLoggedinException(); 
		 }
		 UserSession.currUser = null; 
	 }
	 
	 public CampusCardUser getCurrentUser() {
			 return currUser;
	 }
	 
	 public void setCurrentUser(CampusCardUser currUser) {
		 UserSession.currUser = currUser;
	 }
}
