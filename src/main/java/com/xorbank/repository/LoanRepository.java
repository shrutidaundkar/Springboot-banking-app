package com.xorbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xorbank.model.Loan;

public interface LoanRepository extends  JpaRepository<Loan, Integer> {

	List<Loan> findByUserUserId(int userId);
	
	@Query(value="select * from loandata where user_user_id= ?1 and account_account_id= ?2",nativeQuery=true)
	Loan findLoanByUserIdAccountID(int userId,int accountId);
}
