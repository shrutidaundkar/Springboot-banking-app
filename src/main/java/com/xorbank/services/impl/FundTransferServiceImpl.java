package com.xorbank.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xorbank.exceptions.ResponseMessage;
import com.xorbank.models.Account;
import com.xorbank.models.Transaction;
import com.xorbank.repository.AccountRepository;
import com.xorbank.repository.TransactionRepository;
import com.xorbank.services.FundTransferService;

@Service
public class FundTransferServiceImpl implements FundTransferService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private AccountRepository accountRepository;

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
	public ResponseMessage sendAmount(int fromAccount, int toAccount, double amount,String description) throws Exception {
		Transaction transaction = new Transaction();
		transaction.setFromAccount(fromAccount);
		transaction.setToAccount(toAccount);
		transaction.setDescription(description);
		transaction.setAmount(amount);
		transaction.setTransactionDate(LocalDateTime.now().toString());
		if (fromAccount != toAccount) {
			
			if (checkAccountValidity(toAccount) && checkAccountValidity(fromAccount)) {
				
				if (checkAccountBalance(fromAccount) > amount) {
					Account from = accountRepository.getById(fromAccount);
					Account to = accountRepository.getById(toAccount);
					from.setBalance(from.getBalance() - amount);
					to.setBalance(to.getBalance()+amount);
					accountRepository.save(from);
					accountRepository.save(to);
					transaction.setTransactionStatus("SUCCESS");
					transactionRepository.save(transaction);	
					getAllTransactionsFromAccount(fromAccount);
					return new ResponseMessage("Transaction Successful",201);
					
				} else {
					transaction.setTransactionStatus("FAILED");
					transactionRepository.save(transaction);
					return new ResponseMessage("Insufficient Balance",400);
				}

			} else {
				transaction.setTransactionStatus("FAILED");
				transactionRepository.save(transaction);
				return new ResponseMessage("Account Deactivated or Account does not exist",400);
			}

		} else {
			transaction.setTransactionStatus("FAILED");
			transactionRepository.save(transaction);
			return new ResponseMessage("Sender and receiver account cannot be same!",400);
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


	

}
