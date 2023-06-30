package com.flipkart.jedi.service;

import java.util.List;

import com.flipkart.jedi.bean.Gym;
import com.flipkart.jedi.bean.GymOwner;
import com.flipkart.jedi.exceptions.GymAlreadyExistsException;
import com.flipkart.jedi.exceptions.GymAlreadyRegisteredException;
import com.flipkart.jedi.exceptions.GymNotRemovedException;
import com.flipkart.jedi.exceptions.GymOwnerAlreadyRegisteredException;
/*Interface for GymOwner Service */

public interface GymOwnerGMSInterface {
	public boolean gymOwnerRegister(GymOwner newGymOwner) throws GymAlreadyRegisteredException, GymOwnerAlreadyRegisteredException;
	public Gym addGym(Gym gym) throws GymAlreadyExistsException;
	public boolean removeGym(int gymId) throws GymNotRemovedException;
	public List<Gym> viewGym(String username);
	public int getGymId(Gym gym);
}
