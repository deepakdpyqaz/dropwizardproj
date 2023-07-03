package com.flipkart.jedi.service;

import com.flipkart.jedi.DAO.*;
import com.flipkart.jedi.bean.Booking;
import com.flipkart.jedi.exceptions.*;

/* Booking slot service implements 3 functions -
 * 1.allow a user to book slot, 
 * 2.get clashing bookings incase 2 bookings in the same timeslot
 * 3.remove a slot booking
 * as well*/
public class BookingSlotGMSService implements BookingSlotGMSInterface {
	public boolean bookSlot(String username, int slot_id) throws SlotAlreadyBookedException, NoClashingSlotException, NoSlotsFoundException {
		SlotGMSDao slotDAO = new SlotGMSDAOImpl();
		BookingGMSDao bookingDAO = new BookingGMSDAOImpl();
		SlotGMSInterface slotSer = new SlotGMSService();
		if (slotSer.isSlotAvailable(slot_id)) {
			Booking previous_booking = null;
			try {
				previous_booking = getClashingBooking(username, slot_id);
			} catch (NoClashingSlotException e) {
				throw new RuntimeException(e);
			}
			if (previous_booking != null) {
				return false;
			}
			boolean isBooked = bookingDAO.bookSlot(username, slot_id);
			System.out.println(isBooked);
			if (isBooked) {
				return slotDAO.decrementSeats(slot_id);
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean cancelSlot(int booking_id) throws SlotNotCancelledException {
		BookingGMSDao bookingDAO = new BookingGMSDAOImpl();
		return bookingDAO.cancelBooking(booking_id);
	}

	public Booking getClashingBooking(String username, int slot_id) throws NoClashingSlotException {
		BookingGMSDao bookingDAO = new BookingGMSDAOImpl();
		return bookingDAO.getClashingBooking(username, slot_id);
	}
}
