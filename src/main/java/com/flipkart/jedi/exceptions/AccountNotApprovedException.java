package com.flipkart.jedi.exceptions;

public class AccountNotApprovedException extends Exception{
	private static final long serialVersionUID = 1L;
	private String ownerId;
	public AccountNotApprovedException(String owner) {
		this.ownerId=owner;
	}
	@Override
	public String getMessage() {
		return "Account for '"+this.ownerId+"' is not approved yet. Please wait for approval";
	}
}
