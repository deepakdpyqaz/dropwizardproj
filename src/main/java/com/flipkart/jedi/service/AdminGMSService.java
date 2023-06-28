/**
 * 
 */
package com.flipkart.jedi.service;

import java.util.List;

import com.flipkart.jedi.DAO.GymGMSDAOImpl;
import com.flipkart.jedi.DAO.GymGMSDao;
import com.flipkart.jedi.bean.Gym;
import com.flipkart.jedi.DAO.GymOwnerGMSDao;
import com.flipkart.jedi.DAO.GymOwnerGMSDAOImpl;

/**
 *  SERVICE FOR ADMIN/FLIPKART TO VIEW ALL GYMS AND APPROVE PENDING GYMS
 */
public class AdminGMSService implements AdminGMSInterface{
	public List<Gym> showAllGymCentres() {
		GymGMSDao GMSDao = new GymGMSDAOImpl();
		List<Gym> gymnasiums = GMSDao.getAllGyms();
		return gymnasiums;
	}
	public boolean approveGym(int gymId) {
		GymGMSDao gymGMSDao = new GymGMSDAOImpl();
		return gymGMSDao.updateApproval(gymId);
	}
	public boolean approveGymOwner(String gym_owner_id) {
		GymOwnerGMSDao gymOwnerDao = new GymOwnerGMSDAOImpl();

		return false;
	}
}
