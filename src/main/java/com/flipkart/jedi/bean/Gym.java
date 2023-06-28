package com.flipkart.jedi.bean;

public class Gym {
	private int gymId;
	private String gymName;
	private String gstNo;
	private String address;
	private int totSlots;
	private int numberOfMachines;
	private Slot slots[];
	private int seats;
	private String gymOwnerId;
	private int numberOfInstructors;
	private boolean isCardioAvailable;
	private boolean isCrossfitAvailable;
	private int floorArea;
	private boolean isApproved;
	
	
	public int getNumberOfInstructors() {
		return numberOfInstructors;
	}

	public void setNumberOfInstructors(int numberOfInstructors) {
		this.numberOfInstructors = numberOfInstructors;
	}

	public boolean isIsCardioAvailable() {
		return isCardioAvailable;
	}

	public void setIsCardioAvailable(boolean isCardioAvailable) {
		this.isCardioAvailable = isCardioAvailable;
	}

	public boolean isIsCrossfitAvailable() {
		return isCrossfitAvailable;
	}

	public void setIsCrossfitAvailable(boolean isCrossfitAvailable) {
		this.isCrossfitAvailable = isCrossfitAvailable;
	}

	public int getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(int floorArea) {
		this.floorArea = floorArea;
	}

	public boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public Gym(int gymId, String gymName, String gstNo, String address, int totSlots, int numberOfMachines,
			int seats, String gymOwnerId, int numberOfInstructors, boolean isCardioAvailable,
			boolean isCrossfitAvailable, int floorArea, boolean isApproved) {
		super();
		this.gymId = gymId;
		this.gymName = gymName;
		this.gstNo = gstNo;
		this.address = address;
		this.totSlots = totSlots;
		this.numberOfMachines = numberOfMachines;
		this.seats = seats;
		this.gymOwnerId = gymOwnerId;
		this.numberOfInstructors = numberOfInstructors;
		this.isCardioAvailable = isCardioAvailable;
		this.isCrossfitAvailable = isCrossfitAvailable;
		this.floorArea = floorArea;
		this.isApproved = isApproved;
	}

	public Gym(int gymId, String gymName, String gstNo, String address, int totSlots, int numberOfMachines, int seats, String gymOwnerId) {
		super();
		this.gymId = gymId;
		this.gymName = gymName;
		this.gstNo = gstNo;
		this.address = address;
		this.totSlots = totSlots;
		this.numberOfMachines = numberOfMachines;
		this.seats = seats;
		this.gymOwnerId = gymOwnerId;
	}

	public Gym() {
		// TODO Auto-generated constructor stub
	}

	public String getGstNo() {
		return gstNo;
	}
	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}
	public Slot[] getSlots() {
		return slots;
	}
	public void setSlots(Slot[] slots) {
		this.slots = slots;
	}
	public int getGymId() {
		return gymId;
	}
	public void setGymId(int gymId) {
		this.gymId = gymId;
	}
	public String getGymName() {
		return gymName;
	}
	public void setGymName(String gymName) {
		this.gymName = gymName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getTotSlots() {
		return totSlots;
	}
	public void setTotSlots(int totSlots) {
		this.totSlots = totSlots;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public int getNumberOfMachines() {
		return numberOfMachines;
	}
	public void setNumberOfMachines(int numberOfMachines) {
		this.numberOfMachines = numberOfMachines;
	}
	public String getGymOwnerId() {
		return gymOwnerId;
	}
	public void setGymOwnerId(String gymOwnerId) {
		this.gymOwnerId = gymOwnerId;
	}
	
	
}
