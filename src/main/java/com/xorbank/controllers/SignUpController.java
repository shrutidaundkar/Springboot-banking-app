package com.xorbank.controllers;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xorbank.ConstantMessages;
import com.xorbank.model.User;
import com.xorbank.request.EmailVerificationRequest;
import com.xorbank.response.MessageResponse;
import com.xorbank.services.impl.AdminServiceImpl;
import com.xorbank.services.impl.SignUpServiceImpl;
import net.bytebuddy.utility.RandomString;

@RestController
//@RequestMapping("/server")
@PropertySource("classpath:xorbankUrl.properties")
@RequestMapping("${server.context-path}")
@CrossOrigin(origins = "http://localhost:4200")
public class SignUpController {

	@Autowired
	private SignUpServiceImpl signupService;

	@Autowired
	private AdminServiceImpl adminService;

	@Autowired
	private PasswordEncoder passwordEncoder;

//	@PostMapping("/save")
	@PostMapping("${USER_SAVE}")
	@Transactional
	public MessageResponse signUpUser(@RequestBody User user) throws UnsupportedEncodingException, MessagingException {

		if (!signupService.checkEmail(user.getEmail())) {
			if (!signupService.checkMobileNumber(user.getMobile())) {

				String randomCode = RandomString.make(5);
				user.setEmailVerificationCode(randomCode);
				user.setEmailVerified(false);
				String site_url = ConstantMessages.getSiteurl();
				String encodedPassword = passwordEncoder.encode(user.getPassword());
				user.setPassword(encodedPassword);

				if (signupService.saveUser(user)) {
					signupService.sendVerificationEmail(user, site_url);
					return new MessageResponse("Registration Successful, Verify Your Email", 201);

				} else {
					return new MessageResponse("Registration Unsuccessful!", 400);
				}

			} else {
				return new MessageResponse("Duplicate Mobile Number!", 400);

			}
		} else                                                                                         
			return new MessageResponse("Duplicate Email!", 400);
	}

//	@PostMapping("/verify")
	@PostMapping("${USER_VERIFY}")
	public MessageResponse verifyUser(@RequestBody EmailVerificationRequest emailVerificationRequest) {
		if (signupService.verify(emailVerificationRequest.getEmailVerificationCode())) {
			return new MessageResponse("Verification Successful", 201);
		} else {
			return new MessageResponse("Verification Failed", 400);
		}
	}
	
//	@GetMapping("/all-users")
	@GetMapping("${GET_ALL_USERS}")
	public ResponseEntity<Iterable<User>> getAllUsers() {
		return ResponseEntity.ok().body(adminService.getAllUsers());
	}

}
