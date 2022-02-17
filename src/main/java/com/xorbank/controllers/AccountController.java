package com.xorbank.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xorbank.model.Account;
import com.xorbank.model.ActivemqMessage;
import com.xorbank.model.User;
import com.xorbank.request.AccountRequest;
import com.xorbank.request.UserRequest;
import com.xorbank.response.MessageResponse;
import com.xorbank.services.AccountService;
import com.xorbank.services.ProfileService;
import com.xorbank.services.SignUpService;
import com.xorbank.utils.AccountPDFExporter;

@RestController
//@RequestMapping(path = "/server")
@RequestMapping("${server.context-path}")
@CrossOrigin(origins = "http://localhost:4200")
@PropertySource("classpath:xorbankUrl.properties")
public class AccountController {
	@Autowired
	private AccountService accountService;

	@Autowired
	private SignUpService signupService;

	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private JmsTemplate jmstemplate;

	public AccountController(AccountService accountService, SignUpService signupService) {
		super();
		this.accountService = accountService;
		this.signupService = signupService;
	}
	
	@PostMapping("/ActiveMq")
	public ResponseEntity<String> publicMessage(@RequestBody ActivemqMessage activemqMessage){

		//jmstemplate.convertAndSend(activemqMessage );
		jmstemplate.convertAndSend("Xorbank",activemqMessage );
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//@PostMapping(path = "/create-account")
	@PostMapping("${CREATE_ACCOUNT}")
	public MessageResponse signUp(@RequestBody AccountRequest accountRequest) throws Exception {
		User user = signupService.getUser(accountRequest.getUserId());
		Account account = new Account();
		account.setAccountType(accountRequest.getAccountType());
		account.setUser(user);
		account.setBalance(accountRequest.getBalance());
		account.setAccountStatus(true);
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormatter.format(new Date());
        account.setDateCreated(currentDate);
		
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
		LocalDateTime myTimeObj = LocalDateTime.now();
		String formattedTime = myTimeObj.format(myFormatObj);
		account.setTimeCreated(formattedTime);

		if(accountService.createAccount(account)) {
			return new MessageResponse("Account Created Successfully!", 201);
		}else {
			return new MessageResponse("Account could not be created!",400);
		}
	}
	
	//@GetMapping(path = "all-accounts/{userId}")
	@GetMapping("${GET_ALL_ACCOUNTS}")
	public List<Account> getAllAccounts(@PathVariable("userId") Integer userId) {
		return profileService.findByUserId(userId).getAccounts();

	}

	//@PutMapping(path = "account/deactivate")
	@PutMapping("${ACCOUNT_DEACTIVATE}")
	public MessageResponse deactivateAccount(@RequestBody UserRequest userRequest) throws Exception { 
		Account account = accountService.getAccount(userRequest.getAccountId());
		account.setAccountStatus(false);
		if (accountService.updateAccount(account) != null)
			return new MessageResponse("Account Deactivated", 201);
		else
			throw new Exception("Error Occured");
	}

	//@GetMapping("/account/exportPdf/{userId}")
	@GetMapping("${ACCOUNT_EXPORT_PDF}")
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
