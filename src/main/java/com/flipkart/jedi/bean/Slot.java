/**
 * 
 */
package com.flipkart.jedi.bean;

import java.sql.Date;
import java.sql.Time;

/**
 * 
 */
public class Slot{
	private int slotId;
	private Time slot_start_time;
	private Time slot_end_time;
	private int availSeats;
	private int gymId;
	private Date day;

	public int getSlotId() {
		return slotId;
	}
	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}
	public Time getSlot_start_time() {
		return slot_start_time;
	}
	public void setSlot_start_time(Time slotTime) {
		this.slot_start_time = slotTime;
	}
	public int getAvailSeats() {
		return availSeats;
	}
	
	public Slot(int slotId, Time slot_start_time, Time slot_end_time, int availSeats, int gymId, Date day) {
		super();
		this.slotId = slotId;
		this.slot_start_time = slot_start_time;
		this.slot_end_time = slot_end_time;
		this.availSeats = availSeats;
		this.gymId = gymId;
		this.day = day;
	}
	public Time getSlot_end_time() {
		return slot_end_time;
	}
	public void setSlot_end_time(Time slot_end_time) {
		this.slot_end_time = slot_end_time;
	}
	public Slot() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getGymId() {
		return gymId;
	}
	public void setGymId(int gymId) {
		this.gymId = gymId;
	}
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	public void setAvailSeats(int availSeats) {
		this.availSeats = availSeats;
	}
}
