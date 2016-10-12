package org.patrickslagle.model;

/**
 * <h1>Order objects used to encapsulate data from orders table in the
 * database</h1>
 */
public class Order {

    private String firstName, lastName, phoneNumber, email, dueDate, product, comments;
    private int id;
    private Double price;

    public Order(String firstName, String lastName, String phoneNumber,
            String email, String dueDate, String product, String comments, int id, double price) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dueDate = dueDate;
        this.product = product;
        this.comments = comments;
        this.id = id;
        this.price = price;

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

    public String getDueDate() {
        return dueDate;
    }

    public String getProduct() {
        return product;
    }

    public String getComments() {
        return comments;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
