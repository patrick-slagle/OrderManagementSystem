/**
 * <h1>Database Manager</h1>
 * <h3>The class that oversees all access with the database</h3>
 */

package org.patrickslagle.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseManager {

	private String username;
	private String password;
	private String url;
	private String driver;
	private Connection conn;
	private Statement stmt;

	/**
	 * used by other methods for database access
	 */
	protected void connect() {
		
		username = "pslagle12";
		password = "fOf4Scala";
		driver = "com.mysql.jdbc.Driver";
		
		conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Class not Found Error");
		}
	}

	/**
	 * returns data from the orders table as an ArrayList
	 * 
	 * @return ArrayList
	 */
	private ArrayList<String> getOrderData() {
		Connection conn = null;
		// dummy query
		String sql = "SELECT * FROM orders";
		ArrayList<String> data = new ArrayList<String>();

		setUrl("jdbc:mysql://localhost:3306/pattycakes");
		connect();

		try {
			Statement stmt = getConn().createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			int i = 0;
			while (rs.next()) {
				data.add(rs.getString(i));
				i++;
			}
			System.out.println(data.get(0));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				getConn().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}


	/**
	 *  *******ADD DOC
	 * @param loginUsername
	 * @param loginPassword
	 * @return boolean
	 */
	public boolean validLogin(String loginUsername, String loginPassword) {
		boolean valid = false;
		ResultSet rs = null;
		Statement stmt = null;
		setUrl("jdbc:mysql://localhost:3306/users");
		connect();
		
		try {
			stmt = getConn().createStatement();
			String sql = "SELECT * FROM credentials WHERE user = '" 
							+ loginUsername + "' AND password = '" + loginPassword + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				valid = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				getConn().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return valid;
	}
	
	public void saveOrder(String firstName, String lastName, 
			int phone, String email, 
			Date date, String product, 
			String comments) {
		ResultSet rs = null;
		Statement stmt = null;

		setUrl("jdbc:mysql://localhost:3306/pattycakes");
		connect();

		try {
			stmt = getConn().createStatement();
			
			String sql = "INSERT INTO orders VALUE("
					+ firstName + " " + lastName + " "
					+ phone + " " + email + " "
					+ date + " " + product + " "
					+ comments + ")";
		stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				getConn().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void getOrder() {
		
	}

	public Connection getConn() {
		return conn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
}
