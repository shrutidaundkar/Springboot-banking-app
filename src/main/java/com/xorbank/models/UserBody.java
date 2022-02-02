package com.xorbank.models;

public class UserBody {

	private int userid;
	private int accountId;
	
	public UserBody() {}
	public UserBody(int userId,int accountId) {
		this.userid =userId;
		this.accountId =accountId;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
}
