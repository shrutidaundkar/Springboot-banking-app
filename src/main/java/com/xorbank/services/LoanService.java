package com.xorbank.services;

import java.util.List;

import com.xorbank.model.Loan;


public interface LoanService {

	boolean createLoanAccount(Loan loanAccount);
	
	List<Loan> findAllLoans(int userId);
}
