package com.xorbank.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="accountdata")
public class Account implements Serializable{				
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int accountId;
	@Column
	private String accountType;
	@Column
	private String dateCreated;
	@Column
	private String timeCreated;
	@Column
	private Double balance;

	private boolean accountStatus;
	
	@ManyToOne
	@JsonBackReference(value="user-account")
	private User user;
	
	@OneToOne(mappedBy="account")
	@JsonManagedReference
	private Loan loan;
	
	
	public Account() {
	}
	
	public Account(int accountId, String accountType, String dateCreated, String timeCreated, Double balance, User user,
			boolean accountStatus, Loan loan) {
		super();
		this.accountId = accountId;
		this.accountType = accountType;
		this.dateCreated = dateCreated;
		this.timeCreated = timeCreated;
		this.balance = balance;
		this.user = user;
		this.accountStatus = accountStatus;
		this.loan = loan;
	}

	public Loan getLoanAccount() {
		return loan;
	}
	public void setLoanAccount(Loan loanAccount) {
		this.loan = loanAccount;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public User getUser() {
		return user;
	}
	public Collection<?> setUser(User user) {
		this.user = user;
		return null;
	}
	
	public Boolean getAccountStatus() {
		return accountStatus;
	}
	
	public void setAccountStatus(boolean accountStatus) {
		this.accountStatus = accountStatus;
	}
	
	public String getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(String timeCreated) {
		this.timeCreated = timeCreated;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountType=" + accountType + ", dateCreated=" + dateCreated
				+ ", timeCreated=" + timeCreated + ", balance=" + balance + ", user=" + user + ", accountStatus="
				+ accountStatus + ", loan=" + loan + "]";
	}
	
}
