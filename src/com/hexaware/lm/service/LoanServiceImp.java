package com.hexaware.lm.service;

import com.hexaware.lm.dao.ILoanRepository;
import com.hexaware.lm.dao.LoanRepositoryImp;
import com.hexaware.lm.entity.Loan;
import com.hexaware.lm.exception.InvalidLoanException;

public class LoanServiceImp implements ILoanService {
	
	private ILoanRepository loanRepository;
	
	public LoanServiceImp() {
		this.loanRepository = new LoanRepositoryImp();
	}
	
	@Override
	public boolean applyLoan(Loan loan) throws Exception {
		return loanRepository.applyLoan(loan);
	}

	@Override
	public double calculateInterest(int loanId) throws InvalidLoanException {
		return loanRepository.calculateInterest(loanId);
	}

	@Override
	public double calculateEMI(int loanId) throws InvalidLoanException {
		return loanRepository.calculateEMI(loanId);
	}

	@Override
	public void loanRepayment(int loanId, double amount) throws InvalidLoanException {
		loanRepository.loanRepayment(loanId, amount);
	}

	@Override
	public Loan getLoanById(int loanId) throws InvalidLoanException {
		return loanRepository.getLoanById(loanId);
	}

	@Override
	public void loanStatus(int loanId) throws InvalidLoanException {
		loanRepository.loanStatus(loanId);
	}

	@Override
	public void getAllLoans() {
		loanRepository.getAllLoans();

	}

	@Override
	public void applyForLoan(int customerID) {
		// TODO Auto-generated method stub
		
		
	}

}
