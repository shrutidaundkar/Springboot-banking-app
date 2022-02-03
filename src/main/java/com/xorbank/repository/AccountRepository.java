package com.xorbank.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xorbank.models.Account;
import com.xorbank.models.User;

public interface AccountRepository extends JpaRepository<com.xorbank.models.Account, Integer>{
	List<Account> findAccountByuser(int userId);
	Boolean getAccountStatusByAccountId(int accountId);
	//Boolean updateAccountStatusByAccountId(int accountId, boolean newStatus);
	Account[] findByUser(int userId);
}
