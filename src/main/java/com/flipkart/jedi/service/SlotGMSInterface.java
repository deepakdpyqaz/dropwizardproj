/**
 * 
 */
package com.flipkart.jedi.service;

import java.util.List;

import com.flipkart.jedi.bean.*;

/**
 * 
 */
public interface SlotGMSInterface {
	public List<Slot> getSlotsOfGym(int gym_id);
	public boolean isSlotAvailable(int slot_id);
	public boolean createSlots(List<Slot> slots);
}
