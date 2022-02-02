package com.xorbank.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xorbank.exceptions.UserNotFoundException;
import com.xorbank.models.User;
import com.xorbank.repository.UserRepository;
import com.xorbank.services.SignUpService;

@Service
public class SignUpServiceImpl implements SignUpService{
	
	@Autowired
	private UserRepository repo;
	
	public SignUpServiceImpl() {}
	
	public SignUpServiceImpl(UserRepository repo){
		super();
		this.repo=repo;
	}
	public User saveUser(User user){
		return repo.save(user);
	}

	public User getUser(Integer userId) {
		return repo.getById(userId);
	}

	@Override
	public boolean checkEmail(String email) {
		if( repo.findByEmail(email) != null)
			return true;
		return false;
	}

	@Override
	public boolean checkMobileNumber(String mobile) {
		if( repo.findByMobile(mobile) != null)
			return true;
		return false;
	}
	
}
