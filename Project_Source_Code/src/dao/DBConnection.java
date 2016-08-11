/**
 * @author naina
 * description: This class is a database connection class
 *               to which provides the connection object for 
 *               the Oracle database
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static final String DB_Driver="oracle.jdbc.driver.OracleDriver";
	private static final String DB_Connection="jdbc:oracle:thin:@localhost:1521:XE";
	private static final String DB_user="system";
	private static final String DB_password="manager";

	public static Connection getDBconnection()
	{
		Connection con=null;

		try
		{
			Class.forName(DB_Driver);
		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		try
		{
			con=DriverManager.getConnection(DB_Connection,DB_user,DB_password);
			return con;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			System.out.println(con);
		}
		return con;

	}
}
