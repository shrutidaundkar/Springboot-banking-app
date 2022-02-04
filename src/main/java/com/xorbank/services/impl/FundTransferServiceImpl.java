package com.xorbank.services.impl;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.xorbank.ConstantMessages;
import com.xorbank.models.Account;
import com.xorbank.models.Transaction;
import com.xorbank.models.User;
import com.xorbank.repository.AccountRepository;
import com.xorbank.repository.TransactionRepository;
import com.xorbank.repository.UserRepository;
import com.xorbank.services.FundTransferService;
import com.xorbank.services.SignUpService;

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
	public Transaction sendAmount(int fromAccount, int toAccount, double amount) throws Exception {
		Transaction transaction = new Transaction();
		transaction.setFromAccount(fromAccount);
		transaction.setToAccount(toAccount);
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
					transaction.setDescription("Amount successfully transfered!");
					transactionRepository.save(transaction);					
					
				} else {
					transaction.setTransactionStatus("FAILED");
					transaction.setDescription("Insufficient Balance");
					transactionRepository.save(transaction);
					throw new Exception("Insufficient Balance");
				}

			} else {
				transaction.setTransactionStatus("FAILED");
				transaction.setDescription("Account Deactivated");
				transactionRepository.save(transaction);
				throw new Exception("Account Deactivated");
			}

		} else {
			transaction.setTransactionStatus("FAILED");
			transaction.setDescription("Sender and receiver account cannot be same!");
			transactionRepository.save(transaction);
			throw new Exception("Sender and receiver account cannot be same!");
		}
		return transaction;
	}

	

}
