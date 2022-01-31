package com.xorbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<com.xorbank.models.Account, Integer>{
	
}
