package com.xorbank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xorbank.exceptions.ResponseMessage;
import com.xorbank.exceptions.UserNotFoundException;
import com.xorbank.models.LoginCred;
import com.xorbank.models.User;
import com.xorbank.services.impl.LoginServiceImpl;

@RestController
@RequestMapping("/server")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
	
	@Autowired
	private LoginServiceImpl loginService;

	
	@PostMapping("/login")
	public ResponseMessage findByEmail( @RequestBody LoginCred login) throws UserNotFoundException,Exception{
		User user=loginService.findByEmail(login);
		System.out.println(user);
		if(user==null) {
			return new ResponseMessage("Invalid Email or Password",400);
		}else {
			return new ResponseMessage("Login Successful",user.getUserid());
		}
	}
}
