/**
 * 
 */
package com.flipkart.jedi.service;

import java.util.List;

import com.flipkart.jedi.bean.*;
import com.flipkart.jedi.exceptions.InvalidLoginCredentialsException;
import com.flipkart.jedi.bean.Booking;
import com.flipkart.jedi.bean.Customer;
import com.flipkart.jedi.bean.Gym;
import com.flipkart.jedi.exceptions.UsernameAlreadyUsedException;

/** Interface for CustomerService
 * 
 */
public interface CustomerGMSInterface {
	public boolean customerRegistration(Customer newCustomer) throws UsernameAlreadyUsedException;
	public List<Gym> showAllAvailableGym();
	public Gym showGymDetails(int gym_id);
	public List<Booking> showAllBookings(String username);
	public boolean cancelBooking(int booking_id);
	public Registration getAllDetails(String user_id) throws InvalidLoginCredentialsException;
}
