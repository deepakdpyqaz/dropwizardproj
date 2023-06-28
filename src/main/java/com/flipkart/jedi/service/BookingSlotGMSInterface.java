/**
 * 
 */
package com.flipkart.jedi.service;

import com.flipkart.jedi.bean.Booking;

/**
 *  Interface for BookingSlotService 
 */
public interface BookingSlotGMSInterface {
	public boolean bookSlot(String username,int slot_id);
	public boolean cancelSlot(int booking_id);
	public Booking getClashingBooking(String username, int slot_id);
}
