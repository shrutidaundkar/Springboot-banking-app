package com.xorbank.request;

public class ForgotPasswordRequest {

	private String email;
	private String token;
	private String newPassword;
	private String confirmPassword;
	
	
	public ForgotPasswordRequest() {}
	
	public ForgotPasswordRequest(String email, String token, String newPassword, String confirmPassword) {
		super();
		this.email = email;
		this.token = token;
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
}
