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
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "userdata")
public class User {

	@Id
//	@TableGenerator(name = "User_Gen", table = "User_ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "User_Gen", initialValue = 10000, allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "User_Gen")
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
	@JsonManagedReference
	private List<Account> accounts = new ArrayList<Account>();
	
	@OneToOne
    @JoinColumn(name = "document_id", referencedColumnName = "id")
	private Document document;
	
	@OneToOne(mappedBy="user1")
	
	private LoanAccount loanAccount;
	

	public User() {
	}

	
	public User(int userId, String firstname, String lastname, String email, String mobile, String dateofbirth,
			String age, String gender, String password, String resetPasswordToken, String emailVerificationCode,
<<<<<<< HEAD
			boolean emailVerified, String otp, List<Account> accounts, Document document,LoanAccount loanAccount) {
=======
			boolean emailVerified, int otp, List<Account> accounts, Document document) {
>>>>>>> 4252903c313da781f1c8aca51ec7e38c24a2227a
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
		this.loanAccount=loanAccount;
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


	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", mobile=" + mobile + ", dateofbirth=" + dateofbirth + ", age=" + age + ", gender=" + gender
				+ ", password=" + password + ", resetPasswordToken=" + resetPasswordToken + ", emailVerificationCode="
				+ emailVerificationCode + ", emailVerified=" + emailVerified + ", otp=" + otp + ", accounts=" + accounts
			 + "]";
	}
	public LoanAccount getLoanAccount() {
		return loanAccount;
	}

	public void setLoanAccount(LoanAccount loanAccount) {
		this.loanAccount = loanAccount;
	}


}
