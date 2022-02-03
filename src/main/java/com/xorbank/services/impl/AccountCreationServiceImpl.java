package com.xorbank.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xorbank.models.Account;
import com.xorbank.repository.AccountRepository;
import com.xorbank.services.AccountCreationService;


@Service
public class AccountCreationServiceImpl implements AccountCreationService{
	
	private final AccountRepository repository;
	
	@Autowired
	public AccountCreationServiceImpl(AccountRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Account createAccount(Account a) {
		
		return repository.save(a);
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
		// TODO Auto-generated method stub
		System.out.println(repository.getById(accountId));
		return repository.getById(accountId);
	}

	@Override
	public Account updateAccount(Account account) {
		// TODO Auto-generated method stub
		return repository.save(account);
	}
	

}
