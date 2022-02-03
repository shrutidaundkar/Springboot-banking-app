package com.xorbank.controllers;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xorbank.ConstantMessages;
import com.xorbank.models.User;
import com.xorbank.services.impl.AdminServiceImpl;
import com.xorbank.services.impl.SignUpServiceImpl;

import net.bytebuddy.utility.RandomString;

@RestController
@RequestMapping("/server")
@CrossOrigin(origins = "http://localhost:4200")
public class SignUpController {

	@Autowired
	private SignUpServiceImpl signupService;

	@Autowired
	private AdminServiceImpl adminService;

	@PostMapping("/save")
	@Transactional
	public int signUpUser(@RequestBody User user) throws UnsupportedEncodingException, MessagingException {
		if (!signupService.checkEmail(user.getEmail())) {
			if (!signupService.checkMobileNumber(user.getMobile())) {

				String randomCode = RandomString.make(5);
				user.setEmailVerificationCode(randomCode);
				user.setEmailverified(false);
				String site_url = ConstantMessages.getSiteurl();

				ResponseEntity<User> resp = new ResponseEntity<User>(signupService.saveUser(user), HttpStatus.CREATED);

				signupService.sendVerificationEmail(user, site_url);

				return resp.getStatusCodeValue();
			} else {
				return 1001; // Mobile number already exists
			}
		} else
			return 1002; // Email already exists
	}
	
	
	@PostMapping("/verify")
	public String verifyUser(@RequestBody User user) {
	    if (signupService.verify(user.getEmailVerificationCode())) {
	        return "verification successful";
	    } else {
	        return "verification failed";
	    }
	}

	@GetMapping("/all-users")
	public ResponseEntity<Iterable<User>> getAllUsers() {
		return ResponseEntity.ok().body(adminService.getAllUsers());
	}

}
