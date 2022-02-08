package com.xorbank.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xorbank.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	List<Account> findAccountByuser(int userId);
	Boolean getAccountStatusByAccountId(int accountId);
	Account[] findByUser(int userId);
}
