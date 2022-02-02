package com.xorbank.services;

import org.springframework.stereotype.Service;

import com.xorbank.models.User;

@Service
public interface  SignUpService {
	
	public User saveUser(User user);
	public boolean checkEmail(String email);
	public boolean checkMobileNumber(String mobile);
	public User getUser(Integer userId);
	
}
