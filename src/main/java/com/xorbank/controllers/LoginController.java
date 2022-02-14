package com.xorbank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xorbank.exceptions.UserNotFoundException;
import com.xorbank.model.User;
import com.xorbank.request.LoginRequest;
import com.xorbank.response.MessageResponse;
import com.xorbank.services.impl.LoginServiceImpl;

@RestController
//@RequestMapping("/server")
@PropertySource("classpath:xorbankUrl.properties")
@RequestMapping("${server.context-path}")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
	
	@Autowired
	private LoginServiceImpl loginService;

	
//	@PostMapping("/login")
	@PostMapping("${USER_LOGIN}")
	public MessageResponse findByEmail( @RequestBody LoginRequest login) throws UserNotFoundException,Exception{
		User user=loginService.findByEmail(login);
		if(user==null) {
			return new MessageResponse("Invalid Email or Password",400);
		}else {
			return new MessageResponse("Login Successful",user.getUserId());
		}
	}
}
