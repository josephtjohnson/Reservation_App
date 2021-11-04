package menu;

import api.AdminResource;
import api.HotelResource;
import model.*;
import service.CustomerService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static api.AdminResource.*;
import static api.HotelResource.bookARoom;

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
                        break;
                    case 2:
                        roomList();
                        break;
                    case 3:
                        displayAllReservations();
                        break;
                    case 4:
                        addRooms();
                    case 5:
                        addTestRooms();
                        addTestCustomers();
                        addTestReservations();
                        System.out.println("Test data created");
                        break;
                    case 6:
                        MainMenu.menu();
                        break;                
                    default:
                        System.out.println("Input incorrect. \nPlease enter a number 1 through 5");
                }
            }
            catch(Exception e) {
                System.out.println(e);
                System.out.println("Input incorrect. \nPlease enter a number 1 through 5");
            }
        }
    }
    public static void customerList(){
        List<Customer> customers = new ArrayList<>(getAllCustomers());
        for(Customer customer:customers){
            System.out.println(customer);
        }
    }
    public static void roomList() {
        List<IRoom> rooms = new ArrayList<>(getAllRooms());
        for(IRoom room:rooms){
            System.out.println(room);
        }
    }
    public static void addRooms(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a room number");
        String roomNumber = scanner.nextLine();
        System.out.println("Please enter a room price");
        Double roomPrice = Double.valueOf(scanner.nextLine());
        System.out.println("Please enter a room type : (1) => SINGLE, (2) => DOUBLE");
        String roomTypeInput = scanner.nextLine();
        IRoom.RoomType roomType = null;
        switch (roomTypeInput){
            case "1":
                roomType = IRoom.RoomType.SINGLE;
                break;
            case "2":
                roomType = IRoom.RoomType.DOUBLE;
                break;
            default:
                System.out.println("Please enter a valid room type (1 or 2)");
        }
        List<IRoom> newRooms = new ArrayList<>();
        IRoom room = new Room(roomNumber, roomPrice, roomType, false);
        newRooms.add(room);
        AdminResource.addRoom(newRooms);
    }
    public static void addTestRooms(){
        List<IRoom> testRooms = new ArrayList<>();
        for(int i = 1; i < 10; i++) {
            String roomNumber = String.valueOf(i);
            if (i % 2 == 0){
                Double roomPrice = Double.valueOf(i);
                IRoom room = new Room(roomNumber, roomPrice, IRoom.RoomType.SINGLE, false);
                if(!testRooms.contains(room)) {
                    testRooms.add(room);
                }
            }
            else {
                Double roomPrice = 0.0;
                IRoom room = new FreeRoom(roomNumber, roomPrice, IRoom.RoomType.DOUBLE, true);
                if(!testRooms.contains(room)) {
                    testRooms.add(room);
                }
            }
        }
        AdminResource.addRoom(testRooms);
    }
    public static void addTestCustomers() {
        var names = List.of("Jeff", "Todd", "Clare", "Ashley", "Pasq");
        for(String name:names){
            String customerEmail = name + "@gmail";
            String customerFirstName = name;
            String customerLastName = "Tester";
            HotelResource.createACustomer(customerEmail, customerFirstName, customerLastName);
        }
    }
    public static void addTestReservations() {
        try{
            List<Customer> customers = new ArrayList<>(AdminResource.getAllCustomers());
            List<IRoom> rooms = new ArrayList<>(AdminResource.getAllRooms());
            String checkInDate = "01-01-2021";
            Date checkIn = new SimpleDateFormat("MM-dd-yyyy").parse(checkInDate);
            String checkOutDate = "01-01-2021";
            Date checkOut = new SimpleDateFormat("MM-dd-yyyy").parse(checkOutDate);
            for(Customer customer:customers) {
                for (IRoom room : rooms) {
                        bookARoom(customer.getEmail(), room, checkIn, checkOut);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("Test Reservations not created");
        }
    }
}
