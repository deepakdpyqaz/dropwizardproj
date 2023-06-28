package com.flipkart.jedi.service;

import com.flipkart.jedi.DAO.*;

import com.flipkart.jedi.bean.*;
import com.flipkart.jedi.exceptions.*;

public class UserGMSService implements UserGMSInterface {
	public User login(String username, String password) throws InvalidLoginCredentialsException,AccountNotApprovedException{
		UserGMSDao userDao = new UserGMSDAOImpl();
		RoleGMSDao roleDao = new RoleGMSDAOImpl();
		GymOwnerGMSDao ownerDao = new GymOwnerGMSDAOImpl();
		User user = userDao.getUserByUsername(username);
		if(user!=null) {
			if(user.getPassword().equals(password)) {
				if(roleDao.getRoleNameById(user.getRoleId()).equals("Gym Owner") && !ownerDao.isApproved(username)) {
					throw new AccountNotApprovedException(username);
				}
				return user;
			}
		}
		throw new InvalidLoginCredentialsException();
	}
	public void userRegistration(User newUser){
		UserGMSDao userDao = new UserGMSDAOImpl();
		userDao.createUser(newUser);
		return ;
	}
	public boolean logout() {
		return true;
	}
	public boolean updatePassword(String username, String oldPassword, String newPassword) {
		return true;
	}
}
