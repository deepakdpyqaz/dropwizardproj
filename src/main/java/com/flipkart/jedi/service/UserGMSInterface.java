package com.flipkart.jedi.service;


import com.flipkart.jedi.bean.*;
import com.flipkart.jedi.exceptions.AccountNotApprovedException;
import com.flipkart.jedi.exceptions.InvalidLoginCredentialsException;

public interface UserGMSInterface {
	public User login(String username, String password) throws InvalidLoginCredentialsException, AccountNotApprovedException;
	public boolean logout();
	public boolean updatePassword(String username, String oldPassword, String newPassword);
	public void userRegistration(User newUser);
}
