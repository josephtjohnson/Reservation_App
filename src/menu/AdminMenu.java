package menu;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.FreeRoom;
import model.IRoom;
import model.Room;

import java.text.SimpleDateFormat;
import java.util.*;

import static api.AdminResource.*;


public class AdminMenu {
    public static void main(String args){
        adminMenu();
    }
    public static void adminMenu() {
        boolean keepRunning = true;
        Scanner scanner = new Scanner(System.in);
        while (keepRunning) {
            try {
                System.out.println("Please select one of the following options");
                System.out.println("1. See all customers");
                System.out.println("2. See all rooms");
                System.out.println("3. See all reservations");
                System.out.println("4. Add a room");
                System.out.println("5. Create test data (Customers, Rooms, Reservations)");
                System.out.println("6. Back to main menu");
                String userInput = scanner.nextLine();
                System.out.println("Option selected: " + userInput);
                int selection = Integer.parseInt(userInput);

                switch (selection) {
                    case 1:
                        customerList();
                        System.out.println("\n");
                        break;
                    case 2:
                        roomList();
                        System.out.println("\n");
                        break;
                    case 3:
                        displayAllReservations();
                        System.out.println("\n");
                        break;
                    case 4:
                        addRooms();
                        System.out.println("\n");
                        break;
                    case 5:
                        addTestCustomers();
                        addTestRooms();
                        addTestReservations();
                        System.out.println("\n*****************");
                        System.out.println("TEST DATA CREATED");
                        System.out.println("*****************\n");
                        break;
                    case 6:
                        MainMenu.menu();
                        System.out.println("\n");
                        break;                
                    default:
                        System.out.println("\nInput incorrect. \nPlease enter a number 1 through 5\n");
                }
            }
            catch(Exception e) {
                System.out.println(e);
                System.out.println("\nInput incorrect. \nPlease enter a number 1 through 5\n");
            }
        }
    }
    public static void addTestRooms(){
        List<IRoom> testRooms = new ArrayList<>();
        for(int i = 1; i < 10; i++) {
            String roomNumber = String.valueOf(i);
            if (i % 2 == 0){
                Double roomPrice = (double) i;
                IRoom room = new Room(roomNumber, roomPrice, IRoom.RoomType.SINGLE, false);
                if(!testRooms.contains(room)) {
                    testRooms.add(room);
                    HotelResource.addRoom(room);
                }
            }
            else {
                Double roomPrice = 0.0;
                IRoom room = new FreeRoom(roomNumber, roomPrice, IRoom.RoomType.DOUBLE, true);
                if(!testRooms.contains(room)) {
                    testRooms.add(room);
                    HotelResource.addRoom(room);

                }
            }
        }
        System.out.println("TEST ROOMS CREATED\n");
    }
    public static void addTestCustomers() {
        var names = List.of("Jeff", "Todd", "Clare", "Ashley", "Pasq");
        for(String name:names){
            String customerEmail = name + "@gmail.com";
            HotelResource.createCustomer(customerEmail, name, "Tester");
        }
        System.out.println("TEST CUSTOMERS CREATED\n");
    }
    public static void addTestReservations() {
        try{
            List<Customer> customers = new ArrayList<>(AdminResource.getAllCustomers());
            List<IRoom> rooms = new ArrayList<>(AdminResource.getAllRooms());
            String checkInDate = "01-01-2021";
            Date checkIn = new SimpleDateFormat("MM-dd-yyyy").parse(checkInDate);
            String checkOutDate = "01-02-2021";
            Date checkOut = new SimpleDateFormat("MM-dd-yyyy").parse(checkOutDate);
            for (IRoom room : rooms) {
                for (Customer customer:customers){
                    reserveARoom(customer, room, checkIn, checkOut, room.isFree());
                    checkIn = addDays(checkIn, 2);
                    checkOut = addDays(checkOut, 2);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("Test Reservations not created");
        }
        System.out.println("TEST RESERVATIONS CREATED");
    }
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
    public static void addRooms() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a room number");
        String roomNumber = scanner.nextLine();
        System.out.println("Please enter a room price");
        Double roomPrice = Double.valueOf(scanner.nextLine());
        System.out.println("Please enter a room type : (1) => SINGLE, (2) => DOUBLE");
        String roomTypeInput = scanner.nextLine();
        IRoom.RoomType roomType = null;
        switch (roomTypeInput) {
            case "1" -> roomType = IRoom.RoomType.SINGLE;
            case "2" -> roomType = IRoom.RoomType.DOUBLE;
            default -> System.out.println("Please enter a valid room type (1 or 2)");
        }
        System.out.println("Will this room be free? Y or N");
        String cost = scanner.nextLine();
        IRoom room = null;
        switch (cost.toUpperCase()) {
            case "Y" -> room = new FreeRoom(roomNumber, roomPrice, roomType, true);
            case "N" -> room = new Room(roomNumber, roomPrice, roomType, false);
            default -> System.out.println("Input incorrect. \nPlease enter Y or N");
        }
        if (!AdminResource.getAllRooms().contains(room)) {
            HotelResource.addRoom(room);
            System.out.println("Room added");
        }
        else{
            System.out.println("Room already exists");
        }
    }
}
