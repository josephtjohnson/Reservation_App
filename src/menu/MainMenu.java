package menu;

import api.AdminResource;
import api.HotelResource;
import model.IRoom;
import java.text.SimpleDateFormat;
import java.util.*;

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
                        findAndReserveRoom();
                        break;

                    case 2:
                        getCustomersReservation();
                        break;

                    case 3:
                        createCustomer();
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

    public static void findAndReserveRoom() {
        final AdminResource adminResource = AdminResource.getInstance();
        Collection<String> roomNumbers = new HashSet<>();
        Date checkIn = null;
        Date checkOut = null;
        var isFree = false;
        try {
            Scanner dates = new Scanner(System.in);
            System.out.println("Enter check-in date: mm-dd-yyyy");
            String checkInDate = dates.nextLine();
            checkIn = new SimpleDateFormat("MM-dd-yyyy").parse(checkInDate);
            boolean in = true;
            String checkOutDate = null;
            while (in) {
                try {
                    System.out.println("Enter check-out date: mm-dd-yyyy");
                    checkOutDate = dates.nextLine();
                    checkOut = new SimpleDateFormat("MM-dd-yyyy").parse(checkOutDate);
                    if (checkOutDate.equals(checkInDate) || (checkOut.before(checkIn))) {
                        System.out.println("Checkout date must be after checkin date.");
                    }
                    else {
                        in = false;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Please use the correct date format.");
        }
        System.out.println("Book a room? Y or N");
        Scanner book = new Scanner(System.in);
        String reserve = book.nextLine();
        try {
            switch (reserve.toUpperCase()) {
                case "Y":
                    if (adminResource.getAllRooms().isEmpty()) {
                        System.out.println("There are no rooms in the database. Please create rooms.\n");
                        break;
                    }
                    else {
                        Collection<IRoom> rooms = new ArrayList<>(HotelResource.findARoom(checkIn, checkOut));
                        if (!rooms.isEmpty()){
                            System.out.println("Here are all available hotel rooms");
                            for (IRoom room : rooms) {
                                System.out.println(room);
                                roomNumbers.add(room.getRoomNumber());
                            }
                        }
                        else {
                            boolean run = true;
                            while(run) {
                                try {
                                    System.out.println("No rooms available for the selected dates. Search for rooms?");
                                    String search = book.nextLine();
                                    switch (search.toUpperCase()) {
                                        case "Y":
                                            Collection<IRoom> newRooms = new ArrayList<>(HotelResource.findARoom(checkIn, checkOut));
                                            if (newRooms.isEmpty()) {
                                                boolean keepRunning = true;
                                                while (keepRunning) {
                                                    checkIn = adminResource.addDays(checkIn, 7);
                                                    checkOut = adminResource.addDays(checkOut, 7);
                                                    newRooms = new ArrayList<>(HotelResource.findARoom(checkIn, checkOut));
                                                    if (!newRooms.isEmpty()) {
                                                        keepRunning = false;
                                                    }
                                                }
                                                System.out.println("Here are all available hotel rooms");
                                                System.out.println("Dates: " + checkIn + "-" + checkOut + "\n");
                                                for (IRoom room : newRooms) {
                                                    System.out.println(room);
                                                    roomNumbers.add(room.getRoomNumber());
                                                }
                                                run = false;
                                            } else {
                                                System.out.println("Here are all available hotel rooms");
                                                System.out.println("Dates: " + checkIn + "-" + checkOut + "\n");
                                                for (IRoom room : newRooms) {
                                                    System.out.println(room);
                                                    roomNumbers.add(room.getRoomNumber());
                                                }
                                            }
                                            break;
                                        case "N":
                                            return;
                                        default:
                                            System.out.println("Input incorrect. Please enter Y or N.");

                                    }
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                            }
                            boolean running = true;
                            while (running) {
                                try {
                                    System.out.println("\nBook room?");
                                    String confirm = book.nextLine();
                                    switch (confirm.toUpperCase()) {
                                        case "Y":
                                            running = false;
                                        case "N":
                                            return;
                                        default:
                                            System.out.println("Input incorrect. Please enter Y or N.");
                                    }
                                }
                                catch (Exception e) {
                                    System.out.println(e);
                                }
                            }
                        }
                    }
                    System.out.println("\nEnter room number");
                    String roomNumber = null;
                    while(true) {
                        try {
                            roomNumber = book.nextLine();
                            if (!roomNumbers.contains(roomNumber)) {
                                System.out.println("Room unavailable. Please select an available room from the list.");
                            }
                            else {
                                break;
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                            System.out.println("Invalid input");
                        }
                    }
                    System.out.println("Enter customer email");
                    String customerEmail = book.nextLine();
                    IRoom room = HotelResource.getRoom(roomNumber);
                    HotelResource.bookARoom(customerEmail, room, checkIn, checkOut, isFree);
                    System.out.println("******************************");
                    System.out.println("THANK YOU FOR YOUR RESERVATION");
                    System.out.println("******************************");
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
    public static void getCustomersReservation() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter customer Email:");
            String email = scanner.next();
            String customer = HotelResource.getCustomer(email).getFullName();
            HotelResource.getCustomerReservations(customer);
        }
        catch (Exception e) {
            System.out.println("");
        }
    }
    public static void createCustomer() {
        try {
            Scanner newCustomer = new Scanner(System.in);
            System.out.println("Enter customer email: ");
            String customerEmail = newCustomer.nextLine();
            System.out.println("Enter customer first name: ");
            String customerFirstName = newCustomer.nextLine();
            if (customerFirstName.isEmpty()) {
                System.out.println("First name cannot be empty.\n");
                return;
            }
            System.out.println("Enter customer last name: ");
            String customerLastName = newCustomer.nextLine();
            if (customerLastName.isEmpty()) {
                System.out.println("Last name cannot be empty.\n");
                return;
            }
            HotelResource.createCustomer(customerEmail, customerFirstName, customerLastName);
            System.out.println("\n****************");
            System.out.println("CUSTOMER CREATED");
            System.out.println("****************");
        }
        catch (IllegalArgumentException e) {
            System.out.println("Email format not valid. Please use a valid email address\n");
        }
    }
}
