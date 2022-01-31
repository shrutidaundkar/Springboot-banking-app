package com.xorbank.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xorbank.models.Account;
import com.xorbank.repository.AccountRepository;
import com.xorbank.services.AccountCreationService;


@Service
public class AccountCreationServiceImpl implements AccountCreationService{


	private AccountRepository repository;
	
	@Autowired
	public AccountCreationServiceImpl(AccountRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Account createAccount(Account a) {
		
		return repository.save(a);
	}
	
	

}
