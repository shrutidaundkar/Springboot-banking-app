package com.xorbank.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="transactiondata")
public class Transaction implements Serializable, Comparable<Transaction>{				
	
	private static final long serialVersionUID = 1L;
	
	@Id
//	@TableGenerator(name = "Transaction_Gen", table = "Transaction_ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "Transaction_Gen", initialValue = 10000, allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "Transaction_Gen")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int transactionId;
	
	@Column
	private int fromAccount;
	
	@Column
	private int toAccount;
	
	@Column
	private Double amount;
	
	@Column
	private String transactionStatus;
	
	@Column
	private String transactionDate;

	@Column
	private String description;
	
	public Transaction() {
		super();
	}
	
	public Transaction(int transactionId, int fromA, int toA, Double amount, String transactionStatus,
			String transactionDate, String description) {
		super();
		this.transactionId = transactionId;
		this.fromAccount = fromA;
		this.toAccount = toA;
		this.amount = amount;
		this.transactionStatus = transactionStatus;
		this.transactionDate = transactionDate;
		this.description = description;
	}

	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	
	public int getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(int fromAccount) {
		this.fromAccount = fromAccount;
	}

	public int getToAccount() {
		return toAccount;
	}

	public void setToAccount(int toAccount) {
		this.toAccount = toAccount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", fromAccount=" + fromAccount + ", toAccount="
				+ toAccount + ", amount=" + amount + ", transactionStatus=" + transactionStatus + ", transactionDate="
				+ transactionDate + ", description=" + description + "]";
	}

	@Override
	public int compareTo(Transaction o) {
		
		return this.transactionDate.compareTo(o.transactionDate);
	}

	
}