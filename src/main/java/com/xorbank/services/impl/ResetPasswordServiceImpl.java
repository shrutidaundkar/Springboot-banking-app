package com.xorbank.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.xorbank.exceptions.UserNotFoundException;
import com.xorbank.model.User;
import com.xorbank.repository.UserRepository;

@Service
@Transactional
public class ResetPasswordServiceImpl {

    @Autowired
    private UserRepository repo;
    

	@Autowired
	private PasswordEncoder passwordEncoder;
     
 
    public void updateResetPasswordToken(String resetPasswordToken, String email) throws UserNotFoundException {
        User user = repo.findByEmail(email);
        if (user != null) {
            user.setResetPasswordToken(resetPasswordToken);
            repo.save(user);
        } else {
            throw new UserNotFoundException("Could not find any user with the email " + email);
        }
    }
     
    public User getByResetPasswordToken(String resetPasswordToken) {
        return repo.findByResetPasswordToken(resetPasswordToken);
    }
     
    public void updatePassword(User user, String newPassword) {
		String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword); 
        user.setResetPasswordToken(null);
        repo.save(user);
    }
    
    public User findByEmail(String email) {
		return repo.findByEmail(email);
    	
    }
}