package com.flipkart.jedi.service;

import java.util.List;

import com.flipkart.jedi.bean.Booking;
import com.flipkart.jedi.bean.Customer;
import com.flipkart.jedi.bean.Gym;
import com.flipkart.jedi.DAO.*;
import com.flipkart.jedi.exceptions.UsernameAlreadyUsedException;

/* 
 * CustomerService implements functions to allow a customer to -
 * 1. view all available gyms in the city
 * 2. view details about a gym
 * 3. show all bookings by a customer
 * 4. cancel a booking as needed
 * 5. calls the customerRegistration function as well
 * */
public class CustomerGMSService implements CustomerGMSInterface {
	
	public List<Gym> showAllAvailableGym() {
		GymGMSDao GMSDao = new GymGMSDAOImpl();
		List<Gym> gymnasiums = GMSDao.getAllAvailableGyms();
		return gymnasiums;
	}
	public Gym showGymDetails(int gymNo) {

		GymGMSDao GMSDao = new GymGMSDAOImpl();
		Gym gym = GMSDao.getGymById(gymNo);
		return gym;
	}
	public List<Booking> showAllBookings(String username) {
		BookingGMSDao bookingDao = new BookingGMSDAOImpl();
		List<Booking> bookings = bookingDao.getAllBookings(username);
		return bookings;
		
	}
	public boolean cancelBooking(String username , int bookingNo) {
		return true;
	}
	public boolean customerRegistration(Customer newCustomer) throws UsernameAlreadyUsedException {
		CustomerGMSDao custDao = new CustomerGMSDAOImpl();
		custDao.createCustomer(newCustomer);
		
		return true;
	}
	@Override
	public boolean cancelBooking(int booking_id) {
		// TODO Auto-generated method stub
		BookingGMSDao bookingDao = new BookingGMSDAOImpl();
		return bookingDao.cancelBooking(booking_id);
	}
	
}
