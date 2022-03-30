package com.xorbank.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xorbank.model.Account;
import com.xorbank.model.Loan;
import com.xorbank.repository.AccountRepository;
import com.xorbank.repository.LoanRepository;
import com.xorbank.services.LoanService;


@Service
public class LoanServiceImpl implements LoanService{
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	public LoanServiceImpl(LoanRepository loanRepository,AccountRepository accountRepository){
		super();
		this.loanRepository=loanRepository;
		this.accountRepository=accountRepository;
	}

	@Override
	public boolean createLoanAccount(Loan loanAccount,Account account) {
		accountRepository.save(account);
		loanRepository.save(loanAccount);
		return true;
		
	}

	@Override
	public List<Loan> findAllLoans(int userId) {
		return loanRepository.findByUserUserId(userId);
	}

	@Override
	public boolean checkAccountNo(int userId, int accountId) {
		if(loanRepository.findLoanByUserIdAccountID(userId,accountId) != null) {
			return true;
		}
		return false;
	}
}
