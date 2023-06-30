package com.flipkart.jedi.service;

import java.util.List;

import com.flipkart.jedi.DAO.*;
import com.flipkart.jedi.bean.*;
import com.flipkart.jedi.exceptions.GymAlreadyExistsException;
import com.flipkart.jedi.exceptions.GymNotRemovedException;
import com.flipkart.jedi.exceptions.GymOwnerAlreadyRegisteredException;

public class GymOwnerGMSService implements GymOwnerGMSInterface {
/*
 * Implements functions to allow -
 * 1. remove gyms existing on the app's database
 * 2. view all gyms previously added by the gym owner
 * 3. registration of a gym owner
 * 4. add a new gym
 */
	@Override
	public boolean removeGym(int gymId) throws GymNotRemovedException {
		GymGMSDao gymDao = new GymGMSDAOImpl();
		String res = gymDao.removeGym(gymId);
		if(res.equals("Gym removed"))
			return true;
		return false;
	}
	
	public boolean gymOwnerRegister(GymOwner newGymOwner) throws GymOwnerAlreadyRegisteredException {
		GymOwnerGMSDao gymOwnerDao = new GymOwnerGMSDAOImpl();
		gymOwnerDao.createGymOwner(newGymOwner);
		return true;
	}
	@Override
	public Gym addGym(Gym gym) throws GymAlreadyExistsException {
		GymGMSDao gymDao = new GymGMSDAOImpl();
		return gymDao.createGym(gym);
	}

	@Override
	public List<Gym> viewGym(String username){
		GymGMSDao gymDao = new GymGMSDAOImpl();
		List<Gym> gymnasiums = gymDao.getAllMyGyms(username);
		return gymnasiums;
	}

	public int getGymId(Gym gym){
		return gym.getGymId();
	}
}
