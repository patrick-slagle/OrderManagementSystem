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

/**
 *
 * <h1>The FormManager Class contains methods related to order forms and user
 * registration forms in the order management system.</h1>
 *
 * <h3>It is designed to be used only indirectly by servlets.</h3>
 *
 */
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

        double randNum = Math.random();

        System.out.println(randNum);
        try {
            String sql = "INSERT INTO orders " + "(id, first_name , last_name , phone , email , "
                    + "due_date, product_type , comments, price) " + "VALUES (?,?,?,?,?,?,?,?,?)";
            pstmt = dbm.getConn().prepareStatement(sql);
            
            pstmt.setInt(1, (int) randNum * 10000);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, phone);
            pstmt.setString(5, sqlEmail);
            pstmt.setDate(6, fmtDate);
            pstmt.setString(7, product);
            pstmt.setString(8, comments);
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
            while (rs.next()) {
                System.out.println(rs.getString("last_name"));

                orderList.add(new Order(rs.getString("first_name"), rs.getString("last_name"), rs.getString("phone"),
                        rs.getString("email"), rs.getString("due_date"), rs.getString("product_type"),
                        rs.getString("comments"), rs.getInt("id"), rs.getDouble("price")));
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

    public ArrayList<Order> getOrdersBySearch(ArrayList<Order> orders, String query) {
        ArrayList<Order> resultOrders = null;
        String type = "";
        final String nameExp = ".*[a-zA-Z]+.*[a-zA-Z]";
        final String priceExp = "[0-9]+([,.][0-9]{1,2})?";
        final String dateExp = "([0-9]{2})/([0-9]{2})/([0-9]{4})";

        if (query.matches(nameExp)) {
            System.out.println(nameExp);
            type = "name";
            resultOrders = getOrderResult(orders, type, query);
        } else if (query.matches(priceExp)) {
            type = "price";
            resultOrders = getOrderResult(orders, type, query);
            System.out.println(priceExp);
        } else if (query.matches(dateExp)) {
            System.out.println(dateExp);
            type = "date";
            resultOrders = getOrderResult(orders, type, query);
        }

        return resultOrders;
    }

    private ArrayList<Order> getOrderResult(ArrayList<Order> orders, String type, String query) {
        ArrayList<Order> result = new ArrayList<Order>();
        switch (type) {
            case "name":
                for (int i = 0; i < orders.size(); i++) {
                    if (orders.get(i).getFirstName().equals(query)) {
                        result.add(orders.get(i));
                    }
                }
            case "price":
                for (int i = 0; i < orders.size(); i++) {
                    if (Double.toString(orders.get(i).getPrice()).equals(query)) {
                        result.add(orders.get(i));
                    }
                }
            case "date":
                for (int i = 0; i < orders.size(); i++) {
                    if (orders.get(i).getDueDate().equals(query)) {
                        result.add(orders.get(i));
                    }
                }
        }
        System.out.println(result);
        return result;
    }

}
