package com.xorbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.xorbank.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
	
	List<Transaction> findTransactionByFrom(int accountId);
	List<Transaction> findTransactionByTo(int accountId);
	
}
