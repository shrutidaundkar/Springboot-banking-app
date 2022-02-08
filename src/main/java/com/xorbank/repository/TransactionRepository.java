package com.xorbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xorbank.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
	
	List<Transaction> findTransactionByFromAccount(int accountId);
	List<Transaction> findTransactionByToAccount(int accountId);
}
