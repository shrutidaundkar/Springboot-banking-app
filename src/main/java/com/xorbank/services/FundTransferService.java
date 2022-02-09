package com.xorbank.services;

import java.util.List;

import com.xorbank.exceptions.UserNotFoundException;
import com.xorbank.model.Transaction;
import com.xorbank.model.User;
import com.xorbank.response.MessageResponse;

public interface FundTransferService {

	boolean checkAccountValidity(int accountId);
	double checkAccountBalance(int accountId);
	MessageResponse sendAmount(int fromAccount, int toAccount, double amount, String description,int userId,int otp) throws Exception;
	boolean getAccountStatus(int accountId);
	List<Transaction> getAllTransactionsFromAccount(int accountId);
	User getUser(int userId);
	void updateOTP(int otp, String email) throws UserNotFoundException;
	boolean checkOTP(Integer userId, int otp);
}
