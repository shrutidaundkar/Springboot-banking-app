package com.xorbank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xorbank.models.Transaction;
import com.xorbank.services.FundTransferService;

@RestController
@RequestMapping("/server")
@CrossOrigin(origins = "http://localhost:4200")
public class FundTransferController {
	
	@Autowired
	private FundTransferService fundTransferService;

	
	@PutMapping("/transfer")
	public Transaction fundTransfer(@RequestBody Transaction transactionDetails) throws Exception{
		System.out.println("Fund:"+transactionDetails.getFromAccount());
		if(fundTransferService.checkAccountValidity(transactionDetails.getFromAccount()) && fundTransferService.getAccountStatus(transactionDetails.getFromAccount())) {
			Transaction transaction =  fundTransferService.sendAmount(transactionDetails.getFromAccount(), transactionDetails.getToAccount(), transactionDetails.getAmount());
			System.out.println(transaction);
			return transaction;
		}
		throw new Exception("Account invalid or deactivated!");
	}
}
