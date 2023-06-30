/**
 * 
 */
package com.flipkart.jedi.service;

import java.util.List;

import com.flipkart.jedi.bean.*;
import com.flipkart.jedi.exceptions.NoSlotsFoundException;
import com.flipkart.jedi.exceptions.SlotNotCreatedException;

/**
 * 
 */
public interface SlotGMSInterface {
	public List<Slot> getSlotsOfGym(int gym_id) throws NoSlotsFoundException;
	public boolean isSlotAvailable(int slot_id) throws NoSlotsFoundException;
	public boolean createSlots(List<Slot> slots) throws SlotNotCreatedException, NoSlotsFoundException;
}
