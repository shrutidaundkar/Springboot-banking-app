package com.xorbank.services;

import java.util.List;

import com.xorbank.model.Account;
import com.xorbank.model.Loan;


public interface LoanService {

	boolean createLoanAccount(Loan loanAccount,Account account);
	
	List<Loan> findAllLoans(int userId);

	boolean checkAccountNo(int userId, int accountId);
}
