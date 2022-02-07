package com.xorbank.models;

public class EmailVerificationCred {

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
	public EmailVerificationCred() {}
	public EmailVerificationCred(String email, String emailVerificationCode) {
		super();
		this.email = email;
		this.emailVerificationCode = emailVerificationCode;
	}
	
	
}
