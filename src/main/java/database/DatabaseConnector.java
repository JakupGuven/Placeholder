package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 * Writes and reads user test data to Controller.
 * 
 * @author Jakup
 *
 */
public class DatabaseConnector {
	private static final String URL = "jdbc:sqlite:/home/jakup/eclipse-workspace/Placeholder/db/scheduales.DB";
	
	public static String getURL(int id) {
		try(Connection conn = DriverManager.getConnection(URL)){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT url FROM schedules WHERE id IS '" + id + "'");
			return rs.getString("url");
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void addScheduale(String name, String url) {
		try(Connection conn = DriverManager.getConnection(URL)){
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO schedules (name, url) VALUES ('" + name + "', '" + url + "' )" );
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
    }

}
