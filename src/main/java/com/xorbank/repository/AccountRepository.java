package com.xorbank.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xorbank.models.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	List<Account> findAccountByuser(int userId);
	Boolean getAccountStatusByAccountId(int accountId);
	//Boolean updateAccountStatusByAccountId(int accountId, boolean newStatus);
	Account[] findByUser(int userId);
}
