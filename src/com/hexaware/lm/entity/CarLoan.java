package com.hexaware.lm.entity;

public class CarLoan extends Loan {
	private String carModel;
	private int carValue;
	public CarLoan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CarLoan(int loanID, int customerID, int pricipalAmount, int interestRate, int loanTerm, String loanType,
			String loanStatus) {
		super(loanID, customerID, pricipalAmount, interestRate, loanTerm, loanType, loanStatus);
		// TODO Auto-generated constructor stub
		this.carModel = carModel;
		this.carValue = carValue;
		
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public int getCarValue() {
		return carValue;
	}
	public void setCarValue(int carValue) {
		this.carValue = carValue;
	}
	
	// Method to print all attributes
    public void printCarLoanDetails() {
        super.printLoanDetails();  // Print base class details
        System.out.println("Car Model: " + carModel);
        System.out.println("Car Value: ₹" + carValue);
    }
	
}
