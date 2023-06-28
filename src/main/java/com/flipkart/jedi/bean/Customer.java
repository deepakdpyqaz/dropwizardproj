/**
 * 
 */
package com.flipkart.jedi.bean;

/**
 * 
 */
public class Customer extends User{
	private String customerId;
	private String address;
	private String phone;
	private String gender;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Customer(String username, String password, int roleId, String name, String customerId, String address,
			String phone, String gender) {
		super(username, password, roleId, name);
		this.customerId = customerId;
		this.address = address;
		this.phone = phone;
		this.gender = gender;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(String username, String password, int roleId, String name) {
		super(username, password, roleId, name);
		// TODO Auto-generated constructor stub
	}
	
	
}
