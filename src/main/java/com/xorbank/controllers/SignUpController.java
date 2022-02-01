package com.xorbank.controllers;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:4200")
public class SignUpController {
	
	@Autowired
	private SignUpServiceImpl signupService;
	
	@Autowired
	private AdminServiceImpl adminService;
	
	@PostMapping("/save")
	@Transactional	
	public int signUpUser(@RequestBody User user)
	{
		ResponseEntity<User> resp = new ResponseEntity<User>(signupService.saveUser(user), HttpStatus.CREATED);
		return resp.getStatusCodeValue(); 
		
	}
	
	
	@GetMapping("/all-users")
	public ResponseEntity<Iterable<User>> getAllUsers() {
		return ResponseEntity.ok().body(adminService.getAllUsers()) ;
	}

}
