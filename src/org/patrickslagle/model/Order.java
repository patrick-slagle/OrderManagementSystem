package org.patrickslagle.model;

public class Order {
	
	private String firstName, lastName, phoneNumber, email, dueDate, product, comments;
	
	public Order(String firstName, String lastName, String phoneNumber, 
			String email, String dueDate, String product, String comments) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.dueDate = dueDate;
		this.product = product;
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
	
}
