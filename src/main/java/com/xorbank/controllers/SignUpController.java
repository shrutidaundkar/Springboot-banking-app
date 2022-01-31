package com.xorbank.controllers;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xorbank.models.LoginCred;
import com.xorbank.models.User;
import com.xorbank.services.impl.AdminServiceImpl;
import com.xorbank.services.impl.LoginServiceImpl;
import com.xorbank.services.impl.SignUpServiceImpl;

@RestController
@RequestMapping("/server")
public class SignUpController {
	
	@Autowired
	private SignUpServiceImpl signupService;
	
	@Autowired
	private AdminServiceImpl adminService;
	
	@PostMapping("/save")
	@Transactional	
	public String signUpUser(@RequestBody User user)
	{
		signupService.saveUser(user);
		return user.getFirstname()+"  SignUp Successful..!";
		
	}
	
	
	@GetMapping("/all-users")
	public ResponseEntity<Iterable<User>> getAllUsers() {
		return ResponseEntity.ok().body(adminService.getAllUsers()) ;
	}

}
