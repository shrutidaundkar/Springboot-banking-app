package com.xorbank;

public class ConstantMessages {

	private final static String emailNotRegisteredMessage = "User with this email is not registered, Please SignUp.";
	private final static String passwordIncorrectMessage = "Password Incorrect, Please try again..!";
	private final static String loginSuccessMessage = "Login Successful";

	private final static String siteURL = "http://localhost:4200/";

	private final static String fromEmailAddress = "team.accord.dummy@gmail.com";
	private final static String emailSenderName = "Xorbank Support";

	private final static String verificationEmailContent = "Dear [[name]],<br>"
			+ "Please click the link below to verify your registration:<br>"
			+ "<h3><a href=\"[[URL]]\">VERIFY</a></h3>" + "Thank you,<br>" + "Xorbank Support";

	public static String getEmailNotRegisteredMessage() {
		return emailNotRegisteredMessage;
	}

	public static String getPasswordincorrectmessage() {
		return passwordIncorrectMessage;
	}

	public static String getLoginsuccessmessage() {
		return loginSuccessMessage;
	}

	public static String getSiteurl() {
		return siteURL;
	}

	public static String getFromemailaddress() {
		return fromEmailAddress;
	}

	public static String getEmailsendername() {
		return emailSenderName;
	}

	public static String getVerificationemailcontent() {
		return verificationEmailContent;
	}
	

}
