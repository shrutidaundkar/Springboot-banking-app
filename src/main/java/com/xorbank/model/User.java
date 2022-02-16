package com.xorbank.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "userdata")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	@Column
	private String firstname;
	@Column
	private String lastname;
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private String mobile;
	@Column
	private String dateofbirth;
	@Column
	private String age;
	@Column
	private String gender;
	@Column
	private String password;
	@Column
	private String resetPasswordToken;
	@Column
	private String emailVerificationCode;
	@Column
	private boolean emailVerified;
	@Column
	private int otp;

	@OneToMany(mappedBy = "user")
	@JsonManagedReference(value="user-account")
	private List<Account> accounts;
	
	@OneToOne
    @JoinColumn(name = "documentId", referencedColumnName = "documentId")
	@JsonManagedReference(value="user-document")
	private Document document;
	
	@OneToMany(mappedBy = "user")
	@JsonManagedReference(value="user-loan")
	private List<Loan> loans;
	

	public User() {
	}
	
	public User(int userId, String firstname, String lastname, String email, String mobile, String dateofbirth,
			String age, String gender, String password, String resetPasswordToken, String emailVerificationCode,
			boolean emailVerified, int otp, List<Account> accounts, Document document) {
		super();
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.mobile = mobile;
		this.dateofbirth = dateofbirth;
		this.age = age;
		this.gender = gender;
		this.password = password;
		this.resetPasswordToken = resetPasswordToken;
		this.emailVerificationCode = emailVerificationCode;
		this.emailVerified = emailVerified;
		this.otp = otp;
		this.accounts = accounts;
		this.document = document;
	}




	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserid(int userId) {
		this.userId = userId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	public String getEmailVerificationCode() {
		return emailVerificationCode;
	}

	public void setEmailVerificationCode(String emailVerificationCode) {
		this.emailVerificationCode = emailVerificationCode;
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}

	public void setEmailverified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}


	public int getOtp() {
		return otp;
	}


	public void setOtp(int otp) {
		this.otp = otp;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}


	public List<Loan> getLoans() {
		return loans;
	}


	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", mobile=" + mobile + ", dateofbirth=" + dateofbirth + ", age=" + age + ", gender=" + gender
				+ ", password=" + password + ", resetPasswordToken=" + resetPasswordToken + ", emailVerificationCode="
				+ emailVerificationCode + ", emailVerified=" + emailVerified + ", otp=" + otp + ", accounts=" + accounts
				+ ", document=" + document + ", loans=" + loans + "]";
	}
}
