package com.axee.employee.managment.app.model;

public class EmployeeResponse {
	private int id;
	private String firstName;
	private String lastName;
	private String emailId;
	
	public EmployeeResponse() {
	}

	public EmployeeResponse(String firstName, String lastName, String emailId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
