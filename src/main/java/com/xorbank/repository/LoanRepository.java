package com.xorbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xorbank.model.Loan;

public interface LoanRepository extends  JpaRepository<Loan, Integer> {

	List<Loan> findByUserUserId(int userId);
	
}
