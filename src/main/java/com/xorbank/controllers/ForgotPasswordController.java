package com.xorbank.controllers;

import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xorbank.exceptions.UserNotFoundException;
import com.xorbank.model.User;
import com.xorbank.request.ForgotPasswordRequest;
import com.xorbank.response.MessageResponse;
import com.xorbank.services.impl.ResetPasswordServiceImpl;
import net.bytebuddy.utility.RandomString;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@PropertySource("classpath:xorbankUrl.properties")
public class ForgotPasswordController {
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private ResetPasswordServiceImpl resetPasswordService;

	//@PostMapping("/forgot-password")
	@PostMapping("${FORGET_PASSWORD}")
	public MessageResponse processForgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) 
			throws UnsupportedEncodingException, MessagingException, UserNotFoundException {
		String email = forgotPasswordRequest.getEmail();
		String token = RandomString.make(10);

		User user=resetPasswordService.findByEmail(email);
		if(user == null) {
			return new MessageResponse("User does not exist with this Email",400);
		}else {
			resetPasswordService.updateResetPasswordToken(token, email);
			String resetPasswordLink = "http://localhost:4200/reset-password/" + token;
			System.out.println("Email : " + resetPasswordLink);
			sendEmail(email, resetPasswordLink);
			return new MessageResponse("Reset Password Link send on Registered Email",201);
		}

	}

	public void sendEmail(String recipientEmail, String resetPasswordLink)
			throws MessagingException, UnsupportedEncodingException {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("team.accord.dummy@xorbank.com", "xorbank Support");
		helper.setTo(recipientEmail);

		String subject = "Here's the link to reset your password";
		String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
				+ "<p>Find the below link to change your password:</p>" + resetPasswordLink + "<br>"
				+ "<p>Ignore this email if you do remember your password, " + "or you have not made the request.</p>";

		helper.setSubject(subject);
		helper.setText(content, true);
		mailSender.send(message);
	}

	//@PostMapping("/reset-password")
	@PostMapping("${RESET_PASSWORD}")
	public MessageResponse processResetPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) throws Exception {
		String token = forgotPasswordRequest.getToken();
		String password = forgotPasswordRequest.getNewPassword();

		if (resetPasswordService.getByResetPasswordToken(token) == null) {
			return new MessageResponse("Password Reset Unuccessful",400);

		} else {
			resetPasswordService.updatePassword(resetPasswordService.getByResetPasswordToken(token), password);
			return new MessageResponse("Password Reset Successfully", 201);
		}

	}

}