package menu;

import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        boolean keepRunning = true;
        Scanner scanner = new Scanner(System.in);
        while (keepRunning) {
            try {
                System.out.println("Please select one of the following options");
                System.out.println("1. Find and reserve a room");
                System.out.println("2. See my reservation");
                System.out.println("3. Create an account");
                System.out.println("4. Admin");
                System.out.println("5. Exit");
                String userInput = scanner.nextLine();
                System.out.println("Option selected: " + userInput);
                int selection = Integer.parseInt(userInput);

                if (selection == 1) {
                    Scanner dates = new Scanner(System.in);
                    System.out.println("Enter check-in date: mm-dd-yyyy");
                    String checkInDate = scanner.nextLine();
                    System.out.println("Check-in Date: " + checkInDate);
                    Date checkIn = null;
                    try {
                        checkIn = new SimpleDateFormat("MM-dd-yyyy").parse(checkInDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Enter check-out date: mm-dd-yyyy");
                    String checkOutDate = scanner.nextLine();
                    System.out.println("Check-out Date: " + checkOutDate);
                    Date checkOut = null;
                    try {
                        checkOut = new SimpleDateFormat("MM-dd-yyyy").parse(checkInDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    HotelResource.findARoom(checkIn, checkOut);
                }
                if (selection == 2) {
                    Scanner email = new Scanner(System.in);
                    System.out.println("Enter customer email: ");
                    String customerEmail = scanner.nextLine();
                    System.out.println("Customer email: " + customerEmail);
                    HotelResource.getCustomerReservations(customerEmail);
                    }
                if (selection == 3) {
                    Scanner newCustomer = new Scanner(System.in);
                    System.out.println("Enter customer email: ");
                    String customerEmail = scanner.nextLine();
                    System.out.println("Enter customer first name: ");
                    String customerFirstName = scanner.nextLine();
                    System.out.println("Enter customer last name: ");
                    String customerLastName = scanner.nextLine();
                    HotelResource.createACustomer(customerEmail, customerFirstName, customerLastName);
                }
                if (selection == 4) {
                    AdminMenu.adminMenu();
                }
                if (selection == 5) {
                    keepRunning = false;
                }
                else {
                    System.out.println("Input incorrect. \nPlease enter a number 1 through 5");
                }
            }
            catch(Exception e) {
                System.out.println(e);
                System.out.println("Input incorrect. \nPlease enter a number 1 through 5");
            }
        }
    }
}