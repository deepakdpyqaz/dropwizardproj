package com.flipkart.jedi.service;
import com.flipkart.jedi.DAO.RoleGMSDao;
import com.flipkart.jedi.DAO.RoleGMSDAOImpl;
public class RoleGMSService implements RoleGMSInterface {

	@Override
	public String getRoleNameById(int role_id) {
		RoleGMSDao roleDao = new RoleGMSDAOImpl();
		return roleDao.getRoleNameById(role_id);
	}

	@Override
	public int getRoleIdByName(String role_name) {
		RoleGMSDao roleDao = new RoleGMSDAOImpl();
		return roleDao.getRoleIdByName(role_name);
	}

}
