package com.xorbank.services;

import org.springframework.stereotype.Service;

import com.xorbank.exceptions.UserNotFoundException;
import com.xorbank.models.LoginCred;

@Service
public interface LoginService {

	public String findOneByEmailAndPassword(LoginCred login) throws UserNotFoundException;

}
