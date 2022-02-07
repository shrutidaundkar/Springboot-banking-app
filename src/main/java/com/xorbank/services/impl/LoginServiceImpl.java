package com.xorbank.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User findOneByEmailAndPassword(LoginCred login)throws UserNotFoundException	{
		
		User user = repo.findByEmail(login.getEmail());

		if (user == null)
			throw new UserNotFoundException(ConstantMessages.getEmailNotRegisteredMessage());

		boolean iscorrect = passwordEncoder.matches(login.getPassword(), user.getPassword());
		if (iscorrect == false)
			throw new UserNotFoundException(ConstantMessages.getPasswordincorrectmessage());
		else
			return user;
	}

}
