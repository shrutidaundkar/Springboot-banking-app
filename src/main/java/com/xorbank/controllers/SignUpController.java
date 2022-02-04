package com.xorbank.controllers;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xorbank.ConstantMessages;
import com.xorbank.exceptions.ResponseMessage;
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
	public ResponseMessage signUpUser(@RequestBody User user) 
			throws UnsupportedEncodingException, MessagingException {
		
		if (!signupService.checkEmail(user.getEmail())) {
			if (!signupService.checkMobileNumber(user.getMobile())) {

				String randomCode = RandomString.make(5);
				user.setEmailVerificationCode(randomCode);
				user.setEmailverified(false);
				String site_url = ConstantMessages.getSiteurl();

				if (signupService.saveUser(user)) {
					//signupService.sendVerificationEmail(user, site_url);
					return new ResponseMessage("Registration Successful!", 201);
					
				} else {
					return new ResponseMessage("Registration Unsuccessful!", 400);
				}

			} else {
				return new ResponseMessage("Duplicate Mobile Number!", 400);

			}
		} else
			return new ResponseMessage("Duplicate Email!", 400);
	}

	@PostMapping("/verify") 
	public ResponseMessage verifyUser(@RequestBody User user) {
		if (signupService.verify(user.getEmailVerificationCode())) {
			return new ResponseMessage("verification successful", 201); 
		} else {
			return new ResponseMessage("verification failed", 201);
		}
	}

	@GetMapping("/all-users")
	public ResponseEntity<Iterable<User>> getAllUsers() {
		return ResponseEntity.ok().body(adminService.getAllUsers());
	}

}
