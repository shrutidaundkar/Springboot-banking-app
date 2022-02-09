package com.xorbank.request;

public class TransactionRequest {

	private int userId;
	private int toAccount;
	private int amount;
    private String description;
    private int fromAccount;
    private int otp;
	
	public TransactionRequest() {
		
	}

	public TransactionRequest(int userId, int toAccount, int amount, String description, int fromAccount,
			int otp) {
		super();
		this.userId = userId;
		this.toAccount = toAccount;
		this.amount = amount;
		this.description = description;
		this.fromAccount = fromAccount;
		this.otp = otp;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getToAccount() {
		return toAccount;
	}

	public void setToAccount(int toAccount) {
		this.toAccount = toAccount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(int fromAccount) {
		this.fromAccount = fromAccount;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	@Override
	public String toString() {
		return "TransactionRequest [userId=" + userId + ", toAccount=" + toAccount + ", amount=" + amount
				+ ", description=" + description + ", fromAccount=" + fromAccount + ", otp=" + otp + "]";
	}	
	
	
}
