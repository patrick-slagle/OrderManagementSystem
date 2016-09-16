package org.patrickslagle.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginManager {

	/**
	 * *******ADD DOC
	 * 
	 * @param loginUsername
	 * @param loginPassword
	 * @return boolean
	 */
	public boolean validate(String username, String password) {
		DatabaseManager dbm = new DatabaseManager();
		boolean valid = false;
		ResultSet rs = null;
		Statement stmt = null;
		dbm.setUrl("jdbc:mysql://localhost:3306/users");
		dbm.connect();

		try {
			stmt = dbm.getConn().createStatement();
			String sql = "SELECT * FROM credentials WHERE user = '" + username + "' AND password = '" + password + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				valid = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbm.disconnect(stmt, dbm.getConn());
		}
		return valid;
	}

}
