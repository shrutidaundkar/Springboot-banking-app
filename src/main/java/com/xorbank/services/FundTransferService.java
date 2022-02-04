package com.xorbank.services;

import java.util.List;

import com.xorbank.exceptions.ResponseMessage;
import com.xorbank.models.Transaction;

public interface FundTransferService {

	boolean checkAccountValidity(int accountId);
	double checkAccountBalance(int accountId);
	ResponseMessage sendAmount(int fromAccount, int toAccount, double amount, String description) throws Exception;
	boolean getAccountStatus(int accountId);
	List<Transaction> getAllTransactionsFromAccount(int accountId);
}
