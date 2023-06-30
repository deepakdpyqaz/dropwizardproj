/**
 * 
 */
package com.flipkart.jedi.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.flipkart.jedi.exceptions.*;
import com.flipkart.jedi.service.GymOwnerGMSService;
import com.flipkart.jedi.service.RoleGMSInterface;
import com.flipkart.jedi.service.RoleGMSService;
import com.flipkart.jedi.bean.Gym;
import com.flipkart.jedi.bean.GymOwner;
import com.flipkart.jedi.bean.Slot;
import com.flipkart.jedi.bean.User;
import com.flipkart.jedi.service.*;

import java.sql.Time;

/**
 * 
 */
public class GMSGymOwnerMenu {
	public void showGymOwnerMenu(String username) throws GymNotRemovedException, GymAlreadyExistsException, SlotNotCreatedException, NoSlotsFoundException {
		Scanner in = new Scanner(System.in);
		System.out.println("\nWelcome " + username + " to FlipFit\n");
		System.out.println("Menu:");
		System.out.println("1. Add Gym Center");
		System.out.println("2. Remove Gym Center");
		System.out.println("3. List All My Gym Center");
		System.out.println("4. Logout\n");
		System.out.print("Enter your choice: ");

		int gymOwnerChoice = in.nextInt();
		while (gymOwnerChoice != 5) {
			switch (gymOwnerChoice) {
			case 1:
				Gym newGym = new Gym();
				newGym.setGymOwnerId(username);
				System.out.print("Enter information for Gym\n");
				System.out.print("Enter Gym Name: ");
				newGym.setGymName(in.next());
				System.out.print("Enter GST Number: ");
				newGym.setGstNo(in.next());
				System.out.print("Enter Address of the Gym: ");
				newGym.setAddress(in.next());
				System.out.print("Enter no of seats in a slot: ");
				newGym.setSeats(in.nextInt());
				System.out.print("Enter total number of machines: ");
				newGym.setNumberOfMachines(in.nextInt());
				System.out.print("Enter total number of instructors: ");
				newGym.setNumberOfInstructors(in.nextInt());
				System.out.print("Is cardio available (0 for No and 1 for Yes): ");
				newGym.setIsCardioAvailable(in.nextBoolean());
				System.out.print("Is crossfit available (0 for No and 1 for Yes): ");
				newGym.setIsCrossfitAvailable(in.nextBoolean());
				System.out.print("Enter total floor area: ");
				newGym.setFloorArea(in.nextInt());
				System.out.print("Enter total no of slots: ");
				int total_slots = in.nextInt();
				newGym.setTotSlots(total_slots);
				GymOwnerGMSInterface gymOwnSer = new GymOwnerGMSService();
				Gym new_gym = gymOwnSer.addGym(newGym);
				List<Slot> slots = new ArrayList<Slot>();
				System.out.println("Enter the slot informations\n");
				for (int i = 0; i < total_slots; i++) {
					System.out.print("Enter the start time (HH:mm:ss): ");
					String startTimeString = in.next();
					System.out.print("Enter the end time (HH:mm:ss): ");
					String endTimeString = in.next();
					Time startTime = Time.valueOf(startTimeString);
					Time endTime = Time.valueOf(endTimeString);
					slots.add(new Slot(-1, startTime, endTime, newGym.getSeats(), newGym.getGymId(), null));
					System.out.println();

				}
				SlotGMSInterface slotSer = new SlotGMSService();
				slotSer.createSlots(slots);
				System.out.println("Your Slots have been added");
				break;
			case 2: {
				GymOwnerGMSInterface gymOwner = new GymOwnerGMSService();
				List<Gym> gymnasiums = gymOwner.viewGym(username);
				System.out.println("Name \t Address \t Total Slots Number of Machines");
				for (Gym myGym : gymnasiums) {
					System.out.printf("%-5s\t", myGym.getGymName());
					System.out.printf("%-10s\t", myGym.getAddress());
					System.out.printf("%-3d\t", myGym.getTotSlots());
					System.out.printf("%-3d\n", myGym.getNumberOfMachines());

				}
				int choice = in.nextInt();
				Gym selected = gymnasiums.get(choice - 1);
				if (gymOwner.removeGym(selected.getGymId())) {
					System.out.println("Gym removed");
				} else {
					System.out.println("Gym not removed. Please try again");
				}

				break;
			}
			case 3:
				GymOwnerGMSInterface gymOwner = new GymOwnerGMSService();
				List<Gym> gymnasiums = gymOwner.viewGym(username);
				System.out.println("Name \t Address \t Total Slots Number of Machines");
				for (Gym myGym : gymnasiums) {
					System.out.printf("%-5s\t", myGym.getGymName());
					System.out.printf("%-10s\t", myGym.getAddress());
					System.out.printf("%-3d\t", myGym.getTotSlots());
					System.out.printf("%-3d\n", myGym.getNumberOfMachines());

				}
				break;
			case 4:
				System.out.println("\nLogged Out!\n");
				return;
			}
		}
	}

// **remove parameters **
	public void gymOwnerRegistration(List<User> userList, List<GymOwner> gymOwnerList) throws GymOwnerAlreadyRegisteredException, GymAlreadyRegisteredException, RoleNotFoundException {
		Scanner in = new Scanner(System.in);

		RoleGMSInterface roleGMSSer = new RoleGMSService();
		GymOwnerGMSInterface gymOwner = new GymOwnerGMSService();
		UserGMSInterface user = new UserGMSService();

		GymOwner newGymOwner = new GymOwner("1", "1", 1, "1");
		User newUser = new User("1", "1", 1, "1");

		newGymOwner.setRoleId(roleGMSSer.getRoleIdByName("Gym Owner"));
		newUser.setRoleId(roleGMSSer.getRoleIdByName("Gym Owner"));

		System.out.print("Please enter your Name: ");
		newGymOwner.setName(in.next());
		newUser.setName(newGymOwner.getName());

		System.out.print("Please enter your username: ");
		newGymOwner.setUsername(in.next());
		newUser.setUsername(newGymOwner.getUsername());

		System.out.print("Please set your password: ");
		newGymOwner.setPassword(in.next());
		newUser.setPassword(newGymOwner.getPassword());

		System.out.print("Please enter your Address: ");
		newGymOwner.setAddress(in.next());

		System.out.print("Enter Aadhaar No: ");
		newGymOwner.setAadharNo(in.next());

		System.out.print("Enter PAN No: ");
		newGymOwner.setPanCard(in.next());

		user.userRegistration(newUser);
		gymOwner.gymOwnerRegister(newGymOwner);

		userList.add(newUser);
		System.out.println("\nNew GymOwner Added\n");
	}
}
