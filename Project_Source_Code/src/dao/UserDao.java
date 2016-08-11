/**
 * @author naina
 * description: This class is a database class
 *               to create, update and read date 
 *               from the users table
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.CampusCardUser;
import entity.UserSession;

public class UserDao {
	
	private static final String CALORIC_RESTRICTION = "CALORIC_RESTRICTION";
	private static final String FUNDS_ALLOWANCE = "FUNDS_ALLOWANCE";
	private int reqCalories;
	private double availFunds;
	
	/**
	 * description:validate the user based on the userId and password
	 * @param userId
	 * @param password
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean validateUserLogin(int userId, String password) throws SQLException{
		Connection con=null;
		Statement stmt=null;
		String user_id = String.valueOf(userId);
		
		con=DBConnection.getDBconnection();
		String query="SELECT * FROM USERS WHERE user_id='"+user_id+"'"+" AND PASSWORD ='"+password+"'";
		try
		{
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			
			if(rs.next())
			{
				int caloriesConsumed = 0;
				String[] dietData=ServiceDao.getOrderDAO().getPerDayDietaryConsumption(Integer.parseInt(user_id));
				if(dietData[0] != null){
					caloriesConsumed = Integer.parseInt(dietData[0]);
				}
				double fundsSpent = (double)ServiceDao.getOrderDAO().getPerDayFundsConsumption(Integer.parseInt(user_id));
				CampusCardUser user = 
					new CampusCardUser(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4),Double.parseDouble(rs.getString(5)),
										fundsSpent,Integer.parseInt(rs.getString(7)),caloriesConsumed,
										Boolean.parseBoolean(rs.getString(9)),Boolean.parseBoolean(rs.getString(10)),Boolean.parseBoolean(rs.getString(11)));
				
				UserSession.getInstance().setCurrentUser(user);
				
				this.reqCalories = Integer.parseInt(rs.getString(CALORIC_RESTRICTION));
				this.availFunds = Double.parseDouble(rs.getString(FUNDS_ALLOWANCE));
				return true;
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error in selection");
			System.out.println(e.getMessage());
			return false;
		}
		finally
		{
			if(con!=null)
			{
				con.close();
			}
			if(stmt!=null)
			{
				stmt.close();
			}
		}
		return false;
	}
	
	/**
	 * description:Update the users table
	 * @param user
	 * @throws SQLException
	 */
	public void updateUserProfile(CampusCardUser user)throws SQLException{
		Connection con=null;
		Statement stmt=null;
		
		con=DBConnection.getDBconnection();
		String user_id = String.valueOf(user.getStudentId());
		String availailableFunds = String.valueOf(user.getExpenseProfile().getAvailableFunds());
		String caloric_restriction = String.valueOf(user.getDietaryProfile().getReqCalories());
		String peanut_allergy = String.valueOf(user.getDietaryProfile().isPeanut_allergy());
		String seafood_allergy = String.valueOf(user.getDietaryProfile().isSeafood_allergy());
		String lactose_intolerant = String.valueOf(user.getDietaryProfile().isLactose_intolerant());
		
		String query="UPDATE USERS SET funds_allowance ='"+availailableFunds+"',caloric_restriction ='"+caloric_restriction+"'," +
					 "peanut_allergy ='"+peanut_allergy+"',seafood_allergy ='"+seafood_allergy+"',lactose_intolerant ='"+lactose_intolerant+"'" +
					 "WHERE user_id='"+user_id+"'";
		
		try
		{
			stmt=con.createStatement();
			int updatedRow=stmt.executeUpdate(query);
			System.out.println(updatedRow +"rows updated in updateUserProfile");
		}
		catch(SQLException e)
		{
			System.out.println("error in updating dbms in updateUserProfile");
			System.out.println(e.getMessage());
		}
		finally
		{
			if(con!=null)
			{
				con.close();
			}
			if(stmt!=null)
			{
				((java.sql.Statement) stmt).close();
			}
		}
	}
	
	public int getRequiredCalories(){
		return this.reqCalories;
	}
	
	public double getAvailableFunds(){
		return this.availFunds;
	}
	
	/**
	 * description: Update the available funds and required calories for the user
	 * @param userId
	 * @param spentFunds
	 * @param caloric_count
	 * @return
	 * @throws SQLException
	 */
	public boolean updateFundsAndCalories(int userId,double spentFunds,int caloric_count) throws SQLException{
		Connection con=null;
		Statement stmt=null;
		
		con=DBConnection.getDBconnection();
		String id = String.valueOf(userId);
		String query1="SELECT * FROM USERS WHERE user_id='"+userId+"'";
		
		try
		{
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query1);
			
			if(rs.next())
			{
				String totalSpentFunds = String.valueOf(Double.parseDouble(rs.getString("funds_spent"))+spentFunds);
				String totalCaloriesConsumed = String.valueOf(Integer.parseInt(rs.getString("caloric_count"))+caloric_count);
				String query="UPDATE USERS SET funds_spent ='"+totalSpentFunds+"',caloric_count ='"+totalCaloriesConsumed+"' WHERE user_id ='"+id+"'";
				int updatedRow=stmt.executeUpdate(query);
				System.out.println(updatedRow +"rows updated in setRequiredCalories");
				return true;
			}
		}
		catch(SQLException e)
		{
			System.out.println("error in updating dbms in setRequiredCalories");
			System.out.println(e.getMessage());
			return false;
		}
		finally
		{
			if(con!=null)
			{
				con.close();
			}
			if(stmt!=null)
			{
				((java.sql.Statement) stmt).close();
			}
		}
		return false;
	}
	
	public boolean setRequiredCalories(int userId,int reqCalories)throws SQLException{
		Connection con=null;
		Statement stmt=null;
		
		con=DBConnection.getDBconnection();
		String id = String.valueOf(userId);
		String query="UPDATE USERS SET caloric_restriction ='"+reqCalories+"' WHERE user_id ='"+id+"'";
		
		try
		{
			stmt=con.createStatement();
			int updatedRow=stmt.executeUpdate(query);
			System.out.println(updatedRow +"rows updated in setRequiredCalories");
			return true;
		}
		catch(SQLException e)
		{
			System.out.println("error in updating dbms in setRequiredCalories");
			System.out.println(e.getMessage());
			return false;
		}
		finally
		{
			if(con!=null)
			{
				con.close();
			}
			if(stmt!=null)
			{
				((java.sql.Statement) stmt).close();
			}
		}
	}
	
    public boolean setAvailableFunds(int userId, double availFunds)throws SQLException{
    	Connection con=null;
		Statement stmt=null;
		
		con=DBConnection.getDBconnection();
		String id = String.valueOf(userId);
		String query="UPDATE USERS SET funds_allowance ='"+availFunds+"' WHERE user_id ='"+id+"'";
		
		try
		{
			stmt=con.createStatement();
			int updatedRow=stmt.executeUpdate(query);
			System.out.println(updatedRow +"rows updated in setAvailableFunds");
			return true;
		}
		catch(SQLException e)
		{
			System.out.println("error in updating dbms in setAvailableFunds");
			System.out.println(e.getMessage());
			return false;
		}
		finally
		{
			if(con!=null)
			{
				con.close();
			}
			if(stmt!=null)
			{
				((java.sql.Statement) stmt).close();
			}
		}
	}
    
    
}
