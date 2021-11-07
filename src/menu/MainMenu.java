package menu;

import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.text.SimpleDateFormat;
import java.util.*;

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
                System.out.println("\nPlease select one of the following options");
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
                        System.out.println("\n");
                        break;

                    case 2:
                        getCustomerReservations();
                        System.out.println("\n");
                        break;

                    case 3:
                        createCustomer();
                        System.out.println("\n");
                        break;

                    case 4:
                        AdminMenu.adminMenu();
                        System.out.println("\n");
                        break;

                    case 5:
                        System.out.println("Application will now close");
                        System.out.println("\n");
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
            Date checkIn = null;
            Date checkOut = null;
            boolean isFree = false;
            try {
                Scanner dates = new Scanner(System.in);
                System.out.println("Enter check-in date: mm-dd-yyyy");
                String checkInDate = dates.nextLine();
                System.out.println("Check-in Date: " + checkInDate);
                checkIn = new SimpleDateFormat("MM-dd-yyyy").parse(checkInDate);
                System.out.println("Enter check-out date: mm-dd-yyyy");
                String checkOutDate = dates.nextLine();
                System.out.println("Check-out Date: " + checkOutDate);
                checkOut = new SimpleDateFormat("MM-dd-yyyy").parse(checkOutDate);
                System.out.println("Will this room be free? Y or N");
                String cost = dates.nextLine();
                switch (cost.toUpperCase()) {
                    case "Y":
                        isFree = true;
                        break;
                    case "N":
                        isFree = false;
                        break;
                    default:
                        System.out.println("Input incorrect. \nPlease enter Y or N");
                }
            } catch (Exception e) {
                System.out.println(e);
                System.err.println("Input incorrect. \nPlease enter Y or N");
            }
            System.out.println("Book a room? Y or N");
            Scanner book = new Scanner(System.in);
            String reserve = book.nextLine();
            try {
                switch (reserve.toUpperCase()) {
                    case "Y":
                        System.out.println("Here are all available hotel rooms");
                        HotelResource.findARoom(checkIn, checkOut);
                        System.out.println("Enter room number");
                        String roomNumber = book.nextLine();
                        System.out.println("Enter customer email");
                        String customerEmail = book.nextLine();
                        HotelResource.bookARoom(customerEmail, HotelResource.getRoom(roomNumber), checkIn, checkOut, isFree);
                        System.out.println("Room reserved!");
                        break;
                    case "N":
                        break;
                    default:
                        System.out.println("Input incorrect. \nPlease enter Y or N");
                }
            } catch (Exception e) {
                System.out.println(e);
                System.err.println("Input incorrect. \nPlease enter Y or N");
            }
        }

        public static void getCustomerReservations() {
            Collection<Customer> customerList = new ArrayList<>(HotelResource.getAllCustomers());
            Scanner email = new Scanner(System.in);
            System.out.println("Enter customer email: ");
            String customerEmail = email.nextLine();
            for(Customer customer:customerList){
                for(Collection reservation:getAllCustomerReservations(customer)) {
                    if (customer.equals(HotelResource.retrieveCustomer(customerEmail))) {
                        System.out.println(reservation);
                    }
                    else {
                        System.out.println("Could not find customer reservations.");
                    }
                }
            }
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
