/**
 * 
 */
package com.flipkart.jedi.bean;

import java.util.Date;

/**
 * 
 */
public class Booking {
	/**
	 * @return the booking_id
	 */

	private int booking_id;
	private int slot_id;
	private String customer_id;
	private Date date;
	
	public Booking(int booking_id, int slot_id, String customer_id, Date date) {
		super();
		this.booking_id = booking_id;
		this.slot_id = slot_id;
		this.customer_id = customer_id;
		this.date = date;
	}
	public int getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}
	public int getSlot_id() {
		return slot_id;
	}
	public void setSlot_id(int slot_id) {
		this.slot_id = slot_id;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	

}
