package com.xorbank.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.xorbank.models.Account;

public interface AccountRepository extends JpaRepository<com.xorbank.models.Account, Integer>{
	Account[] findAllByUser(int userId);
	Boolean getAccountStatusByAccountId(int accountId);
	//Account findByUserId(int userId);
	//Boolean updateAccountStatusByAccountId(int accountId, boolean newStatus);
}
