package com.xorbank.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
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
import com.xorbank.model.LoanAccount;
import com.xorbank.model.User;
import com.xorbank.request.AccountRequest;
import com.xorbank.request.LoanAccountRequest;
import com.xorbank.request.UserRequest;
import com.xorbank.response.MessageResponse;
import com.xorbank.services.AccountService;
import com.xorbank.services.ProfileService;
import com.xorbank.services.SignUpService;
import com.xoriant.utility.AccountPDFExporter;

@RestController
@RequestMapping(path = "/server")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {
	@Autowired
	private AccountService accountService;

	@Autowired
	private SignUpService signupService;

	@Autowired
	private ProfileService profileService;

	public AccountController(AccountService accountCreationService, SignUpService signupService) {
		super();
		this.accountService = accountCreationService;
		this.signupService = signupService;
	}

	@PostMapping(path = "/account")
	public MessageResponse signUp(@RequestBody AccountRequest accountRequest) throws Exception {

		User user = signupService.getUser(accountRequest.getUserId());
		Account account = new Account();
		account.setAccountType(accountRequest.getAccountType());
		account.setUser(user);
		account.setBalance(accountRequest.getBalance());
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
		account.setDateCreated(currentDateTime);

		if(accountService.createAccount(account)) {
			return new MessageResponse("Account Created Successfully!", 201);
		}else {
			return new MessageResponse("Account could not be created!",400);
		}
	}

	@PostMapping(path = "/loan-account")
	public MessageResponse createloanAccount(@RequestBody LoanAccountRequest loanAccountReq) throws Exception {
		User user = profileService.findByUserId(loanAccountReq.getUserId());
		Account account=accountService.getAccount(loanAccountReq.getAccountId());
		System.out.println("Account"+account);
		LoanAccount loanAccount = new LoanAccount();
		loanAccount.setUser(user);
		loanAccount.setAccount(account);
		loanAccount.setBalance(loanAccountReq.getBalance());
		loanAccount.setLoanType(loanAccountReq.getLoanType());
		loanAccount.setTenure(loanAccountReq.getTenure());
		loanAccount.setMonthlyEMI(loanAccountReq.getMonthlyEMI());
		System.out.println("Loan Account"+loanAccount);
		if(accountService.createLoanAccount(loanAccount)) {
			return new MessageResponse("Loan Account Created Successfully!", 201);
		}else {
			return new MessageResponse("Loan Account could not be created!",400);
		}
	}
	
	@GetMapping(path = "all-accounts/{userId}")
	public List<Account> getAllAccounts(@PathVariable("userId") Integer userId) {
		return profileService.findByUserId(userId).getAccounts();

	}

	@PutMapping(path = "account/deactivate")
	public MessageResponse deactivateAccount(@RequestBody UserRequest userRequest) throws Exception { 
		Account account = accountService.getAccount(userRequest.getAccountId());
		account.setAccountStatus(false);
		if (accountService.updateAccount(account) != null)
			return new MessageResponse("Account Deactivated", 201);
		else
			throw new Exception("Error Occured");
	}

	@GetMapping("/account/exportPdf/{userId}")
    public void exportToPDF(HttpServletResponse response,@PathVariable("userId") Integer userId) throws DocumentException, IOException, com.lowagie.text.DocumentException {
        response.setContentType("application/pdf"); 
        String headerKey = "Content-Disposition";
        String headerValue = "attachment";
        response.setHeader(headerKey, headerValue);
         
        List<Account> listAccounts = accountService.getAllAccounts(userId);
         
        AccountPDFExporter exporter = new AccountPDFExporter(listAccounts);
        exporter.export(response);
    }
}
