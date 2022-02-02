package com.xorbank.services;

import com.xorbank.models.User;

public interface ProfileService {
	
	User findByUserId(Integer userid);
	
	public User updateUser(User user);
	
}
