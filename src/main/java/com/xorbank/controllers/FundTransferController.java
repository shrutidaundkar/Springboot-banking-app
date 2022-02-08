package com.xorbank.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xorbank.ConstantMessages;
import com.xorbank.exceptions.UserNotFoundException;
import com.xorbank.model.Transaction;
import com.xorbank.model.User;
import com.xorbank.request.TransactionRequest;
import com.xorbank.response.MessageResponse;
import com.xorbank.services.FundTransferService;

import net.bytebuddy.utility.RandomString;

@RestController
@RequestMapping("/server")
@CrossOrigin(origins = "http://localhost:4200")
public class FundTransferController {
	
	@Autowired
	private FundTransferService fundTransferService;
	
	@Autowired
	private JavaMailSender mailSender;

	
	@PutMapping("/transfer")
	public MessageResponse fundTransfer(@RequestBody TransactionRequest transactionRequest) throws Exception{
		System.out.println(transactionRequest);
		if(fundTransferService.checkOTP(transactionRequest.getUserId(),transactionRequest.getOtp())) 
		{
			if(fundTransferService.checkAccountValidity(transactionRequest.getFromAccount()) && fundTransferService.getAccountStatus(transactionRequest.getFromAccount())) 
			{
				return fundTransferService.sendAmount(transactionRequest.getFromAccount(), transactionRequest.getToAccount(), transactionRequest.getAmount(),
						transactionRequest.getDescription()); 	
			}else {
				return new MessageResponse("Account invalid or deactivated!",400);
			}
		}else {
			return new MessageResponse("Invalid OTP",400);
		}
		
	}
	
	@GetMapping(path="/history/{fromAccountId}")
	public List<Transaction> getHistoryOf(@PathVariable("fromAccountId") Integer accountId){
		return fundTransferService.getAllTransactionsFromAccount(accountId);
	}
	
	@GetMapping(path="/otp/{userId}")
	public MessageResponse sendOTP(@PathVariable("userId") Integer userId) throws UnsupportedEncodingException, MessagingException, UserNotFoundException{
		String otp = RandomString.make(5);

		User user=fundTransferService.getUser(userId);
		if(user == null) {
			return new MessageResponse("User does not exist with this Email",400);
		}else {
			fundTransferService.updateOTP(otp, user.getEmail());
			sendMail(user.getEmail(), otp);
			return new MessageResponse("OTP send on Registered Email",201);
		}
	}
	
	public void sendMail(String email,String otp) throws UnsupportedEncodingException, MessagingException {
		String toAddress = email;
		String subject = "Transaction OTP";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom(ConstantMessages.getFromemailaddress(), ConstantMessages.getEmailsendername());
		helper.setTo(toAddress);
		helper.setSubject(subject);

		helper.setText("Your OTP for Fund Transfer is "+otp, true);

		mailSender.send(message);
	}
}
