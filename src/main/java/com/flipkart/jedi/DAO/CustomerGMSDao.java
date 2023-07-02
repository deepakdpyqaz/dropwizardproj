/**
 * 
 */
package com.flipkart.jedi.DAO;


import com.flipkart.jedi.bean.Customer;
import com.flipkart.jedi.bean.Registration;

/**
 * 
 */
public interface CustomerGMSDao {
	public boolean createCustomer(Customer customer);


	Registration getAllDetails(String userId);
}

