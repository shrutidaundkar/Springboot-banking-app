package com.xorbank.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xorbank.exceptions.ResponseMessage;
import com.xorbank.models.Transaction;
import com.xorbank.services.FundTransferService;

@RestController
@RequestMapping("/server")
@CrossOrigin(origins = "http://localhost:4200")
public class FundTransferController {
	
	@Autowired
	private FundTransferService fundTransferService;

	
	@PutMapping("/transfer")
	public ResponseMessage fundTransfer(@RequestBody Transaction transactionDetails) throws Exception{
		System.out.println("Fund:"+transactionDetails.getFromAccount());
		if(fundTransferService.checkAccountValidity(transactionDetails.getFromAccount()) && fundTransferService.getAccountStatus(transactionDetails.getFromAccount())) {
			return fundTransferService.sendAmount(transactionDetails.getFromAccount(), transactionDetails.getToAccount(), transactionDetails.getAmount(),
					transactionDetails.getDescription()); 
		}
		return new ResponseMessage("Account invalid or deactivated!",400);
	}
	
	@GetMapping(path="/history/{fromAccountId}")
	public List<Transaction> getHistoryOf(@PathVariable("fromAccountId") Integer accountId){
		return fundTransferService.getAllTransactionsFromAccount(accountId);
	}
}
