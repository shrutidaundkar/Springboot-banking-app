package com.xorbank.request;

public class LoanAccountRequest {
	private int userId;	
	private int accountId;
	private Double balance;
	private String loanType;
	private int tenure;
	private int monthlyEMI;
	
	public LoanAccountRequest() {
	}

	public LoanAccountRequest(int userId, int accountId, Double balance, String loanType, int tenure, int monthlyEMI) {
		super();
		this.userId = userId;
		this.accountId = accountId;
		this.balance = balance;
		this.loanType = loanType;
		this.tenure = tenure;
		this.monthlyEMI = monthlyEMI;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public int getMonthlyEMI() {
		return monthlyEMI;
	}

	public void setMonthlyEMI(int monthlyEMI) {
		this.monthlyEMI = monthlyEMI;
	}

	@Override
	public String toString() {
		return "LoanAccountCred [userId=" + userId + ", accountId=" + accountId + ", balance=" + balance + ", loanType="
				+ loanType + ", tenure=" + tenure + ", monthlyEMI=" + monthlyEMI + "]";
	}
	
	
	
	
	
}
