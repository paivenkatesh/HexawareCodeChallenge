package com.hexaware.lm.entity;

public class Customer {
	
	private int CustomerID;
	private String Name;
	private String EmailAddress;
	private String PhoneNumber;
	private String Address;
	private int creditScore;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(int customerID, String name, String emailAddress, String phoneNumber, String address,
			int creditScore) {
		this.CustomerID = customerID;
		this.Name = name;
		this.EmailAddress = emailAddress;
		this.PhoneNumber = phoneNumber;
		this.Address = address;
		this.creditScore = creditScore;
	}

	public int getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmailAddress() {
		return EmailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		EmailAddress = emailAddress;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public int getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}
	

}
