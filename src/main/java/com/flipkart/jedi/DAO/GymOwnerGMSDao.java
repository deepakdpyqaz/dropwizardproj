package com.flipkart.jedi.DAO;

import java.util.List;

import com.flipkart.jedi.bean.GymOwner;

public interface GymOwnerGMSDao {
	public void createGymOwner(GymOwner newGymOwner);
	public boolean isApproved(String gym_owner_id);
	public boolean approveGymOwner(String gym_owner_id);
	public List<GymOwner> getAllGymOwner();

}
