package com.xorbank.services.impl;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.xorbank.ConstantMessages;
import com.xorbank.model.User;
import com.xorbank.repository.UserRepository;
import com.xorbank.services.SignUpService;

@Service
public class SignUpServiceImpl implements SignUpService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private JavaMailSender mailSender;

	public SignUpServiceImpl() {
	}

	public SignUpServiceImpl(UserRepository repo) {
		super();
		this.repo = repo;
	}

	public boolean saveUser(User user) {
		if(repo.save(user)!=null) {
			return true;
		}
		else
			return false;
	}

	public User getUser(Integer userId) {
		return repo.findByUserId(userId);
	}

	@Override
	public boolean checkEmail(String email) {
		if (repo.findByEmail(email) != null)
			return true;
		return false;
	}

	@Override
	public boolean checkMobileNumber(String mobile) {
		if (repo.findByMobile(mobile) != null)
			return true;
		return false;
	}

	@Override
	public void sendVerificationEmail(User user, String siteURL) throws UnsupportedEncodingException, MessagingException {
		String toAddress = user.getEmail();
		String subject = "Please verify your registration";
		String content = ConstantMessages.getVerificationemailcontent();

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom(ConstantMessages.getFromemailaddress(), ConstantMessages.getEmailsendername());
		helper.setTo(toAddress);
		helper.setSubject(subject);

		content = content.replace("[[name]]", user.getFirstname());
		String verifyURL = siteURL + "verify/"+ user.getEmailVerificationCode();

		content = content.replace("[[URL]]", verifyURL);

		helper.setText(content, true);

		mailSender.send(message);

	}

	public boolean verify(String verificationCode) {
	    User user = repo.findByEmailVerificationCode(verificationCode);
	     
	    if (user == null || user.isEmailVerified()) {
	        return false;
	    } else {
	        user.setEmailVerificationCode(null);
	        user.setEmailVerified(true);
	        repo.save(user);
	         
	        return true;
	    }
	     
	}

	

}
