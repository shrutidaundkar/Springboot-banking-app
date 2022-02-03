package com.xorbank.services;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;

import com.xorbank.models.User;

@Service
public interface SignUpService {

	public User saveUser(User user);

	public boolean checkEmail(String email);

	public boolean checkMobileNumber(String mobile);

	public User getUser(Integer userId);

	public void sendVerificationEmail(User user, String siteURL) throws UnsupportedEncodingException, MessagingException;

}
