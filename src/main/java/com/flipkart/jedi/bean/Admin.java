/**
 * 
 */
package com.flipkart.jedi.bean;

/**
 * 
 */
public class Admin extends User{
	private String adminId;

	public Admin(String username, String password, int roleId, String name, String adminId) {
		super(username, password, roleId, name);
		this.adminId = adminId;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	
	
}
