package com.hexaware.lm.dao;

import com.hexaware.lm.entity.Loan;
import com.hexaware.lm.exception.InvalidLoanException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LoanRepositoryImp implements ILoanRepository {
	
	private static final String URL = "jdbc:mysql://localhost:3306/loan_management"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "Venkatesh#12"; 
	

	@Override
	public boolean applyLoan(Loan loan) throws Exception {
		String sql = "INSERT INTO loan (LoanID, CustomerID, PrincipalAmount, interestRate, loanTerm, loanType, loanStatus) VALUES (?,?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            // Set the parameters for the SQL query
        	preparedStatement.setInt(1,loan.getLoanID());
            preparedStatement.setInt(2, loan.getCustomerID());
            preparedStatement.setDouble(3, loan.getPricipalAmount());
            preparedStatement.setDouble(4, loan.getInterestRate());
            preparedStatement.setInt(5, loan.getLoanTerm());
            preparedStatement.setString(6, loan.getLoanType());
            preparedStatement.setString(7, "Pending"); // Set initial loan status

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Loan application submitted successfully.");
                return true;
            } else {
                System.out.println("Failed to submit loan application.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Database operation failed.");
        }
    }

	@Override
	public double calculateInterest(int loanId) throws InvalidLoanException {
		Loan loan = getLoanById(loanId);
        return (loan.getPricipalAmount() * loan.getInterestRate() * loan.getLoanTerm()) / 100;
    }

	@Override
	public double calculateInterest(int principalAmount, int interestRate, int loanTerm) {
		return (principalAmount * interestRate * loanTerm) / 100;
	}

	@Override
	public void loanStatus(int loanId) throws InvalidLoanException {
		Loan loan = getLoanById(loanId);
        if (loan != null) {
            if (loan.getLoanType().equals("HomeLoan") || loan.getLoanType().equals("CarLoan")) {
                // Check credit score (dummy check)
                int creditScore = 700;  // Dummy credit score
                if (creditScore > 650) {
                    loan.setLoanStatus("Approved");
                    System.out.println("Loan Approved.");
                } else {
                    loan.setLoanStatus("Rejected");
                    System.out.println("Loan Rejected.");
                }
            }
        } 
    }
	
	private void updateLoanStatus(int loanId, String status) throws InvalidLoanException {
        String sql = "UPDATE loan SET loanStatus = ? WHERE LoanID = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, loanId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidLoanException("Failed to update loan status.");
        }
    }


	@Override
	public double calculateEMI(int loanId) throws InvalidLoanException {
		Loan loan = getLoanById(loanId);
        double r = loan.getInterestRate() / 12.0 / 100;
        int n = loan.getLoanTerm();
        double emi = (loan.getPricipalAmount() * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
        return emi;
    }

	@Override
	public double calculateEMI(int principalAmount, int interestRate, int loanTerm) {
		double r = interestRate / 12.0 / 100;
        int n = loanTerm;
        double emi = (principalAmount * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
        return emi;
    }

	@Override
	public void loanRepayment(int loanId, double amount) throws InvalidLoanException {
		Loan loan = getLoanById(loanId);
        double emi = calculateEMI(loanId);
        if (amount < emi) {
            System.out.println("Payment rejected: Amount is less than EMI.");
        } else {
            int noOfEmi = (int) (amount / emi);
            loan.setLoanTerm(loan.getLoanTerm() - noOfEmi);
            System.out.println("EMIs paid: " + noOfEmi);
            System.out.println("Remaining loan term: " + loan.getLoanTerm() + " months.");
        }
    }

	
	private void updateLoanTerm(int loanId, int newTerm) throws InvalidLoanException {
        String sql = "UPDATE loan SET loanTerm = ? WHERE loanID = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setInt(1, newTerm);
            preparedStatement.setInt(2, loanId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidLoanException("Failed to update loan term.");
        }
    }
	@Override
	public void getAllLoans() {
		 String sql = "SELECT * FROM loan";
	        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement preparedStatement = connection.prepareStatement(sql);
	             ResultSet resultSet = preparedStatement.executeQuery()) {
	             
	            while (resultSet.next()) {
	                Loan loan = new Loan();
	                loan.setLoanID(resultSet.getInt("LoanID"));
	                loan.setCustomerID(resultSet.getInt("CustomerID"));
	                loan.setPricipalAmount(resultSet.getDouble("PrincipalAmount"));
	                loan.setInterestRate(resultSet.getDouble("interestRate"));
	                loan.setLoanTerm(resultSet.getInt("loanTerm"));
	                loan.setLoanType(resultSet.getString("loanType"));
	                loan.setLoanStatus(resultSet.getString("loanStatus"));
	                loan.printLoanDetails(); // Ensure this method prints all relevant details
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
    }

	@Override
	public Loan getLoanById(int loanId) throws InvalidLoanException {
String sql = "SELECT * FROM loan WHERE LoanID = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setInt(1, loanId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Loan loan = new Loan();
                loan.setLoanID(resultSet.getInt("LoanID"));
                loan.setCustomerID(resultSet.getInt("CustomerID"));
                loan.setPricipalAmount(resultSet.getDouble("PrincipalAmount"));
                loan.setInterestRate(resultSet.getDouble("interestRate"));
                loan.setLoanTerm(resultSet.getInt("loanTerm"));
                loan.setLoanType(resultSet.getString("loanType"));
                loan.setLoanStatus(resultSet.getString("loanStatus"));
                return loan;
            } else {
                throw new InvalidLoanException("Loan with ID: " + loanId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidLoanException("Database operation failed.");
        }
        
	}

}
