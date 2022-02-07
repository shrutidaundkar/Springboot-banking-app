package com.xorbank.controllers;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/save")
	@Transactional
	public ResponseMessage signUpUser(@RequestBody User user) throws UnsupportedEncodingException, MessagingException {

		if (!signupService.checkEmail(user.getEmail())) {
			if (!signupService.checkMobileNumber(user.getMobile())) {

				String randomCode = RandomString.make(5);
				user.setEmailVerificationCode(randomCode);
				user.setEmailverified(false);
				String site_url = ConstantMessages.getSiteurl();
				String encodedPassword = passwordEncoder.encode(user.getPassword());
				user.setPassword(encodedPassword);

				if (signupService.saveUser(user)) {
					signupService.sendVerificationEmail(user, site_url);
					return new ResponseMessage("Registration Successful, Verify Your Email", 201);

				} else {
					return new ResponseMessage("Registration Unsuccessful!", 400);
				}

			} else {
				return new ResponseMessage("Duplicate Mobile Number!", 400);

			}
		} else
			return new ResponseMessage("Duplicate Email!", 400);
	}

	@GetMapping("/verify/{token}")
	public ResponseMessage verifyUser(@PathVariable("token") String token) {
		if (signupService.verify(token)) {
			return new ResponseMessage("verification successful", 201);
		} else {
			return new ResponseMessage("verification failed", 400);
		}
	}

	@GetMapping("/all-users")
	public ResponseEntity<Iterable<User>> getAllUsers() {
		return ResponseEntity.ok().body(adminService.getAllUsers());
	}

}
