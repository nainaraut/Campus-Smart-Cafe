/**
 * @author naina
 * description: This class is a database class
 *               to create, update and read date 
 *               from the campus_cafes table
 */
package dao;
import entity.CampusCafes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CampusCafeDao {

	/**
	 * description:gives a list of all the cafes and vending machines
	 * @return List<CampusCafes>
	 * @throws SQLException
	 */
	public List<CampusCafes> getCampusCafeList() throws SQLException{
		Connection con=null;
		Statement stmt=null;
		List<CampusCafes> campusCafes = new ArrayList<CampusCafes>();
		
		con=DBConnection.getDBconnection();
		String query="SELECT * FROM CAMPUS_CAFE";
		try
		{
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			
			while(rs.next())
			{
				campusCafes.add(new CampusCafes(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
				System.out.println(rs.getString(1));
			}
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
		return campusCafes;
	}
	
	/**
	 * Gives the details of the campusCafe on the bases of the campus id
	 * @param campusCafeId
	 * @return CampusCafe
	 * @throws SQLException
	 */
	public CampusCafes getCampusCafeDetails(String campusCafeId)throws SQLException{
		Connection con=null;
		Statement stmt=null;
		con=DBConnection.getDBconnection();
		String query="SELECT * FROM CAMPUS_CAFE WHERE campus_cafe_id='"+campusCafeId+"'";
		try
		{
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			
			if(rs.next())
			{
				return new CampusCafes(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
			}
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
		return null;
	}
}
