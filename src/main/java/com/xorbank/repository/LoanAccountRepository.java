package com.xorbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xorbank.model.LoanAccount;

public interface LoanAccountRepository extends  JpaRepository<LoanAccount, Integer> {
	

}
