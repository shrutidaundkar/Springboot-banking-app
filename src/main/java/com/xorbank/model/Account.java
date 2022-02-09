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
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="accountdata")
public class Account implements Serializable{				
	
	private static final long serialVersionUID = 1L;
	
	@Id
//	@TableGenerator(name = "Account_Gen", table = "Account_ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "Account_Gen", initialValue = 10000, allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Account_Gen")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int accountId;
	
	@Column
	private String accountType;
	
	@Column
	private String dateCreated;
	
	@Column
	private Double balance;
	
	@ManyToOne
	@JsonBackReference
	private User user;

	private boolean accountStatus;
	
	@OneToOne(mappedBy="account")
	@JsonManagedReference
	private LoanAccount loanAccount;
	
	
	
	
	public Account() {
		this.accountStatus= true;
	}
	public Account(int accountId, String accountType, String dateCreated, Double balance, User user,LoanAccount loanAccount) {
		super();
		this.accountId = accountId;
		this.accountType = accountType;
		this.dateCreated = dateCreated;
		this.balance = balance;
		this.user = user;
		this.accountStatus= true;
		this.loanAccount=loanAccount;
	}
	public Account(String accountType, String dateCreated, Double balance, User user,LoanAccount loanAccount) {
		super();
		this.accountType = accountType;
		this.dateCreated = dateCreated;
		this.balance = balance;
		this.user = user;
		this.accountStatus= true;
		this.loanAccount=loanAccount;
	}
	public LoanAccount getLoanAccount() {
		return loanAccount;
	}
	public void setLoanAccount(LoanAccount loanAccount) {
		this.loanAccount = loanAccount;
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
	public boolean setAccountStatus(Boolean accountStatus) {
		return this.accountStatus = accountStatus;
	}
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountType=" + accountType + ", dateCreated=" + dateCreated
				+ ", balance=" + balance + ", user=" + user + ", accountStatus=" + accountStatus + "]";
	}
	
	
}
