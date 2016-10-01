/**
 * 
 * <h1>The FormManager Class contains methods 
 * related to order forms and user registration 
 * forms in the order management system.</h1> 
 * 
 * <h3>It is designed to be used only indirectly by servlets.</h3>
 * 
 */

package org.patrickslagle.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FormManager {

	private DatabaseManager dbm;

	/**
	 * save a user's order to the database
	 * 
	 * @param firstName
	 * @param lastName
	 * @param phone
	 * @param email
	 * @param date
	 * @param product
	 * @param price
	 * @param comments
	 */
	public void saveOrder(String firstName, String lastName, String phone, String email, String date, String product,
			String price, String comments) {
		dbm = new DatabaseManager();
		PreparedStatement pstmt = null;
		dbm.setUrl("jdbc:mysql://localhost:3306/pattycakes");
		dbm.connect();

		String sqlEmail = formatEmail(email);
		java.sql.Date fmtDate = formatDate(date);
		double fmtPrice = formatPrice(price);
		try {
			String sql = "INSERT INTO orders " + "(first_name , last_name , phone , email , "
					+ "due_date, product_type , comments, id, price) " + "VALUES (?,?,?,?,?,?,?,?,?)";
			pstmt = dbm.getConn().prepareStatement(sql);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, phone);
			pstmt.setString(4, sqlEmail);
			pstmt.setDate(5, fmtDate);
			pstmt.setString(6, product);
			pstmt.setString(7, comments);
			pstmt.setInt(8, (int) Math.random());
			pstmt.setDouble(9, fmtPrice);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbm.disconnect(pstmt, dbm.getConn());
		}
	}

	/**
	 * formats the price string as a double so it can be saved to the database
	 * 
	 * @param price
	 * @return
	 */
	private double formatPrice(String price) {
		System.out.println(Double.parseDouble(price));
		return Double.parseDouble(price);
	}

	/**
	 * formats the date string as a SQL date
	 * 
	 * @param date
	 * @return
	 */
	private java.sql.Date formatDate(String date) {
		java.sql.Date formattedDate = null;
		try {
			formattedDate = new java.sql.Date(new SimpleDateFormat("MM/dd/yyyy").parse(date).getTime());
		} catch (ParseException e) {
			System.out.println("Please use date format 'MM/DD/YYYY'");
		} catch (Exception e) {
			System.out.println("Error: ");
			e.printStackTrace();
		}
		return formattedDate;
	}

	/**
	 * formats the email string so that the '@' symbol is compatible with SQL
	 * 
	 * @param email
	 * @return
	 */
	private String formatEmail(String email) {

		List<String> list = Arrays.asList(email.split(","));
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

	/**
	 * returns data from the orders table as an ArrayList
	 * 
	 * @return ArrayList
	 */
	public List getOrders() {
		dbm = new DatabaseManager();
		Statement stmt = null;
		String sql = "SELECT * FROM orders";
		List<Order> orderList = new ArrayList<Order>();

		dbm.setUrl("jdbc:mysql://localhost:3306/pattycakes");
		dbm.connect();
		try {
			stmt = dbm.getConn().createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// When getting orders, we encapsulate the data in order objects
			rs.beforeFirst();
			int i = 0;
			while (rs.next()) {
				orderList.add(new Order(rs.getString("first_name"), rs.getString("last_name"), rs.getString("phone"),
						rs.getString("email"), rs.getString("due_date"), rs.getString("product_type"),
						rs.getString("comments"), rs.getInt("id"), rs.getDouble("price")));
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbm.disconnect(stmt, dbm.getConn());
		}
		return orderList;
	}

	/**
	 * register a new user to the database
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 */
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

	/**
	 * tests the two password fields in registration for a match
	 * 
	 * @param password1
	 * @param password2
	 * @return
	 */
	public boolean testPassword(String password1, String password2) {
		if (password1.equals(password2)) {
			return true;
		} else {
			return false;
		}
	}

	public Order getOrderBySearch(String query) {
		dbm = new DatabaseManager();
		dbm.setUrl("jdbc:mysql://localhost:3306/pattycakes");
		dbm.connect();

		Order order = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";

		final String nameExp = ".*[a-zA-Z]+.*[a-zA-Z]";
		final String priceExp = "[0-9]+([,.][0-9]{1,2})?";
		final String dateExp = "([0-9]{2})/([0-9]{2})/([0-9]{4})";

		try {
			stmt = dbm.getConn().createStatement();
			if (query.matches(nameExp)) {
				System.out.println(nameExp);
				sql = "SELECT * FROM orders WHERE first_name = '" + query + "'";
			} else if (query.matches(priceExp)) {
				System.out.println(priceExp);
				sql = "SELECT * FROM orders WHERE price = '" + query + "'";
			} else if (query.matches(dateExp)) {
				System.out.println(dateExp);
				sql = "SELECT * FROM orders WHERE due_date = '" + query + "'";
			}
			if (!sql.equals("")) {
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					order = new Order(rs.getString("first_name"), rs.getString("last_name"), rs.getString("phone"),
							rs.getString("email"), rs.getString("due_date"), rs.getString("product_type"),
							rs.getString("comments"), rs.getInt("id"), rs.getDouble("price"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

}
