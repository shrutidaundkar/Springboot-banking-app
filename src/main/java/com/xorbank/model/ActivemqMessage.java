package com.xorbank.model;

import java.io.Serializable;

public class ActivemqMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	private String message;

	public ActivemqMessage() {}
	
	public ActivemqMessage(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ActivemqMessage [message=" + message + "]";
	}
}
