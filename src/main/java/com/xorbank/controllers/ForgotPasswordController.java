package com.xorbank.controllers;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xorbank.Utility;
import com.xorbank.exceptions.UserNotFoundException;
import com.xorbank.models.ForgotCred;
import com.xorbank.models.User;
import com.xorbank.services.impl.ResetPasswordService;

import net.bytebuddy.utility.RandomString;

@RestController
public class ForgotPasswordController {
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private ResetPasswordService resetPasswordService;

	@PostMapping("/forgot_password")
	public void processForgotPassword(@RequestBody ForgotCred fCred)
			throws UnsupportedEncodingException, MessagingException, UserNotFoundException {
		String email = fCred.getEmail();
		String token = RandomString.make(10);

			resetPasswordService.updateResetPasswordToken(token, email);
			String resetPasswordToken = token;
			sendEmail(email, resetPasswordToken);

	}

	public void sendEmail(String recipientEmail, String token) throws MessagingException, UnsupportedEncodingException {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("team.accord.dummy@xorbank.com", "xorbank Support");
		helper.setTo(recipientEmail);

		String subject = "Here's the token to reset your password";
		String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
				+ "<p>Find the token below to change your password:</p>"  + token
				 + "<br>" + "<p>Ignore this email if you do remember your password, "
				+ "or you have not made the request.</p>";

		helper.setSubject(subject);
		helper.setText(content, true);
		mailSender.send(message);
	}

	@PostMapping("/reset_password")
	public String processResetPassword(@RequestBody ForgotCred fCred) {
		String token = fCred.getToken();
		String password = fCred.getNewPassword();

		User user = resetPasswordService.getByResetPasswordToken(token);

		if (user == null) {
			return "No User Found";
		} else {
			resetPasswordService.updatePassword(user, password);
		}
		return "password Reset Successful";

	}

}