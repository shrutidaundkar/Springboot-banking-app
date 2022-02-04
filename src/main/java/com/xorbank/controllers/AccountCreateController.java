package com.xorbank.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xorbank.exceptions.ResponseMessage;
import com.xorbank.models.Account;
import com.xorbank.models.AccountCred;
import com.xorbank.models.User;
import com.xorbank.models.UserBody;
import com.xorbank.services.AccountCreationService;
import com.xorbank.services.ProfileService;
import com.xorbank.services.SignUpService;

@RestController
@RequestMapping(path = "/server")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountCreateController {
	@Autowired
	private AccountCreationService accountCreationService;

	@Autowired
	private SignUpService signupService;

	@Autowired
	private ProfileService profileService;

	public AccountCreateController(AccountCreationService accountCreationService, SignUpService signupService) {
		super();
		this.accountCreationService = accountCreationService;
		this.signupService = signupService;
	}

	@PostMapping(path = "/account")
	public ResponseMessage signUp(@RequestBody AccountCred accountCred) throws Exception {

		User user = signupService.getUser(accountCred.getUserId());
		Account account = new Account();
		account.setAccountType(accountCred.getAccountType());
		account.setUser(user);
		account.setBalance(accountCred.getBalance());
		account.setDateCreated(LocalDateTime.now().toString());

		if(accountCreationService.createAccount(account)) {
			return new ResponseMessage("Account Created Successfully!", 201);
		}else {
			return new ResponseMessage("Account could not be created!",400);
		}
	}

	@GetMapping(path = "all-accounts/{userid}")
	public List<Account> getAllAccounts(@PathVariable("userid") Integer userId) {
		return profileService.findByUserId(userId).getAccounts();

	}

	@PutMapping(path = "account/deactivate")
	public ResponseMessage deactivateAccount(@RequestBody UserBody user) throws Exception { 
		Account account = accountCreationService.getAccount(user.getAccountId());
		account.setAccountStatus(false);
		if (accountCreationService.updateAccount(account) != null)
			return new ResponseMessage("Account Deactivated", 201);
		else
			throw new Exception("Error Occured");
	}

}
