package com.xorbank.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xorbank.model.Account;
import com.xorbank.model.LoanAccount;
import com.xorbank.repository.AccountRepository;
import com.xorbank.repository.LoanAccountRepository;
import com.xorbank.services.AccountCreationService;


@Service
public class AccountCreationServiceImpl implements AccountCreationService{
	
	private final AccountRepository repository;
	private final LoanAccountRepository loanRepository;
	@Autowired
	public AccountCreationServiceImpl(AccountRepository repository,LoanAccountRepository loanRepository ) {
		super();
		this.repository = repository;
		this.loanRepository=loanRepository;
	}

	@Override
	public boolean createAccount(Account a) {
		
		return repository.save(a).getAccountStatus();
	}

	@Override
	public List<Account> getAllAccounts(int userId) {
		return repository.findAll();
	}

	@Override
	public boolean getAccountStatus(int accountId) {
	
		return repository.getById(accountId).getAccountStatus();
	}

	@Override
	public Account getAccount(int accountId) {
		System.out.println(repository.getById(accountId));
		return repository.getById(accountId);
	}

	@Override
	public Account updateAccount(Account account) {
		return repository.save(account);
	}
	@Override
	public boolean createLoanAccount(LoanAccount loanAccount) {
		loanRepository.save(loanAccount);
		return true;
		
	}
}
