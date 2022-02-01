package com.xorbank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xorbank.models.LoginCred;
import com.xorbank.services.impl.LoginServiceImpl;

@RestController
@RequestMapping("/server")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
	
	@Autowired
	private LoginServiceImpl loginService;

	
	@PostMapping("/login")
	public ResponseEntity<String> findOneByEmailAndPassword( @RequestBody LoginCred login){
		return ResponseEntity.ok().body(loginService.findOneByEmailAndPassword(login)) ;
	}
}
