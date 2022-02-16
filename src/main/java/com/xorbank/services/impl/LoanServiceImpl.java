package com.xorbank.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xorbank.model.Loan;
import com.xorbank.repository.LoanRepository;
import com.xorbank.services.LoanService;


@Service
public class LoanServiceImpl implements LoanService{
	
	private final LoanRepository loanRepository;
	@Autowired
	public LoanServiceImpl(LoanRepository loanRepository ) {
		super();
		this.loanRepository=loanRepository;
	}

	@Override
	public boolean createLoanAccount(Loan loanAccount) {
		loanRepository.save(loanAccount);
		return true;
		
	}

	@Override
	public List<Loan> findAllLoans(int userId) {
		return loanRepository.findByUserUserId(userId);
	}
}
