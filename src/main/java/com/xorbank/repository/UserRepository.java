package com.xorbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.xorbank.models.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	
	User findByEmail(String email);
	User findOneByEmailAndPassword(String email,String password);
	User findByResetPasswordToken(String token);

}
