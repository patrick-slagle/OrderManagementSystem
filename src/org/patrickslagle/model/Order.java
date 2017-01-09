package org.patrickslagle.model;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>Order objects used to encapsulate data from orders table in the
 * database</h1>
 */
public class Order {

    private String firstName, lastName, phoneNumber,
            email, dueDate, product, comments, imageTitle, imageUrl;

    private int id, day, month, year;
    private Double price;

    public Order(String firstName, String lastName, String phoneNumber,
            String email, String dueDate, String product, String comments, int id, double price) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        try {
            this.dueDate = formatDate(dueDate);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        this.day = setDay(dueDate);
        this.month = setMonth(dueDate);
        this.year = setYear(dueDate);

        this.product = product;
        this.comments = comments;
        this.id = id;
        this.price = price;

        this.imageTitle = null;
        this.imageUrl = null;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getDay() {
        return day;
    }

    private int setDay(String dueDate) {
        String parseHelper = dueDate.substring(dueDate.indexOf("-") + 1);
        day = Integer.parseInt(parseHelper.substring(parseHelper.indexOf("-") + 1));
        return day;
    }

    public int getMonth() {
        return month;
    }

    private int setMonth(String dueDate) {
        String parseHelper = dueDate.substring(dueDate.indexOf("-") + 1);
        month = Integer.parseInt(parseHelper.substring(0, parseHelper.indexOf("-")));
        return month;
    }

    public int getYear() {
        return year;
    }

    private int setYear(String dueDate) {
        year = Integer.parseInt(dueDate.substring(0, dueDate.indexOf("-")));
        return year;
    }

    private String formatDate(String dueDate) throws ParseException {

        char[] chars = dueDate.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '-') {
                StringBuilder sb = new StringBuilder();
                sb.append(dueDate);
                sb.deleteCharAt(i);
                sb.insert(i, "/");
                dueDate = sb.toString();
            }
        }

        SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = inputFormat.parse(dueDate);
        String output = outputFormat.format(date);
        
        return output;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getDueDate() throws ParseException {
        return dueDate;
    }

    public String getProduct() {
        return product;
    }

    public String getComments() {
        return comments;
    }

    public String getPrice() {
        return fmtPrice(price);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private String fmtPrice(Double price) {
        DecimalFormat dFormat = new DecimalFormat("#.00");
        return dFormat.format(price);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
