package com.flipkart.jedi.service;

import com.flipkart.jedi.DAO.*;
import com.flipkart.jedi.bean.Booking;
/* Booking slot service implements 3 functions - 
 * 1.allow a user to book slot, 
 * 2.get clashing bookings incase 2 bookings in the same timeslot
 * 3.remove a slot booking
 * as well*/
public class BookingSlotGMSService implements BookingSlotGMSInterface {
	public boolean bookSlot(String username, int slot_id) {
		SlotGMSDao slotDAO = new SlotGMSDAOImpl();
		BookingGMSDao bookingDAO = new BookingGMSDAOImpl();
		SlotGMSInterface slotSer = new SlotGMSService();
		if (slotSer.isSlotAvailable(slot_id)) {
			Booking previous_booking = getClashingBooking(username, slot_id);
			if (previous_booking != null) {
				return false;
			}
			boolean isBooked = bookingDAO.bookSlot(username, slot_id);
			if (isBooked) {
				return slotDAO.decrementSeats(slot_id);
			} else {
				return false;
			}
		}
		return false;
	}

	public Booking getClashingBooking(String username, int slot_id) {
		BookingGMSDao bookingDAO = new BookingGMSDAOImpl();
		return bookingDAO.getClashingBooking(username, slot_id);
	}

	@Override
	public boolean cancelSlot(int booking_id) {
		BookingGMSDao bookingDAO = new BookingGMSDAOImpl();
		return bookingDAO.cancelBooking(booking_id);
	}
}
