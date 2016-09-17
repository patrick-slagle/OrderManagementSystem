package org.patrickslagle.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.sql.ResultSetMetaData;

public class FormManager {

	private DatabaseManager dbm;

	public void saveOrder(String firstName, String lastName, String phone, String email, String date, String product,
			String comments) {
		dbm = new DatabaseManager();
		PreparedStatement pstmt = null;
		dbm.setUrl("jdbc:mysql://localhost:3306/pattycakes");
		dbm.connect();

		String sqlEmail = formatEmail(email);
		java.sql.Date fmtDate = formatDate(date);

		try {
			String sql = "INSERT INTO orders " + "(first_name , last_name , phone , email , "
					+ "due_date, product_type , comments, id) " + "VALUES (?,?,?,?,?,?,?,?)";
			pstmt = dbm.getConn().prepareStatement(sql);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, phone);
			pstmt.setString(4, sqlEmail);
			pstmt.setDate(5, fmtDate);
			pstmt.setString(6, product);
			pstmt.setString(7, comments);
			pstmt.setInt(8, (int) Math.random());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbm.disconnect(pstmt, dbm.getConn());
		}
	}

	public java.sql.Date formatDate(String date) {
		Date fmtDate = null;
		java.sql.Date sqlDate = null;
		try {
			DateFormat df = new SimpleDateFormat("MM/DD/YYYY");
			fmtDate = (Date) df.parse(date);
			sqlDate = new java.sql.Date(fmtDate.getTime());
		} catch (ParseException e) {
			System.out.println("Please use date format 'MM/DD/YYYY'");
		} catch (Exception e) {
			System.out.println("Error: ");
			e.printStackTrace();
		}
		return sqlDate;
	}

	/**
	 * returns data from the orders table as an ArrayList
	 * 
	 * @return ArrayList
	 */
	public HashMap getOrders() {
		dbm = new DatabaseManager();
		Statement stmt = null;
		String sql = "SELECT * FROM orders";
		HashMap<Integer, Order> orderMap = new HashMap<Integer, Order>();

		dbm.setUrl("jdbc:mysql://localhost:3306/pattycakes");
		dbm.connect();
		try {
			stmt = dbm.getConn().createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			rs.beforeFirst();
			int i = 0;
			while (rs.next()) {
				orderMap.put(i + 1,
						new Order(rs.getString("first_name"), rs.getString("last_name"), rs.getString("phone"),
								rs.getString("email"), rs.getString("due_date"), rs.getString("product_type"),
								rs.getString("comments"), rs.getInt("id")));
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbm.disconnect(stmt, dbm.getConn());
		}
		return orderMap;
	}

	public String formatEmail(String email) {

		List list = Arrays.asList(email.split(","));
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == "@") {
				list.add(i, "\\");
			}

			Iterator<String> it = list.iterator();
			while (it.hasNext()) {
				String s = it.next();
				if (s.contains(Character.toString('[')) || s.contains(Character.toString(']'))) {
					System.out.println("removing");
					list.remove(s);
				}
			}
		}
		return list.toString();
	}

	public void createUser(String firstName, String lastName, String email, String password) {
		dbm = new DatabaseManager();
		PreparedStatement pstmt = null;
		dbm.setUrl("jdbc:mysql://localhost:3306/pattycakes");
		dbm.connect();

		try {
			String sqlEmail = formatEmail(email);

			String sql = "INSERT INTO users " + "(first_name , last_name , email , password, id) "
					+ "VALUES (?,?,?,?,?)";
			pstmt = dbm.getConn().prepareStatement(sql);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, sqlEmail);
			pstmt.setString(4, password);
			pstmt.setInt(5, (int) Math.random());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbm.disconnect(pstmt, dbm.getConn());
		}
	}

	public boolean testPassword(String password1, String password2) {
		if (password1.equals(password2)) {
			System.out.println("Here");
			System.out.println(password1 + " " + password2);

			return true;
		} else {
			System.out.println(password1 + " " + password2);

			return false;
		}
	}
}
