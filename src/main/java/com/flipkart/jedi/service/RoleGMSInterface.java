/**
 * 
 */
package com.flipkart.jedi.service;

import com.flipkart.jedi.exceptions.RoleNotFoundException;


public interface RoleGMSInterface {
	public String getRoleNameById(int role_id) throws RoleNotFoundException;
	public int getRoleIdByName(String role_name) throws RoleNotFoundException;
}
