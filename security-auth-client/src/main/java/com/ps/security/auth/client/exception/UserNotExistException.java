package com.ps.security.auth.client.exception;

public class UserNotExistException extends RuntimeException {
	private static final long serialVersionUID = 491506906071750288L;
	private String userId;
	public UserNotExistException(String userId) {
		super("User Not Exists.");
		this.userId = userId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	

}
