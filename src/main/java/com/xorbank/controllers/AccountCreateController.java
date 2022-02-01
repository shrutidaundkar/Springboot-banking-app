package com.xorbank.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xorbank.models.Account;
import com.xorbank.services.AccountCreationService;
import com.xorbank.services.SignUpService;

@RestController
@RequestMapping(path="/server")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountCreateController {

	private AccountCreationService service;
	private SignUpService signupService;
	

	public AccountCreateController(AccountCreationService service, SignUpService signupService) {
		super();
		this.service = service;
		this.signupService = signupService;
	}


	@PostMapping(path="account/{userId}")
	public  ResponseEntity<Account> signUp(@RequestBody Account account,@PathVariable("userId") String userId) {
		System.out.println("Account Controller "+account);
		//service.signup(user);
		account.setUser(signupService.getUser(Integer.parseInt(userId)));
		System.out.println("Account Controller "+account);
		
		return new ResponseEntity<Account>(service.createAccount(account),HttpStatus.CREATED);
		//ResponseEntity<Account> responseEntity = new ResponseEntity<Account>(service.createAccount(account),HttpStatus.CREATED);
		//return responseEntity.getStatusCode();
	}

}
