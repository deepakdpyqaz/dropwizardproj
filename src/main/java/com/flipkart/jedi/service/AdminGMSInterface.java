/**
 * 
 */
package com.flipkart.jedi.service;

import java.util.List;

import com.flipkart.jedi.bean.Gym;

/** Interface for AdminGMSService
 * 
 */
public interface AdminGMSInterface {
	public List<Gym> showAllGymCentres();
	public boolean approveGym(int gymNo);
	public boolean approveGymOwner(String gym_owner_id);
}
