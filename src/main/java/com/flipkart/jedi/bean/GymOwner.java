/**
 * 
 */
package com.flipkart.jedi.bean;

import java.util.List;

/**
 * 
 */
public class GymOwner extends User{
//	public GymOwner(String userId, String password, String role) {
//		super(userId, password, role);
//		// TODO Auto-generated constructor stub
//	}
//	public List<Gym> getAllGym() {
//		return allGym;
//	}
	public GymOwner() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GymOwner(String username, String password, int roleId, String name, String registrationId, String address,
			String aadharNo, String panNo, Boolean isApproved) {
		super(username, password, roleId, name);
		this.registrationId = registrationId;
		this.address = address;
		AadharNo = aadharNo;
		this.panNo = panNo;
	}
	public GymOwner(String username, String password, int roleId, String name) {
		super(username, password, roleId, name);
		// TODO Auto-generated constructor stub
	}
//	public void setAllGym(List<Gym> allGym) {
//		this.allGym = allGym;
//	}
	private String registrationId;

	private String address;
//	private List<Gym> allGym;
	private String AadharNo;
	private String panNo;
	private Boolean isApproved;
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public Boolean getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}
	public String getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	
	
	
	public String getPanCard() {
		return panNo;
	}
	public void setPanCard(String panCard) {
		this.panNo = panCard;
	}

	public String getAadharNo() {
		return AadharNo;
	}
	public void setAadharNo(String aadharNo) {
		AadharNo = aadharNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
