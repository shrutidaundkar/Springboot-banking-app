package com.xorbank.models;

public class AccountCred {

	private String accountType;
	private int userId;	
	private Double balance;
	public AccountCred() {}
	public AccountCred(String accountType, int userId, Double balance) {
		super();
		this.accountType = accountType;
		this.userId = userId;
		this.balance = balance;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "AccountCred [accountType=" + accountType + ", userId=" + userId + ", balance=" + balance + "]";
	}
}
