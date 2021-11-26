package menu;

import api.AdminResource;
import api.HotelResource;
import model.FreeRoom;
import model.IRoom;
import model.Room;
import java.util.*;

import static api.AdminResource.*;


public class AdminMenu {
    public static void main(String args){
        adminMenu();
    }
    public static void adminMenu() {
        final AdminResource adminResource = AdminResource.getInstance();
        Collection<IRoom> rooms = new ArrayList<>(adminResource.getAllRooms());
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
                        adminResource.customerList();
                        System.out.println("\n");
                        break;
                    case 2:
                        adminResource.roomList();
                        System.out.println("\n");
                        break;
                    case 3:
                        adminResource.displayAllReservations();
                        System.out.println("\n");
                        break;
                    case 4:
                        addRooms();
                        System.out.println("\n");
                        break;
                    case 5:
                        adminResource.addTestData();
                        System.out.println("\n*****************");
                        System.out.println("TEST DATA CREATED");
                        System.out.println("*****************\n");
                        break;
                    case 6:
                        keepRunning = false;
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
    public static void addRooms() {
        try {
            final AdminResource adminResource = AdminResource.getInstance();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter a room number");
            String roomNumber = scanner.nextLine();
            System.out.println("Please enter a room price");
            Double correctRoomPrice = null;
            try {
                correctRoomPrice = Double.valueOf(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input. Room price must be a number.");
                return;
            }
            Double roomPrice = correctRoomPrice;
            System.out.println("Please enter a room type : (1) => SINGLE, (2) => DOUBLE");
            String roomTypeInput = scanner.nextLine();
            IRoom.RoomType roomType = null;
            try {
                switch (roomTypeInput) {
                    case "1":
                        roomType = IRoom.RoomType.SINGLE;
                        break;
                    case "2":
                        roomType = IRoom.RoomType.DOUBLE;
                        break;
                    default:
                        System.out.println("Please enter a valid room type (1 or 2)");
                        return;
                }
            }
            catch (Exception e) {
                System.out.println("");
            }
            System.out.println("Will this room be free? Y or N");
            String cost = scanner.nextLine();
            IRoom room = null;
            try {
                switch (cost.toUpperCase()) {
                    case "Y":
                        room = new FreeRoom(roomNumber, roomPrice, roomType, true);
                        break;
                    case "N":
                        room = new Room(roomNumber, roomPrice, roomType, false);
                        break;
                    default:
                        System.out.println("Input incorrect. \nPlease enter Y or N");
                        return;
                }
            }
            catch (Exception e) {
                System.out.println("");
            }
            if (!adminResource.getAllRooms().contains(room)) {
                HotelResource.addRoom(room);
                System.out.println("Room added");
            } else {
                System.out.println("Room already exists");
            }
        }
        catch (Exception e) {
            System.out.println("");
        }
    }
}
