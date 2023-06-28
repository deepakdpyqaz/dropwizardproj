/**
 * 
 */
package com.flipkart.jedi.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.flipkart.jedi.bean.Gym;
import com.flipkart.jedi.bean.GymOwner;
import com.flipkart.jedi.service.*;

/**
 * 
 */
public class GMSAdminMenu {
	public void showAdminMenu(String username) {
		Scanner in = new Scanner(System.in);
		int adminChoice;
		do {
			System.out.println("\nWelcome " + username + " to FlipFit\n");
			System.out.println("Menu:");
			System.out.println("1. Show all Gym Owners");
			System.out.println("2. Show all Gym Centers");
			System.out.println("3. Logout\n");
			System.out.print("Enter your choice: ");

			adminChoice = in.nextInt();

			switch (adminChoice) {
			case 1:
			{
				System.out.println("List of Gym Owners:\n");
				AdminGMSInterface adminSer = new AdminGMSService();
				List<GymOwner> gymowners = new ArrayList<GymOwner>();
				System.out.println("Sn.    Name \t     Address \t     Aadhar Card \t Pan Card \t Approval");
				int cnt = 1;
				for(GymOwner gymowner: gymowners) {
					System.out.printf(cnt+"\t");
					System.out.printf("%-ts\t", gymowner.getName());
					System.out.printf("%-10s\t",gymowner.getAddress());
					System.out.printf("%-10s\t",gymowner.getAadharNo());
					System.out.printf("%-10s\t",gymowner.getPanCard());
					if(gymowner.getIsApproved()) {
						System.out.printf("Approved\n");
					}
					else {
						System.out.println("Not approved\n");
					}
				}
				System.out.println("Select the gym owner to approve:");
				int choice = in.nextInt()-1;
				GymOwner selected = gymowners.get(choice);
				if(adminSer.approveGymOwner(selected.getUsername())) {
					System.out.println("Gym Owner '"+selected.getUsername()+"' approved");
				}
				else {
					System.out.println("Not approved");
				}
				
				
				
			}
			case 2:
				System.out.println("List of Gym Centres:\n");
				AdminGMSInterface adminSer = new AdminGMSService();
				List<Gym> gymnasiums = adminSer.showAllGymCentres();

				System.out.println("Sno.    Name \t      Address \t     GST No.        Approval");
				int cnt = 1;

				for (Gym gym : gymnasiums) {
					System.out.printf(cnt + "\t");
					System.out.printf("%-5s\t     ", gym.getGymName());
					System.out.printf("%-10s\t   ", gym.getAddress());
					System.out.printf("%-10s\t   ", gym.getGstNo());
					if (gym.getIsApproved()) {
						System.out.printf("Approved\n");
					} else {
						System.out.printf("Not approved\n");
					}

					cnt++;
				}

				System.out.println("\nEnter -1 to go back");
				System.out.print("\nPlease select the gym cemtre to approve: ");

				int gymNo = in.nextInt();
				if(gymNo<1) {
					break;
				}
				int gymId = gymnasiums.get(gymNo - 1).getGymId();
				if (gymnasiums.get(gymNo - 1).getIsApproved()) {
					System.out.println("\nGym already approved!\n");
				} else if (adminSer.approveGym(gymId)) {
					System.out.println("\nGym has been successfully approved!\n");
				} else {
					System.out.println("\nError!Gym Cannot be approved\n");
				}
				break;
			case 3:
				System.out.println("logged out!\n");
				break;
			}
		} while (adminChoice != 3);
	}
}
