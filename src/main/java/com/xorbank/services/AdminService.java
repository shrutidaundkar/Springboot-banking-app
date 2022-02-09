package com.xorbank.services;


import com.xorbank.model.Account;
import com.xorbank.model.LoanAccount;


public interface AdminService {

	Account createAccount(Account a);
	LoanAccount createLoanAccount(LoanAccount loanAccount);
}
