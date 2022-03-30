package com.xorbank.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xorbank.model.Account;
import com.xorbank.model.Loan;
import com.xorbank.model.User;
import com.xorbank.request.LoanRequest;
import com.xorbank.response.MessageResponse;
import com.xorbank.services.AccountService;
import com.xorbank.services.LoanService;
import com.xorbank.services.ProfileService;

@RestController
//@RequestMapping(path = "/server")
@RequestMapping("${server.context-path}")
@CrossOrigin(origins = "http://localhost:4200")
@PropertySource("classpath:xorbankUrl.properties")
public class LoanController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private LoanService loanService;

	@Autowired
	private ProfileService profileService;

	public LoanController(LoanService loanService, ProfileService profileService, AccountService accountService) {
		super();
		this.loanService = loanService;
		this.profileService = profileService;
		this.accountService = accountService;
	}

	//@PostMapping(path = "/loan-account")
	@PostMapping("${CREATE_LOAN}")
	public MessageResponse createloanAccount(@RequestBody LoanRequest loanRequest) throws Exception {
		User user = profileService.findByUserId(loanRequest.getUserId());
		Account account=accountService.getAccount(loanRequest.getAccountId());
		Loan loanAccount = new Loan();
		loanAccount.setUser(user);
		loanAccount.setAccount(account);
		loanAccount.setRemainingAmount(loanRequest.getRemainingAmount());
		account.setBalance(account.getBalance()+loanRequest.getLoanAmount());
		loanAccount.setLoanAmount(loanRequest.getLoanAmount());
		loanAccount.setLoanPurpose(loanRequest.getLoanPurpose());
		loanAccount.setInterest(loanRequest.getInterest());
		loanAccount.setTenureInMonths(loanRequest.getTenureInMonths());
		loanAccount.setMonthlyEMI(loanRequest.getMonthlyEMI());
		if(loanService.createLoanAccount(loanAccount,account)) {
			return new MessageResponse("Loan Process Successful", 201);
		}else {
			return new MessageResponse("Loan Process Unsuccessful",400);
		}
	}
	
	//@GetMapping(path = "all-loans/{userId}")
	@GetMapping("${GET_ALL_LOANS}")
	public List<Loan> getAllLoans(@PathVariable("userId") Integer userId) {
		return loanService.findAllLoans(userId);
		//return profileService.findByUserId(userId).getLoans();
	}
	
	@GetMapping("/check-account-no/{userId}/{accountId}")
	public MessageResponse checkAccountNo(@PathVariable("userId") Integer userId,@PathVariable("accountId") Integer accountId)
	{
		if(loanService.checkAccountNo(userId,accountId) ) {
			return new MessageResponse("Loan already taken on this account number", 400);
		}else {
			return new MessageResponse("Loan does not exist on this account number",201);
		}
	}
}
