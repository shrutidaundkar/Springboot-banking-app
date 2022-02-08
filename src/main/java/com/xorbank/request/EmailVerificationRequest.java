package com.xorbank.request;

public class EmailVerificationRequest {

	private String email;
	private String emailVerificationCode;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailVerificationCode() {
		return emailVerificationCode;
	}
	public void setEmailVerificationCode(String emailVerificationCode) {
		this.emailVerificationCode = emailVerificationCode;
	}
	public EmailVerificationRequest() {}
	public EmailVerificationRequest(String email, String emailVerificationCode) {
		super();
		this.email = email;
		this.emailVerificationCode = emailVerificationCode;
	}
	
	
}
