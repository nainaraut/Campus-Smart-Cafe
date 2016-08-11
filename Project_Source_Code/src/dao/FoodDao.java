/**
 * @author naina
 * description: This class is a database class
 *               to create, update and read date 
 *               from the food table
 */
package dao;
import entity.CampusCafes;
import entity.Food;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FoodDao {

	/**
	 * description:Gives a list of food items present in the cafe/Vending machine
	 *             based on the campus cafe id
	 * @param campusCafeId
	 * @return List<Food>
	 * @throws SQLException
	 */
	public List<Food> getFoodItems(String campusCafeId) throws SQLException{
		Connection con=null;
		Statement stmt=null;
		Statement stmt1=null;
		String foodItems = "";
		List<Food> foodList = new ArrayList<Food>();
		
		con=DBConnection.getDBconnection();
		String query = "SELECT * FROM campus_cafe WHERE campus_cafe_id = '"+campusCafeId+"'";
		String[] food_ids = null;
		String foodIdString = "(";
		try
		{
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			
			if(rs.next())
			{
				new CampusCafes(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
				foodItems = rs.getString(5);
				food_ids = foodItems.split("[,]+");
			}
			
			for(int i = 0;i< food_ids.length;i++){
				if(i == food_ids.length-1){
					foodIdString = foodIdString+"'"+food_ids[i]+"')";
				}
				else{
					foodIdString = foodIdString+"'"+food_ids[i]+"',";
				}
			}
			
			
			System.out.println("row read from campus_cafe");
			String query2 = "SELECT * FROM food WHERE food_id in "+foodIdString;
			stmt1=con.createStatement();
			ResultSet rs2 =stmt1.executeQuery(query2);
			
			while(rs2.next())
			{
				String foodId = rs2.getString(1);
				String foodName = rs2.getString(2);
				String type = rs2.getString(3);
				double price = Double.parseDouble(rs2.getString(4));
				int calories = Integer.parseInt(rs2.getString(5));
				int carbohydrates= Integer.parseInt(rs2.getString(6));
				int fats=Integer.parseInt(rs2.getString(7));
				int cholestrol=Integer.parseInt(rs2.getString(8));
				boolean peanut_allergy = Boolean.parseBoolean(rs2.getString(9));
				boolean seafood_allergy =Boolean.parseBoolean(rs2.getString(10));
				boolean lactose_intolerant =Boolean.parseBoolean(rs2.getString(11));
				String prepTime =rs2.getString(12);
			
				foodList.add(new Food(foodId,foodName,type,price,calories,carbohydrates,fats,cholestrol,
						peanut_allergy,seafood_allergy,lactose_intolerant,prepTime));
				
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error in selection from campus_cafe");
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
				stmt.close();
			}
		}
		return foodList;
	}
	
	/**
	 * Gives details of the food item based on the food id
	 * @param foodId
	 * @return Food
	 * @throws SQLException
	 */
	public Food getFoodDetails(String foodId) throws SQLException{
		Connection con=null;
		Statement stmt=null;
		Food food = null;
		
		con=DBConnection.getDBconnection();
		String query="SELECT * FROM FOOD WHERE food_id ='"+foodId+"'";
		
		try
		{
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			
			if(rs.next())
			{
				String food_Id = rs.getString(1);
				String name = rs.getString(2);
				String type= rs.getString(3);
				double price= Double.parseDouble(rs.getString(4));
				int calories= Integer.parseInt(rs.getString(5));
				int carbohydrates= Integer.parseInt(rs.getString(6));
				int fats= Integer.parseInt(rs.getString(7));
				int cholestrol= Integer.parseInt(rs.getString(8));
				boolean peanut_allergy = Boolean.parseBoolean(rs.getString(9));
				boolean seafood_allergy = Boolean.parseBoolean(rs.getString(10));
				boolean lactose_intolerant= Boolean.parseBoolean(rs.getString(11));
				String prepTime= rs.getString(12);
				
				food = new Food(food_Id,name,type,price,calories,carbohydrates,
								fats,cholestrol,peanut_allergy,seafood_allergy,
								lactose_intolerant,prepTime);
			}
			System.out.println("row readed in getFoodDetails");
			
		}
		catch(SQLException e)
		{
			System.out.println("Error in selection in getFoodDetails");
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
				stmt.close();
			}
		}
		return food;
	}
}
