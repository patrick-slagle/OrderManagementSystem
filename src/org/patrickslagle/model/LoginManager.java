
/**
 * 
 * <h1>Class used to validate user credentials upon login</h1>
 * 
 */

package org.patrickslagle.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginManager {

	/**
	 * validate user login credentials
	 * 
	 * @param loginUsername
	 * @param loginPassword
	 * @return boolean
	 */
	public static boolean validate(String username, String password) {
		DatabaseManager dbm = new DatabaseManager();
		boolean valid = false;
		ResultSet rs = null;
		Statement stmt = null;
		dbm.setUrl("jdbc:mysql://localhost:3306/pattycakes");
		dbm.connect();
		try {
			stmt = dbm.getConn().createStatement();
			String sql = "SELECT * FROM users WHERE email = '[" + username + "]' AND password = '" + password + "'";
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
