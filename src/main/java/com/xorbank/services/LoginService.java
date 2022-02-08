package com.xorbank.services;

import org.springframework.stereotype.Service;

import com.xorbank.exceptions.UserNotFoundException;
import com.xorbank.model.User;
import com.xorbank.request.LoginRequest;

@Service
public interface LoginService {

	public User findByEmail(LoginRequest login) throws UserNotFoundException;

}
