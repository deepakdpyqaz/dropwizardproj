/**
 * 
 */
package com.flipkart.jedi.service;

import java.util.List;

import com.flipkart.jedi.bean.*;
import com.flipkart.jedi.exceptions.InvalidLoginCredentialsException;

/** Interface for CustomerService
 * 
 */
public interface CustomerGMSInterface {
	public boolean customerRegistration(Customer newCustomer);
	public List<Gym> showAllAvailableGym();
	public Gym showGymDetails(int gym_id);
	public List<Booking> showAllBookings(String username);
	public boolean cancelBooking(int booking_id);
	public Registration getAllDetails(String user_id) throws InvalidLoginCredentialsException;
}
