package com.xorbank.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xorbank.models.Account;
import com.xorbank.models.AccountCred;
import com.xorbank.models.User;
import com.xorbank.models.UserBody;
import com.xorbank.services.AccountCreationService;
import com.xorbank.services.SignUpService;

@RestController
@RequestMapping(path="/server")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountCreateController {

	private AccountCreationService accountCreationService;
	private SignUpService signupService;
	

	public AccountCreateController(AccountCreationService accountCreationService, SignUpService signupService) {
		super();
		this.accountCreationService = accountCreationService;
		this.signupService = signupService;
	}


	@PostMapping(path="account/")
	public  ResponseEntity<Account> signUp(@RequestBody AccountCred accountCred) {
		
		User user= null;
		Account account = null;
		ResponseEntity<Account> response;
		try {
			user = signupService.getUser(accountCred.getUserId());
			account = new Account();
			account.setAccountType(accountCred.getAccountType());
			account.setUser(user);
			account.setBalance(accountCred.getBalance());
			account.setDateCreated(LocalDateTime.now().toString());
			
			response = new ResponseEntity<Account>(accountCreationService.createAccount(account),HttpStatus.CREATED);
		}
		catch(Exception e) {
			response = new ResponseEntity<Account>(new Account(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@PostMapping(path = "all-accounts/")
	public List<Account> getAllAccounts(@RequestBody UserBody userId){
		System.out.println("userId"+userId);
		return accountCreationService.getAllAccounts(userId.getUserid());
	}
	
	@PutMapping(path = "account/deactivate")
	public boolean deactivateAccount(@RequestBody UserBody user){
		Account account = accountCreationService.getAccount(user.getAccountId());
		account.setAccountStatus(false);
		if(accountCreationService.updateAccount(account)!= null)
			return true;
		else
			return false;
	}
	
}
