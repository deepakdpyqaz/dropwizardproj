package com.flipkart.jedi.exceptions;

public class InvalidLoginCredentialsException extends Exception{
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Invalid login credentials";
	}
}
