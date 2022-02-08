package com.xorbank.services;

import com.xorbank.model.User;

public interface ProfileService {
	
	User findByUserId(Integer userId);
	
	public User updateUser(User user);
	
}
