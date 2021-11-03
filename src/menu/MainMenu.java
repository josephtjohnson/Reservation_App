package menu;

import api.HotelResource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static api.HotelResource.*;

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

                switch (selection) {

                    case 1:
                        findARoom();
                        break;

                    case 2:
                        getCustomerReservations();
                        break;

                    case 3:
                        createCustomer();
                        break;

                    case 4:
                        AdminMenu.adminMenu();
                        break;

                    case 5:
                        System.out.println("Application will now close");
                        keepRunning = false;
                        break;

                    default:
                        System.out.println("Input incorrect. \nPlease enter a number 1 through 5");
                }
            }
            catch(Exception e){
                    System.out.println(e);
                    System.err.println("Input incorrect. \nPlease enter a number 1 through 5");
                }
            }
        }

        public static void findARoom() {
            try{
            Scanner dates = new Scanner(System.in);
            System.out.println("Enter check-in date: mm-dd-yyyy");
            String checkInDate = dates.nextLine();
            System.out.println("Check-in Date: " + checkInDate);
            Date checkIn = new SimpleDateFormat("MM-dd-yyyy").parse(checkInDate);
            System.out.println("Enter check-out date: mm-dd-yyyy");
            String checkOutDate = dates.nextLine();
            System.out.println("Check-out Date: " + checkOutDate);
            Date checkOut = new SimpleDateFormat("MM-dd-yyyy").parse(checkOutDate);
            System.out.println("Will this room be free? Y or N");
            String cost = dates.nextLine();
                switch (cost) {
                    case "Y":
                        boolean isFree = false;
                        break;
                    case "N":
                        isFree = true;
                        break;
                    default:
                        System.out.println("Input incorrect. \nPlease enter Y or N");
                }
            }
            catch (Exception e) {
                System.out.println(e);
                System.err.println("Input incorrect. \nPlease enter Y or N");
            }
            findARoom();
            System.out.println("Book a room? Y or N");
            Scanner book = new Scanner(System.in);
            String reserve = book.nextLine();
            try {
                switch (reserve) {
                    case "Y":
                        System.out.println("Enter customer email");
                        String customerEmail = book.nextLine();
                        System.out.println("Enter room number");
                        String roomNumber = book.nextLine();
                        System.out.println("Enter check in date mm-dd-yyy");
                        String checkInDate2 = book.nextLine();
                        Date checkIn2 = new SimpleDateFormat("MM-dd-yyyy").parse(checkInDate2);
                        System.out.println("Enter check out date mm-dd-yyyy");
                        String checkOutDate2 = book.nextLine();
                        Date checkOut2 = new SimpleDateFormat("MM-dd-yyyy").parse(checkOutDate2);
                        HotelResource.bookARoom(customerEmail, HotelResource.getRoom(roomNumber), checkIn2, checkOut2);
                        System.out.println("Room reserved!");
                        break;
                    case "N":
                        break;
                    default:
                        System.out.println("Input incorrect. \nPlease enter Y or N");
                }
            }
            catch (Exception e) {
                System.out.println(e);
                System.err.println("Input incorrect. \nPlease enter Y or N");
            }
        }

        public static void getCustomerReservations() {
            Scanner email = new Scanner(System.in);
            System.out.println("Enter customer email: ");
            String customerEmail = email.nextLine();
            System.out.println("Customer email: " + customerEmail);
            getAllCustomerReservations(customerEmail);
        }

        public static void createCustomer() {
            Scanner newCustomer = new Scanner(System.in);
            System.out.println("Enter customer email: ");
            String customerEmail = newCustomer.nextLine();
            System.out.println("Enter customer first name: ");
            String customerFirstName = newCustomer.nextLine();
            System.out.println("Enter customer last name: ");
            String customerLastName = newCustomer.nextLine();
            HotelResource.createACustomer(customerEmail, customerFirstName, customerLastName);
        }
    }
