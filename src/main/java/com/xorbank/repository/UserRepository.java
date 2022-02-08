package com.xorbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xorbank.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	
	User findByEmail(String email);
	User findOneByEmailAndPassword(String email,String password);
	User findByResetPasswordToken(String token);
	User findByMobile(String mobile);
	User findByEmailVerificationCode(String code);
	User findByUserId(Integer userId);
	
}
