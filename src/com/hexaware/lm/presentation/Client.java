package com.hexaware.lm.presentation;

import com.hexaware.lm.entity.Loan;
import com.hexaware.lm.exception.InvalidLoanException;
import com.hexaware.lm.service.ILoanService;
import com.hexaware.lm.service.LoanServiceImp;

import java.util.Scanner;

public class Client {
	
	public static void main(String[] args) {
		ILoanService loanService = new LoanServiceImp();
		
		Scanner scanner = new Scanner(System.in);
		int choice;
		
		do {
			System.out.println("\n--- Loan Management System ---");
            System.out.println("1. Apply for a Loan");
            System.out.println("2. Get All Loans");
            System.out.println("3. Get Loan by ID");
            System.out.println("4. Loan Repayment");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    applyForLoan(loanService, scanner);
                    break;
                case 2:
                    loanService.getAllLoans();
                    break;
                case 3:
                    getLoanById(loanService, scanner);
                    break;
                case 4:
                    loanRepayment(loanService, scanner);
                    break;
                case 5:
                    System.out.println("Exiting the Loan Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);
        
        scanner.close();
    }
		
	
	private static void applyForLoan(ILoanService loanService, Scanner scanner) {
        Loan loan = new Loan();
        
        System.out.print("Enter Loan ID: ");
        loan.setLoanID(scanner.nextInt());
        System.out.print("Enter Customer ID: ");
        loan.setCustomerID(scanner.nextInt());
        System.out.print("Enter Principal Amount: ");
        loan.setPricipalAmount(scanner.nextDouble());
        System.out.print("Enter Interest Rate: ");
        loan.setInterestRate(scanner.nextDouble());
        System.out.print("Enter Loan Term (in months): ");
        loan.setLoanTerm(scanner.nextInt());
        System.out.print("Enter Loan Type (e.g., HomeLoan, CarLoan): ");
        loan.setLoanType(scanner.next());

        try {
            boolean success = loanService.applyLoan(loan);
            if (success) {
                System.out.println("Loan application submitted successfully.");
            } else {
                System.out.println("Loan application was cancelled.");
            }
        } catch (Exception e) {
            System.out.println("Error while applying for loan: " + e.getMessage());
        }
    }
	
	private static void getLoanById(ILoanService loanService, Scanner scanner) {
        System.out.print("Enter Loan ID: ");
        int loanId = scanner.nextInt();
        try {
            Loan loan = loanService.getLoanById(loanId);
            if (loan != null) {
                System.out.println("Loan Details: ");
                loan.printLoanDetails(); 
            }
        } catch (InvalidLoanException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
	
	private static void loanRepayment(ILoanService loanService, Scanner scanner) {
        System.out.print("Enter Loan ID: ");
        int loanId = scanner.nextInt();
        System.out.print("Enter repayment amount: ");
        double amount = scanner.nextDouble();
        
        try {
            loanService.loanRepayment(loanId, amount);
            System.out.println("Repayment processed successfully.");
        } catch (InvalidLoanException e) {
            System.out.println("Error during repayment: " + e.getMessage());
        }
		
		
	}
	
}
