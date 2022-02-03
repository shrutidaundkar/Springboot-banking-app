package com.xorbank.controllers;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.xorbank.exceptions.UserNotFoundException;
import com.xorbank.models.ForgotCred;
import com.xorbank.models.User;
import com.xorbank.services.impl.ResetPasswordService;

import net.bytebuddy.utility.RandomString;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ForgotPasswordController {
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private ResetPasswordService resetPasswordService;

	@PostMapping("/forgot-password")
	public void processForgotPassword(@RequestBody ForgotCred fCred)
			throws UnsupportedEncodingException, MessagingException, UserNotFoundException {
		String email = fCred.getEmail();
		String token = RandomString.make(10);
		

			resetPasswordService.updateResetPasswordToken(token, email);
			String resetPasswordLink = "http://localhost:4200/reset-password/" + token;
		    System.out.println("Email : "+resetPasswordLink);
			sendEmail(email, resetPasswordLink);
			
	}

	public void sendEmail(String recipientEmail, String resetPasswordLink) throws MessagingException, UnsupportedEncodingException {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("team.accord.dummy@xorbank.com", "xorbank Support");
		helper.setTo(recipientEmail);

		String subject = "Here's the link to reset your password";
		String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
				+ "<p>Find the below link to change your password:</p>"  + resetPasswordLink	+
				 "<br>" + "<p>Ignore this email if you do remember your password, "
				+ "or you have not made the request.</p>";

		helper.setSubject(subject);
		helper.setText(content, true);
		mailSender.send(message);
	}

	@PostMapping("/reset-password")
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