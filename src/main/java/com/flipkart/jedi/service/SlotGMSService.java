/**
 * 
 */
package com.flipkart.jedi.service;

import java.util.ArrayList;
import java.util.List;

import com.flipkart.jedi.bean.Slot;
import com.flipkart.jedi.DAO.SlotGMSDao;
import com.flipkart.jedi.DAO.SlotGMSDAOImpl;
import java.time.LocalDate;
import java.sql.Date;
import com.flipkart.jedi.constants.FlipFitConstants;
import com.flipkart.jedi.exceptions.NoSlotsFoundException;
import com.flipkart.jedi.exceptions.SlotNotCreatedException;

/**
 * 
 */
public class SlotGMSService implements SlotGMSInterface {

	@Override
	public List<Slot> getSlotsOfGym(int gym_id) throws NoSlotsFoundException {
		// TODO Auto-generated method stub
		List<Slot> slots = new ArrayList<Slot>();
		SlotGMSDao slotDao = new SlotGMSDAOImpl();
		return slotDao.getSlotsOfGym(gym_id);
	}
	
	public boolean isSlotAvailable(int slot_id) throws NoSlotsFoundException {
		SlotGMSDao slotDAO = new SlotGMSDAOImpl();
		return slotDAO.isSlotAvailable(slot_id);
	}

	@Override
	public boolean createSlots(List<Slot> slots) throws SlotNotCreatedException, NoSlotsFoundException {
		List<Slot> advanced_slots = new ArrayList<Slot>();
		for(Slot st:slots) {
			for(int i=0;i<FlipFitConstants.ADVANCED_SLOT_BOOKING;i++) {
				Slot new_slot = new Slot(st.getGymId(),st.getSlot_start_time(),st.getSlot_end_time(),st.getAvailSeats(),st.getGymId(),st.getDay());
				LocalDate dt = LocalDate.now().plusDays(i);
				new_slot.setDay(Date.valueOf(dt));
				advanced_slots.add(new_slot);
			}
		}
		SlotGMSDao slotDao = new SlotGMSDAOImpl();
		return slotDao.createSlot(advanced_slots);
	}

	@Override
	public Slot getSlot(int slotId){
		Slot slot = new Slot();
		SlotGMSDao slotDao = new SlotGMSDAOImpl();
		return slotDao.getSlot(slotId);
	}

}
