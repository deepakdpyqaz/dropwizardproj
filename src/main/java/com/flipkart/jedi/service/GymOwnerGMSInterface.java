package com.flipkart.jedi.service;

import java.util.List;

import com.flipkart.jedi.bean.Gym;
import com.flipkart.jedi.bean.GymOwner;
/*Interface for GymOwner Service */

public interface GymOwnerGMSInterface {
	public boolean gymOwnerRegister(GymOwner newGymOwner);
	public Gym addGym(Gym gym);
	public boolean removeGym(int gymId);
	public List<Gym> viewGym(String username);
}
