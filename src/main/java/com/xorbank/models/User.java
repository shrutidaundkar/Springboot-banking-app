package com.xorbank.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "userdata")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userid;
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

	private boolean emailVerified;

	@OneToMany(mappedBy = "user")
	@JsonManagedReference
	private List<Account> accounts = new ArrayList<Account>();

	public User() {
	}

	public User(int userid, String firstname, String lastname, String email, String mobile, String dateofbirth,
			String age, String gender, String password, String resetPasswordToken, List<Account> accounts, String emailVerificationCode) {
		super();
		this.userid = userid;
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
		this.accounts = accounts;
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

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
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

	@Override
	public String toString() {
		return "User [userid=" + userid + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", mobile=" + mobile + ", dateofbirth=" + dateofbirth + ", age=" + age + ", gender=" + gender
				+ ", password=" + password + ", resetPasswordToken=" + resetPasswordToken + ", emailVerificationCode="
				+ emailVerificationCode + ", emailVerified=" + emailVerified + ", accounts=" + accounts + "]";
	}

	

}
