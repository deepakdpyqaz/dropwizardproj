/**
 * 
 */
package com.flipkart.jedi.service;

import java.util.List;

import com.flipkart.jedi.bean.Gym;
import com.flipkart.jedi.exceptions.GymAlreadyApprovedException;
import com.flipkart.jedi.exceptions.GymOwnerAlreadyApprovedException;
import com.flipkart.jedi.exceptions.InvalidLoginCredentialsException;

/** Interface for AdminGMSService **/
public interface AdminGMSInterface {
	public List<Gym> showAllGymCentres() throws InvalidLoginCredentialsException;
	public boolean approveGym(int gymNo) throws GymAlreadyApprovedException;
	public boolean approveGymOwner(String gym_owner_id) throws GymOwnerAlreadyApprovedException;
}
