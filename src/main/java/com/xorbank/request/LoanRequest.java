package com.xorbank.request;

public class LoanRequest {
	private int userId;	
	private int accountId;
	private double remainingAmount;
	private double loanAmount;
	private double interest;
	private String loanPurpose;
	private int tenureInMonths;
	private int monthlyEMI;
	
	public LoanRequest() {
	}
	
	public LoanRequest(int userId, int accountId, double remainingAmount, double loanAmount, double interest,
			String loanPurpose, int tenureInMonths, int monthlyEMI) {
		super();
		this.userId = userId;
		this.accountId = accountId;
		this.remainingAmount = remainingAmount;
		this.loanAmount = loanAmount;
		this.interest = interest;
		this.loanPurpose = loanPurpose;
		this.tenureInMonths = tenureInMonths;
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

	public double getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public String getLoanPurpose() {
		return loanPurpose;
	}

	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}

	public int getTenureInMonths() {
		return tenureInMonths;
	}

	public void setTenureInMonths(int tenureInMonths) {
		this.tenureInMonths = tenureInMonths;
	}

	public int getMonthlyEMI() {
		return monthlyEMI;
	}

	public void setMonthlyEMI(int monthlyEMI) {
		this.monthlyEMI = monthlyEMI;
	}

	@Override
	public String toString() {
		return "LoanRequest [userId=" + userId + ", accountId=" + accountId + ", remainingAmount=" + remainingAmount
				+ ", loanAmount=" + loanAmount + ", interest=" + interest + ", loanPurpose=" + loanPurpose
				+ ", tenureInMonths=" + tenureInMonths + ", monthlyEMI=" + monthlyEMI + "]";
	}
}
