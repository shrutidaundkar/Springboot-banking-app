package com.xorbank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="LoanAccountData")
public class LoanAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int loanId;
	
	
	@OneToOne
	@JsonBackReference
	private Account account;
	
	@Column
	private Double balance;
	
	@Column
	private String loanType;
	
	@Column
	private int tenure;
	
	@Column
	private int monthlyEMI;
	
	@OneToOne
	private User user1;

	public LoanAccount() {
	
	}

	public LoanAccount(Account account, Double balance, String loanType, int tenure, int monthlyEMI, User user1) {
		super();
		this.account = account;
		this.balance = balance;
		this.loanType = loanType;
		this.tenure = tenure;
		this.monthlyEMI = monthlyEMI;
		this.user1 = user1;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
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

	public User getUser() {
		return user1;
	}

	public void setUser(User user) {
		this.user1 = user1;
	}

	@Override
	public String toString() {
		return "LoanAccount [account=" + account + ", balance=" + balance + ", loanType=" + loanType + ", tenure="
				+ tenure + ", monthlyEMI=" + monthlyEMI + ", user=" + user1 + "]";
	}
	
	
	
	
	
	

}
