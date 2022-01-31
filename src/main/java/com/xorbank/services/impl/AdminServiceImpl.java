package com.xorbank.services.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xorbank.models.Account;
import com.xorbank.models.User;
import com.xorbank.repository.UserRepository;
import com.xorbank.services.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UserRepository repo;
	
	public AdminServiceImpl() {}
	
	public AdminServiceImpl(UserRepository repo)
	{
		super();
		this.repo=repo;
	}
	
	public Iterable<User> getAllUsers() {
		return  repo.findAll() ;
	}

	@Override
	public Account createAccount(Account a) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
