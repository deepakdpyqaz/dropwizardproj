package com.flipkart.jedi.client;

import java.util.*;
import com.flipkart.jedi.service.*;
import com.flipkart.jedi.bean.*;
import com.flipkart.jedi.exceptions.*;

public class GMSApplication {

	public static List<User> userList = new ArrayList<>();

	public static List<Customer> customerList = new ArrayList<>();

	public static List<GymOwner> gymOwnerList = new ArrayList<>();

	public static void menu() {
		try {
			Scanner in = new Scanner(System.in);
			GMSApplication gmsApplication = new GMSApplication();
			createMainMenu();
			int userInput = in.nextInt();
			while (userInput != 5) {
				switch (userInput) {
				case 1:
					// login
					gmsApplication.loginUser();
					break;
				case 2:
					// student registration
					gmsApplication.customerRegistration();
					break;
				case 3:
					gmsApplication.gymownerRegistration();
					break;
				case 4:
					gmsApplication.updatePassword();
				default:
					System.out.println("Invalid Output");
				}
				createMainMenu();
				userInput = in.nextInt();
			}
		} catch (InputMismatchException excep) {
			System.out.println("Wrong user input, try again!");
			menu();
		}
	}

	public static void main(String[] args) {
		menu();
	}

	public static void createMainMenu() {
		System.out.println("\nWelcome to FlipFit Gymnasium Application\n");
		System.out.println("Menu:");
		System.out.println("1. Login");
		System.out.println("2. Customer Registration");
		System.out.println("3. GymOwner Registration");
		System.out.println("4. Update Password");
		System.out.println("5. Exit\n");
		System.out.print("Enter your choice: ");

	}

	public void loginUser() {
		System.out.println("\nLogin to FlipFit\n");
		System.out.print("Enter your username: ");
		Scanner in = new Scanner(System.in);
		String username = in.next();
		System.out.print("Enter your password: ");
		String password = in.next();
		UserGMSInterface userSer = new UserGMSService();
		RoleGMSInterface rolSer = new RoleGMSService();
		User user = null;
		try {
			user = userSer.login(username, password);
			if (user != null) {
				String role = rolSer.getRoleNameById(user.getRoleId());
				switch (role) {
				case "Customer":
					GMSCustomerMenu.showCustomerMenu(username);
					break;

				case "Gym Owner":
					GMSGymOwnerMenu gymOwnerMenu = new GMSGymOwnerMenu();
					gymOwnerMenu.showGymOwnerMenu(username);
					break;

				case "Admin":
					GMSAdminMenu adminMenu = new GMSAdminMenu();
					adminMenu.showAdminMenu(username);
					break;

				default:
					System.out.println("\nRole not found\n");

					break;
				}
			}
		} catch (InvalidLoginCredentialsException ilce) {
			System.out.println(ilce.getMessage());
			return;
		}
		catch (AccountNotApprovedException acne) {
			System.out.println(acne.getMessage());
			return;
		}
	}

	public void customerRegistration() {
		GMSCustomerMenu cusMenu = new GMSCustomerMenu();
		cusMenu.customerRegistration();
	}

	public void gymownerRegistration() {
		GMSGymOwnerMenu gymOwnerMenu = new GMSGymOwnerMenu();
		gymOwnerMenu.gymOwnerRegistration(userList, gymOwnerList);

	}

	public boolean updatePassword() {
		UserGMSInterface userSerr = new UserGMSService();
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter your username: ");
		String usernamee = in.nextLine();
		System.out.print("Please enter your old password: ");
		String oldPassword = in.nextLine();
		System.out.print("Please enter your new password: ");
		String newPassword = in.nextLine();
		in.close();
		if (userSerr.updatePassword(usernamee, oldPassword, newPassword))
			return true;
		return false;
	}

}
