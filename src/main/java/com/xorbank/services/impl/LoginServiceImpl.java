package com.xorbank.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xorbank.exceptions.UserNotFoundException;
import com.xorbank.models.LoginCred;
import com.xorbank.models.User;
import com.xorbank.repository.UserRepository;
import com.xorbank.services.LoginService;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private UserRepository repo;

	public User findOneByEmailAndPassword(LoginCred login)throws UserNotFoundException	{
		
		User user1 = repo.findByEmail(login.getEmail());
		User user = repo.findOneByEmailAndPassword(login.getEmail(), login.getPassword());

		if (user1 == null)
			throw new UserNotFoundException("User with email "+login.getEmail()+" is not registered, Please SignUp.");

		if (user == null)
			throw new UserNotFoundException("Password Incorrect, Please try again..!");
		else
			return user;
	}

}
