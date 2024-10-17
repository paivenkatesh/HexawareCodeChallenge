package com.hexaware.lm.service;
import com.hexaware.lm.entity.Loan;
import com.hexaware.lm.exception.InvalidLoanException;

public interface ILoanService {
	void applyForLoan(int customerID);
	 boolean applyLoan(Loan loan) throws Exception;
	    double calculateInterest(int loanId) throws InvalidLoanException;
	    double calculateEMI(int loanId) throws InvalidLoanException;
	    void loanRepayment(int loanId, double amount) throws InvalidLoanException;
	    Loan getLoanById(int loanId) throws InvalidLoanException;
	    void loanStatus(int loanId) throws InvalidLoanException;
	    void getAllLoans();

}
