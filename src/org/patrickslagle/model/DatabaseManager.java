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

	protected void disconnect(Statement stmt, Connection conn) {
		try {
			stmt.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
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
