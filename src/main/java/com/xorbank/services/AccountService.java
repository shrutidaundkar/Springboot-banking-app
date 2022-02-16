package com.xorbank.services;

import java.util.List;

import com.xorbank.model.Account;


public interface AccountService {

	boolean createAccount(Account a);
	Account getAccount(int accountId);
	boolean getAccountStatus( int accountId);
	List<Account> getAllAccounts(int userId);
	Account updateAccount(Account account);
}
