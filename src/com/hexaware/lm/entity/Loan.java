package com.hexaware.lm.entity;

public class Loan {
	private int loanID;
	private int customerID;
	private double pricipalAmount;
	private double interestRate;
	private int loanTerm;
	private String loanType;
	private String loanStatus;
	
	public Loan() {
		// TODO Auto-generated constructor stub
	}

	public Loan(int loanID, int customerID, int pricipalAmount, int interestRate, int loanTerm, String loanType,
			String loanStatus) {
		this.loanID = loanID;
		this.customerID = customerID;
		this.pricipalAmount = pricipalAmount;
		this.interestRate = interestRate;
		this.loanTerm = loanTerm;
		this.loanType = loanType;
		this.loanStatus = loanStatus;
	}

	public int getLoanID() {
		return loanID;
	}

	public void setLoanID(int loanID) {
		this.loanID = loanID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public double getPricipalAmount() {
		return pricipalAmount;
	}

	public void setPricipalAmount(double d) {
		this.pricipalAmount = d;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double d) {
		this.interestRate = d;
	}

	public int getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(int loanTerm) {
		this.loanTerm = loanTerm;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}
	
	// Method to print all attributes
    public void printLoanDetails() {
        System.out.println("Loan Details:");
        System.out.println("Loan ID: " + loanID);
        System.out.println("Customer ID: " + customerID);
        System.out.println("Principal Amount: " + pricipalAmount);
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("Loan Term: " + loanTerm + " months");
        System.out.println("Loan Type: " + loanType);
        System.out.println("Loan Status: " + loanStatus);
	
    }
	
}
