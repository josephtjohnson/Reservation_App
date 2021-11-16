package menu;

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
                        findAndReserveRoom();
                        System.out.println("******************************");
                        System.out.println("THANK YOU FOR YOUR RESERVATION");
                        System.out.println("******************************");
                        break;

                    case 2:
                        getAllCustomerReservations();
                        System.out.println("\n");
                        break;

                    case 3:
                        createCustomer();
                        System.out.println("\n****************");
                        System.out.println("CUSTOMER CREATED");
                        System.out.println("****************");

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
    }

    public static void findAndReserveRoom() {
        Date checkIn = null;
        Date checkOut = null;
        var isFree = false;
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
                    System.out.println(HotelResource.findAvailableRooms(checkIn, checkOut));
                    System.out.println("Enter room number");
                    String roomNumber = book.nextLine();
                    System.out.println("Enter customer email");
                    String customerEmail = book.nextLine();
                    IRoom room = HotelResource.getRoom(roomNumber);
                    HotelResource.bookARoom(customerEmail, room, checkIn, checkOut, isFree);
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
