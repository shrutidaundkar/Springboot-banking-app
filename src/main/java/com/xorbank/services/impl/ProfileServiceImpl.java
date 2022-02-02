package com.xorbank.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xorbank.models.User;
import com.xorbank.repository.UserRepository;
import com.xorbank.services.ProfileService;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService{
	
	private final UserRepository userRepo;
	
	@Autowired
	public ProfileServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public User findByUserId(Integer userid) {
		
		return userRepo.findByuserid(userid);
	}
	
	public User updateUser(User user)
	{
		return userRepo.save(user);
	}
}
