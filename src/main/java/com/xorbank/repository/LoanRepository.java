package com.xorbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xorbank.model.Loan;

public interface LoanRepository extends  JpaRepository<Loan, Integer> {
	
}
