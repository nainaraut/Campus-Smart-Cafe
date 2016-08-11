/**
 * @author naina
 * description: This class is a database class
 *               to create, update and read date 
 *               from the order_details table
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import entity.OrderFood;

public class OrderDao {
	
	/**
	 * Enter the order details in the order_details table
	 * @param orders
	 * @throws SQLException
	 */
	public void placeOrder(List<OrderFood> orders) throws SQLException{	
		Connection con=null;
		Statement stmt=null;
		
		con=DBConnection.getDBconnection();
		
		String query="INSERT INTO ORDER_DETAILS (order_id,campus_cafe_id,user_id ,food_id ,quantity,total_amount,purchase_date) " +
		"VALUES(ORDER_ID_SEQ.nextval,?,?,?,?,?,TO_DATE(?,'MM-dd-yyyy'))";
		
		try
		{
			PreparedStatement ps = con.prepareStatement(query);
			for(OrderFood order : orders){
				ps.setString(1,order.getCampus_cafe_id());
				ps.setString(2,String.valueOf(order.getUser_id()));
				ps.setString(3,order.getFood_id());
				ps.setInt(4,order.getQuantity());
				ps.setFloat(5,(float) order.getTotal_amount());
				DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
				String date = dateFormat.format(new Date()); // will print like 02-20-2014
				ps.setString(6,date);
				ps.addBatch();
			}
			ps.executeBatch();
		}
		catch(SQLException e)
		{
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
	}
	
	/**
	 * Gives the order details based on the user id
	 * @param UserId
	 * @return List<OrderFood>
	 * @throws SQLException
	 */
	public List<OrderFood> getOrderDetails(int UserId) throws SQLException{
		Connection con=null;
		Statement stmt=null;
		String id = String.valueOf(UserId);
		List<OrderFood> orderedFoodList = new ArrayList<OrderFood>();
		
		con=DBConnection.getDBconnection();
		String query="SELECT * FROM ORDER_details WHERE user_id='"+id+"'";
		
		try
		{
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			
			while(rs.next())
			{
				int user_id = Integer.parseInt(rs.getString("user_id"));
				String campus_cafe_id  = rs.getString("campus_cafe_id");
				String food_id  = rs.getString("food_id");
				int quantity = rs.getInt("quantity");
				double total_amount  = rs.getFloat("total_amount");
				Date purchase_date = rs.getDate("purchase_date");
				orderedFoodList.add(new OrderFood(campus_cafe_id,user_id,food_id,quantity,total_amount,purchase_date));
			}
			System.out.println("row readed");
		}
		catch(SQLException e)
		{
			System.out.println("Error in selection");
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
		
		return orderedFoodList;
	}
	
	/**
	 * Gives the calories consumed by the user during the day
	 * @param user_id
	 * @return String[]
	 * @throws SQLException
	 */
	public String[] getPerDayDietaryConsumption(int user_id) throws SQLException{
		Connection con=null;
		Statement stmt=null;
		String id = String.valueOf(user_id);
		String[] dietaryConsumption = new String[4];
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String date = dateFormat.format(new Date()); // will print like 02-20-2014
		con=DBConnection.getDBconnection();
		String query = "select o.user_id,sum(f.calories) AS calories, SUM(f.carbohydrates) AS carbohydrates," +
				"SUM(f.fat) AS fats, SUM(f.cholesterol) AS cholesterol from order_details o,food f " +
				"where o.food_id=f.food_id and o.user_id = '"+id+"' and TO_CHAR((o.PURCHASE_DATE),'MM-DD-YYYY')>='"+date+"' "+
				"group by o.user_id";
		try
		{
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			
			if(rs.next())
			{
				dietaryConsumption[0] = rs.getString("calories");
				dietaryConsumption[1] = rs.getString("carbohydrates");
				dietaryConsumption[2] = rs.getString("fats");
				dietaryConsumption[3] = rs.getString("cholesterol");
			}
			System.out.println("row readed");
		}
		catch(SQLException e)
		{
			System.out.println("Error in selection");
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
		return dietaryConsumption;
	}
	
	/**
	 * description:Gives the funds spends by the user in the day
	 * @param user_id
	 * @return funds
	 * @throws SQLException
	 */
	public float getPerDayFundsConsumption(int user_id) throws SQLException{
		Connection con=null;
		Statement stmt=null;
		String id = String.valueOf(user_id);
		float totalSpentAmt = 0;
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String date = dateFormat.format(new Date()); // will print like 02-20-2014
		con=DBConnection.getDBconnection();
		String query = "select o.user_id,sum(o.total_amount) AS funds from order_details o,food f " +
				"where o.food_id=f.food_id and o.user_id = '"+id+"' and TO_CHAR((o.PURCHASE_DATE),'MM-DD-YYYY')>='"+date+"' "+
				"group by o.user_id";
		try
		{
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			
			if(rs.next())
			{
				totalSpentAmt = rs.getFloat(2);
			}
			System.out.println("row readed");
		}
		catch(SQLException e)
		{
			System.out.println("Error in selection");
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
		return totalSpentAmt;
	}
	
	public Map<String,Float> fundSpentInMonth(int user_id) throws SQLException{
		Connection con=null;
		Statement stmt=null;
		Map<String,Float> fundsList = new HashMap<String,Float>();
        String id = String.valueOf(user_id);
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String date = dateFormat.format(new Date()); // will print like 02-20-2014
		String monthYear = null;
		final DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        final Calendar c = Calendar.getInstance();
        try {
            c.setTime(df.parse(date));
            int mon = c.get(Calendar.MONTH)+1;
            String month = String.valueOf(c.get(Calendar.MONTH)+1);
            if(mon < 10){
            	month = "0"+month;
            }
            String year = String.valueOf(c.get(Calendar.YEAR));
            monthYear = month+"-"+year;
        } 
        catch (ParseException e) {
            e.printStackTrace();
        }
		con=DBConnection.getDBconnection();	
        String query="SELECT TO_CHAR((O.PURCHASE_DATE),'dd'),sum(f.price * o.quantity) FROM ORDER_DETAILS o, FOOD f " +
        		     "WHERE o.food_id=f.food_id and o.user_id = '"+id+"' and " +
        		     "TO_CHAR((O.PURCHASE_DATE),'MM-YYYY')>='"+monthYear+"' "+
        		     "group by O.PURCHASE_DATE";
        try{
        	stmt = con.createStatement();
        	ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				fundsList.put(rs.getString(1),rs.getFloat(2));
				System.out.println(rs.getString(1)+","+rs.getString(2));				
			}
			System.out.println("row readed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fundsList;
	}
	
	/**
	 * description:Gives the funds consumed by the user during the year
	 * @param user_id
	 * @return Map<String, Float>
	 * @throws SQLException
	 */
	public Map<String, Float> getYearlyFundsConsumption(int user_id) throws SQLException{
		Connection con=null;
		Statement stmt=null;
		Map<String,Float> fundsList = new LinkedHashMap<String,Float>();
        String id = String.valueOf(user_id);
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String date = dateFormat.format(new Date()); // will print like 02-20-2014
		final DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        final Calendar c = Calendar.getInstance();
        String year = null;
        try {
            c.setTime(df.parse(date));           
            year = String.valueOf(c.get(Calendar.YEAR));
        } 
        catch (ParseException e) {
            e.printStackTrace();
        }
		con=DBConnection.getDBconnection();	
        String query="select to_char( o.Purchase_date, 'MON' ) as month,sum(o.total_amount) as amount "+
        			 "from ORDER_DETAILS o, food f where  o.user_id = '"+id+"' "+
        			 "and to_char(o.Purchase_date,'YYYY') "+ 
        			 "LIKE '%"+year+"%' group by o.purchase_date "+
        			 "ORDER BY TO_DATE(TO_CHAR(o.Purchase_date,'MON'),'MON')";
        try{
        	stmt = con.createStatement();
        	ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				fundsList.put(rs.getString(1),rs.getFloat(2));
				System.out.println(rs.getString(1)+","+rs.getString(2));				
			}
			System.out.println("row readed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fundsList;
	}
	
	/**
	 * Give the calories consumed by the user over the year
	 * @param user_id
	 * @return Map<String, Float>
	 * @throws SQLException
	 */
	public Map<String, Float> getYearlyCaloryConsumption(int user_id) throws SQLException{
		Connection con=null;
		Statement stmt=null;
		Map<String,Float> fundsList = new LinkedHashMap<String,Float>();
        String id = String.valueOf(user_id);
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String date = dateFormat.format(new Date()); // will print like 02-20-2014
		final DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        final Calendar c = Calendar.getInstance();
        String year = null;
        try {
            c.setTime(df.parse(date));           
            year = String.valueOf(c.get(Calendar.YEAR));
           
        } 
        catch (ParseException e) {
            e.printStackTrace();
        }
		con=DBConnection.getDBconnection();	
        String query="select to_char( o.Purchase_date, 'MON' ) as month,sum(o.quantity * f.calories) as calories "+
        			 "from ORDER_DETAILS o, food f where  o.user_id = '"+id+"' "+
        			 "and to_char(o.Purchase_date,'YYYY') "+ 
        			 "LIKE '%"+year+"%' group by o.purchase_date "+ 
        			 "ORDER BY TO_DATE(TO_CHAR(o.Purchase_date,'MON'),'MON')";
        try{
        	stmt = con.createStatement();
        	ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				fundsList.put(rs.getString(1),rs.getFloat(2));
				System.out.println(rs.getString(1)+","+rs.getString(2));				
			}
			System.out.println("row readed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fundsList;
	}
}
