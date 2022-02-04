package com.xorbank.services;

import com.xorbank.models.Transaction;

public interface FundTransferService {

	boolean checkAccountValidity(int accountId);
	double checkAccountBalance(int accountId);
	Transaction sendAmount(int fromAccount, int toAccount, double amount) throws Exception;
	boolean getAccountStatus(int accountId);
}
