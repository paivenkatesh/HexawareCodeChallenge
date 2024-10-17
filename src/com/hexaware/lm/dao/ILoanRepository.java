package com.hexaware.lm.dao;

import com.hexaware.lm.entity.Loan;
import com.hexaware.lm.exception.InvalidLoanException;

public interface ILoanRepository {
	
	boolean applyLoan(Loan loan) throws Exception;
	double calculateInterest(int loanId) throws InvalidLoanException;
	double calculateInterest(int principalAmount, int interestRate, int loanTerm);
	void loanStatus(int loanId) throws InvalidLoanException;
	double calculateEMI(int loanId) throws InvalidLoanException;
	double calculateEMI(int principalAmount, int interestRate, int loanTerm);
	void loanRepayment(int loanId, double amount) throws InvalidLoanException;
	void getAllLoans();
    Loan getLoanById(int loanId) throws InvalidLoanException;
	
}
