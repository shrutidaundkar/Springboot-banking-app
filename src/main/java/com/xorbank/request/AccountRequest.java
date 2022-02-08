package com.xorbank.request;

public class AccountRequest {

	private int userId;	
	private String accountType;
	private Double balance;
	
	public AccountRequest() {}
	public AccountRequest(String accountType, int userId, Double balance) {
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
