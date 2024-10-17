package com.hexaware.lm.entity;

public class HomeLoan extends Loan {
	
	private String propertyAddress;
    private int propertyValue;
    
	public HomeLoan() {
		// TODO Auto-generated constructor stub
	}

	public HomeLoan(int loanID, int customerID, int pricipalAmount, int interestRate, int loanTerm, String loanType,
			String loanStatus) {
		super(loanID, customerID, pricipalAmount, interestRate, loanTerm, loanType, loanStatus);
		// TODO Auto-generated constructor stub
		this.propertyAddress = propertyAddress;
		this.propertyValue = propertyValue;
	}

	public String getPropertyAddress() {
		return propertyAddress;
	}

	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}

	public int getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(int propertyValue) {
		this.propertyValue = propertyValue;
	}
    
	// Method to print all attributes
    public void printHomeLoanDetails() {
        super.printLoanDetails();  // Print base class details
        System.out.println("Property Address: " + propertyAddress);
        System.out.println("Property Value: ₹" + propertyValue);
    }
	
	
    

}
