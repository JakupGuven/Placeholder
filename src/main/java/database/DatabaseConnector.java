package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 * 
 * @author Jakup
 *
 */
public class DatabaseConnector {
	private static String URL = "jdbc:sqlite:/home/jakup/eclipse-workspace/Placeholder/db/scheduales.DB";

	public static void setDBLocation(String location) {
		URL = "jdbc:sqlite:" + location;
	}

	public static String getURL(int id) {
		try (Connection conn = DriverManager.getConnection(URL)) {
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
		try (Connection conn = DriverManager.getConnection(URL)) {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO schedules (name, url) VALUES ('" + name + "', '" + url + "' )");
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public static String[] getAllProgramNames() {
		try (Connection conn = DriverManager.getConnection(URL)) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM schedules");
			int count = rs.getInt(1);
			String[] programArray = new String[count];
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT name FROM schedules");
			int i = 0;
			while (rs.next()) {
				programArray[i] = rs.getString("name");
				i++;
			}
			return programArray;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param args
	 *            the command line arguments
	 * @throws SQLException
	 */
	public static void main(String[] args) {
		getProgramList();
	}

	public static ArrayList<String> getProgramList() {
		try (Connection conn = DriverManager.getConnection(URL)) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("Select id, name from schedules");
			ArrayList<String> programList = new ArrayList<String>();
			while (rs.next()) {
				String data = rs.getInt(1) + " " + rs.getString(2);
				programList.add(data);
			}
			return programList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
