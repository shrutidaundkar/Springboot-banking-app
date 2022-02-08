package com.xorbank.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xorbank.model.Account;
import com.xorbank.model.User;
import com.xorbank.request.AccountRequest;
import com.xorbank.request.UserRequest;
import com.xorbank.response.MessageResponse;
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
	public MessageResponse signUp(@RequestBody AccountRequest accountRequest) throws Exception {

		User user = signupService.getUser(accountRequest.getUserId());
		Account account = new Account();
		account.setAccountType(accountRequest.getAccountType());
		account.setUser(user);
		account.setBalance(accountRequest.getBalance());
		account.setDateCreated(LocalDateTime.now().toString());

		if(accountCreationService.createAccount(account)) {
			return new MessageResponse("Account Created Successfully!", 201);
		}else {
			return new MessageResponse("Account could not be created!",400);
		}
	}

	@GetMapping(path = "all-accounts/{userId}")
	public List<Account> getAllAccounts(@PathVariable("userId") Integer userId) {
		return profileService.findByUserId(userId).getAccounts();

	}

	@PutMapping(path = "account/deactivate")
	public MessageResponse deactivateAccount(@RequestBody UserRequest userRequest) throws Exception { 
		Account account = accountCreationService.getAccount(userRequest.getAccountId());
		account.setAccountStatus(false);
		if (accountCreationService.updateAccount(account) != null)
			return new MessageResponse("Account Deactivated", 201);
		else
			throw new Exception("Error Occured");
	}

}
