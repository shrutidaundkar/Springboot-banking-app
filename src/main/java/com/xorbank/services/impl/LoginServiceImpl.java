package com.xorbank.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xorbank.ConstantMessages;
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
		
		User find_user_by_email = repo.findByEmail(login.getEmail());
		User user = repo.findOneByEmailAndPassword(login.getEmail(), login.getPassword());

		if (find_user_by_email == null)
			throw new UserNotFoundException(ConstantMessages.getEmailNotRegisteredMessage());

		if (user == null)
			throw new UserNotFoundException(ConstantMessages.getPasswordincorrectmessage());
		else
			return user;
	}

}
