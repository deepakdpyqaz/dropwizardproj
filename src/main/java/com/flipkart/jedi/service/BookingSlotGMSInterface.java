/**
 * 
 */
package com.flipkart.jedi.service;

import com.flipkart.jedi.bean.Booking;
import com.flipkart.jedi.exceptions.NoClashingSlotException;
import com.flipkart.jedi.exceptions.NoSlotsFoundException;
import com.flipkart.jedi.exceptions.SlotAlreadyBookedException;
import com.flipkart.jedi.exceptions.SlotNotCancelledException;

/**
 *  Interface for BookingSlotService 
 */
public interface BookingSlotGMSInterface {
	public boolean bookSlot(String username,int slot_id) throws SlotAlreadyBookedException, NoClashingSlotException, NoSlotsFoundException;
	public boolean cancelSlot(int booking_id) throws SlotNotCancelledException;
	public Booking getClashingBooking(String username, int slot_id) throws NoClashingSlotException;
}
