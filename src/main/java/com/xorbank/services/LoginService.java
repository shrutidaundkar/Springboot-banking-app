package com.xorbank.services;

import org.springframework.stereotype.Service;

import com.xorbank.exceptions.UserNotFoundException;
import com.xorbank.models.LoginCred;
import com.xorbank.models.User;

@Service
public interface LoginService {

	public User findOneByEmailAndPassword(LoginCred login) throws UserNotFoundException;

}
