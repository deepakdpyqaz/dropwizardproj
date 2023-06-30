/**
 * 
 */
package com.flipkart.jedi.client;

import java.util.*;
import java.util.Scanner;

import com.flipkart.jedi.bean.*;
import com.flipkart.jedi.exceptions.*;
import com.flipkart.jedi.service.*;
import java.text.SimpleDateFormat;

/**
 * 
 */
public class GMSCustomerMenu {
	
	public static void showCustomerMenu(String username) throws SlotAlreadyBookedException, ClashingSlotNotCancelledException, NoClashingSlotException, SlotNotCancelledException, NoSlotsFoundException {
		Scanner in = new Scanner(System.in);
		GMSCustomerMenu gmsCustomerMenu = new GMSCustomerMenu();
		createCustomerMenu(username);
		int userInput = in.nextInt();
		while (userInput != 5) {
			switch (userInput) {
			case 1:
				// Show all gym
				gmsCustomerMenu.showAllGym(username);
				break;
			case 2:
				// Show all slot booking
				gmsCustomerMenu.showAllSlotBooking(username);
				break;
			case 3:
				//Cancel Slot Booking
				gmsCustomerMenu.cancelSlotBooking(username);
				break;
			case 4:
				//Logout
				System.out.println("\nlogged out!\n");
				return;
//				gmsApplication.updatePassword();
			default:
				System.out.println("Invalid Output");
			}
			createCustomerMenu(username);
			userInput = in.nextInt();
		}
	}
	
	public static void createCustomerMenu(String username) {
		System.out.println("\nWelcome " + username + " to FlipFit\n");
		System.out.println("Menu:");
		System.out.println("1. Show all gym");
		System.out.println("2. Show all slot bookings");
		System.out.println("3. Cancel slot bookings");
		System.out.println("4. Logout\n");
		System.out.print("\nEnter your choice: ");

	}
	
	public void showAllGym(String username) throws ClashingSlotNotCancelledException, SlotAlreadyBookedException, NoClashingSlotException, SlotNotCancelledException, NoSlotsFoundException {

		CustomerGMSInterface cusSer = new CustomerGMSService();
		SlotGMSInterface slotSer = new SlotGMSService();
		Scanner in = new Scanner(System.in);
		int slotNo = -1;
		while (slotNo < 0) {
			List<Gym> gymnasiums = cusSer.showAllAvailableGym();
			System.out.println("Name \t Address \t No. of Machines Total Slots");
			for (Gym gym : gymnasiums) {
				System.out.printf("%-5s\t", gym.getGymName());
				System.out.printf("%-18s\t", gym.getAddress());
				System.out.printf("%-10d\t", gym.getNumberOfMachines());
				System.out.printf("%-2d\n", gym.getTotSlots());
			}
			System.out.print("\nPlease Select the gym: ");
			int gymNo = in.nextInt();
			Gym selected_gym = gymnasiums.get(gymNo-1);
			List<Slot> slots = slotSer.getSlotsOfGym(selected_gym.getGymId());

			System.out.println("\n1. Please select the slot you want to book: ");
			System.out.println("Day(mm/dd/yy) \t Start Time \t End Time\tAvailabe Seats");
			SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
			for (Slot st : slots) {
				System.out.printf("%-3tD\t", st.getDay());
				System.out.printf("%s\t", timeFormat.format(st.getSlot_start_time()));
				System.out.printf("%s\t", timeFormat.format(st.getSlot_end_time()));
				System.out.printf("%-3d\n", st.getAvailSeats());
			}
			System.out.println("2. Back(enter -1)\n");
			System.out.print("\nEnter your choice: ");
			slotNo = in.nextInt();

			if (slotNo != -1) {
				BookingSlotGMSService bookSlotser = new BookingSlotGMSService();
				Slot selected_slot = slots.get(slotNo-1);
				Booking clashing = bookSlotser.getClashingBooking(username, selected_slot.getSlotId());
				if (clashing != null) {
					System.out.println("\nYou already have a booking\n");
					System.out.println(clashing.getBooking_id() + "  " + clashing.getSlot_id() + "  "
							+ clashing.getDate());
					System.out.println("Press 1 to cancel old booking and create new booking");
					System.out.println("Press 2 to cancel current booking");
					int booking_choice = in.nextInt();
					if (booking_choice == 2)
						continue;
					else if (booking_choice == 1) {
						if (!slotSer.isSlotAvailable(selected_slot.getSlotId())) {
							System.out.println("\nSlot full, please choose another slot\n");
							continue;
						}
					}
					bookSlotser.cancelSlot(clashing.getBooking_id());
				}
				if (bookSlotser.bookSlot(username, selected_slot.getSlotId())) {
					System.out.println("\nSlot booked successfully!\n");
				} else {
					System.out.println("\nSlot cannot be booked\n");
				}
			}
		}
	}
	
	public void showAllSlotBooking(String username) {
		CustomerGMSInterface cusSer = new CustomerGMSService();
		System.out.println("\nYour Slot Bookings: ");
		List<Booking> bookings = cusSer.showAllBookings(username);
		System.out.println("Booking id\tSlot id\t\tDate");
		for (Booking booking : bookings) {
			System.out
					.println(booking.getBooking_id() + "\t\t" + booking.getSlot_id() + "\t\t" + booking.getDate());
		}
		
	}
	
	public void cancelSlotBooking(String username) {
		CustomerGMSInterface cusSer = new CustomerGMSService();
		Scanner in = new Scanner(System.in);
		List<Booking> previous_bookings = cusSer.showAllBookings(username);
		System.out.println("Booking id\tSlot id\t\tDate");
		for (Booking booking : previous_bookings) {
			System.out
					.println(booking.getBooking_id() + "\t\t" + booking.getSlot_id() + "\t\t" + booking.getDate());
		}

		System.out.print("\nPlease select the slot booking you want to cancel: ");
		int bookingNo = in.nextInt();
		if (cusSer.cancelBooking(previous_bookings.get(bookingNo-1).getBooking_id())) {
			System.out.println("\nYour booking has been successfully cancelled\n");
		} else {
			System.out.println("\nEror! Booking cannot be cancelled\n");
		}
	}
	
	public void customerRegistration() throws RoleNotFoundException {
	
		RoleGMSInterface roleGMSSer = new RoleGMSService();

		Customer newCustomer = new Customer();
		Scanner in = new Scanner(System.in);

		System.out.print("\nPlease enter your Username: ");
		newCustomer.setUsername(in.next());
		System.out.print("Please set your Password: ");
		newCustomer.setPassword(in.next());
		newCustomer.setRoleId(roleGMSSer.getRoleIdByName("Customer"));
		System.out.print("Please enter your Name: ");
		newCustomer.setName(in.next());
		System.out.print("Please enter your Address: ");
		newCustomer.setAddress(in.next());
		System.out.print("Please enter your Phone: ");
		newCustomer.setPhone(in.next());
		System.out.print("Please enter your Gender: ");
		newCustomer.setGender(in.next());

		CustomerGMSInterface cusSer = new CustomerGMSService();
		try {
			cusSer.customerRegistration(newCustomer);
		}
		catch(Exception e){
			e.printStackTrace();
		}

		System.out.println("\nNew Customer Added\n");
	}
}
