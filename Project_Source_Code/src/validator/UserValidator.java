/**
 * @author naina
 * description: To validate the user entered credentials
 */
package validator;

import java.sql.SQLException;
import dao.ServiceDao;

public class UserValidator {
	
	public boolean validateUserLogin(int loginId,String password){
		boolean valid = false;
		try {
			valid = ServiceDao.getUserDAO().validateUserLogin(loginId,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return valid;
	}
}
