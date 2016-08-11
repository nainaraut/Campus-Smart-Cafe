/**
 * @author naina
 * description: This class acts as a middleware between the database classes
 *               and all the other classes which need to use them
 */
package dao;

public class ServiceDao {

	private static UserDao userDao;
	private static FoodDao foodDao;
	private static OrderDao orderDao;
	private static CampusCafeDao campusCafeDao;
	
	public static UserDao getUserDAO() {
		if (userDao == null) {
			userDao = new UserDao();
		}
		return userDao;
	}
	
	public static FoodDao getFoodDAO() {
		if (foodDao == null) {
			foodDao = new FoodDao();
		}
		return foodDao; 
	}
	
	public static OrderDao getOrderDAO() {
		if (orderDao == null) {
			orderDao = new OrderDao(); 
		}
		return orderDao; 
	}
	
	public static CampusCafeDao getCampusCafeDAO() {
		if (campusCafeDao == null) {
			campusCafeDao = new CampusCafeDao(); 
		}
		return campusCafeDao; 
	}
}
