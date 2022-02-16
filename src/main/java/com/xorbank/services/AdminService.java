package com.xorbank.services;


import com.xorbank.model.Account;
import com.xorbank.model.Loan;


public interface AdminService {

	Account createAccount(Account a);
	Loan createLoanAccount(Loan loanAccount);
}
