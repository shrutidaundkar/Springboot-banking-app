package com.xorbank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="Loanadata")
public class Loan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int loanId;
	@Column
	private Double remainingAmount;
	@Column
	private Double loanAmount;
	@Column
	private String loanPurpose;
	@Column
	private Double interest;
	@Column
	private int tenureInMonths;
	@Column
	private int monthlyEMI;
	
	@ManyToOne
	@JsonBackReference(value="user-loan")
	private User user;

	@OneToOne
	@JsonBackReference
	private Account account;

	public Loan() {
	
	}

	public Loan(int loanId, Double remainingAmount, Double loanAmount, String loanPurpose, Double interest,
			int tenureInMonths, int monthlyEMI, User user, Account account) {
		super();
		this.loanId = loanId;
		this.remainingAmount = remainingAmount;
		this.loanAmount = loanAmount;
		this.loanPurpose = loanPurpose;
		this.interest = interest;
		this.tenureInMonths = tenureInMonths;
		this.monthlyEMI = monthlyEMI;
		this.user = user;
		this.account = account;
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public Double getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(Double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getLoanPurpose() {
		return loanPurpose;
	}

	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}

	public Double getInterest() {
		return interest;
	}

	public void setInterest(Double interest) {
		this.interest = interest;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Loan [loanId=" + loanId + ", remainingAmount=" + remainingAmount + ", loanAmount=" + loanAmount
				+ ", loanPurpose=" + loanPurpose + ", interest=" + interest + ", tenureInMonths=" + tenureInMonths
				+ ", monthlyEMI=" + monthlyEMI + ", user=" + user + ", account=" + account + "]";
	}

}
