package com.flipkart.jedi.DAO;
import com.flipkart.jedi.bean.Role;

public interface RoleGMSDao {
	public String getRoleNameById(int role_id);
	public int getRoleIdByName(String role_name);
}
