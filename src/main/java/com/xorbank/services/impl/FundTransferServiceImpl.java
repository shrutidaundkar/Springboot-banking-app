package com.xorbank.services.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xorbank.exceptions.UserNotFoundException;
import com.xorbank.model.Account;
import com.xorbank.model.Transaction;
import com.xorbank.model.User;
import com.xorbank.repository.AccountRepository;
import com.xorbank.repository.TransactionRepository;
import com.xorbank.repository.UserRepository;
import com.xorbank.response.MessageResponse;
import com.xorbank.services.FundTransferService;

@Service
public class FundTransferServiceImpl implements FundTransferService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private UserRepository userRepository;


	public FundTransferServiceImpl() {
	}

	@Override
	public boolean checkAccountValidity(int accountId) {
		return accountRepository.existsById(accountId);
	}

	
	@Override
	public double checkAccountBalance(int accountId) {
		return accountRepository.getById(accountId).getBalance();
	}
	
	@Override
	public boolean getAccountStatus(int accountId) {
		
		return accountRepository.getById(accountId).getAccountStatus();
	}

	@Override
	public MessageResponse sendAmount(int fromAccount, int toAccount, double amount,String description,int userId,int otp) throws Exception {
		Transaction transaction = new Transaction();
		transaction.setFromAccount(fromAccount);
		transaction.setToAccount(toAccount);
		transaction.setDescription(description);
		transaction.setAmount(amount);
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormatter.format(new Date());
		transaction.setTransactionDate(currentDate);
		
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
		LocalDateTime myTimeObj = LocalDateTime.now();
		String formattedTime = myTimeObj.format(myFormatObj);
		transaction.setTransactionTime(formattedTime);
		if (fromAccount != toAccount) {
			
			if (checkAccountValidity(toAccount) && checkAccountValidity(fromAccount)) {
				
				if (checkAccountBalance(fromAccount) > amount) {
					
					if(checkOTP(userId,otp)) {
						Account from = accountRepository.getById(fromAccount);
						Account to = accountRepository.getById(toAccount);
						from.setBalance(from.getBalance() - amount);
						to.setBalance(to.getBalance()+amount);
						accountRepository.save(from);
						accountRepository.save(to);
						transaction.setTransactionStatus("SUCCESS");
						transactionRepository.save(transaction);	
						getAllTransactionsFromAccount(fromAccount);
						return new MessageResponse("Transaction Successful",201);
						
					}else {
						transaction.setTransactionStatus("FAILED");
						transactionRepository.save(transaction);
						return new MessageResponse("Invalid OTP",400);
					}
					
				} else {
					transaction.setTransactionStatus("FAILED");
					transactionRepository.save(transaction);
					return new MessageResponse("Insufficient Balance",400);
				}

			} else {
				transaction.setTransactionStatus("FAILED");
				transactionRepository.save(transaction);
				return new MessageResponse("Account Deactivated or Account does not exist",400);
			}

		} else {
			transaction.setTransactionStatus("FAILED");
			transactionRepository.save(transaction);
			return new MessageResponse("Sender and receiver account cannot be same!",400);
		}
	}

	@Override
	public List<Transaction> getAllTransactionsFromAccount(int accountId) {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		transactionList.addAll(transactionRepository.findTransactionByFromAccount(accountId));
		transactionList.addAll(transactionRepository.findTransactionByToAccount(accountId));
		
		Collections.sort(transactionList);
		return transactionList;
	}

	@Override
	public User getUser(int userId) {
		return userRepository.findByUserId(userId);
	}
	
	@Override
	public void updateOTP(int otp, String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setOtp(otp);
            userRepository.save(user);
        } else {
            throw new UserNotFoundException("Could not find any user with the email " + email);
        }
    }

	@Override
	public boolean checkOTP(Integer userId, int otp) {
		User user = userRepository.findByUserId(userId);
		if(otp != 0 && otp==user.getOtp()) {
			user.setOtp(0);
			userRepository.save(user);
			return true;
		}
		return false;
	}
}
