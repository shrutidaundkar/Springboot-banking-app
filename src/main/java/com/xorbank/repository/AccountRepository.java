package com.xorbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xorbank.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	Boolean getAccountStatusByAccountId(int accountId);
	Account[] findByUser(int userId);
	Account findByAccountId(int accountId);
}
