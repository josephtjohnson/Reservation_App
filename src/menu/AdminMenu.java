package menu;

import api.AdminResource;
import api.HotelResource;
import model.IRoom;
import model.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
                System.out.println("5. Back to main menu");
                String userInput = scanner.nextLine();
                System.out.println("Option selected: " + userInput);
                int selection = Integer.parseInt(userInput);

                if (selection == 1) {
                    AdminResource.getAllCustomers();
                }
                if (selection == 2) {
                    AdminResource.getAllRooms();
                }
                if (selection == 3) {
                    AdminResource.displayAllReservations();
                }
                if (selection == 4) {
                    Scanner addRoom = new Scanner(System.in);
                    System.out.println("Please enter a room number");
                    String roomNumber = scanner.nextLine();
                    System.out.println("Please enter a room price");
                    Double roomPrice = Double.valueOf(scanner.nextLine());
                    System.out.println("Please enter a room type : (1) => SINGLE, (2) => DOUBLE");
                    String roomTypeInput = scanner.nextLine();
                    switch (roomTypeInput){
                        case "1":
                            roomType = IRoom.RoomType.SINGLE;
                            break;
                        case "2":
                            roomType = IRoom.RoomType.DOUBLE:
                            break;
                        default:
                            System.out.println("Please enter a valid room type (1 or 2)")
                    }
                    List<IRoom> newRooms = new ArrayList<>();
                    IRoom room = new Room(roomNumber, roomPrice, roomType, false);
                    newRooms.add(room);
                    AdminResource.addRoom(newRooms);
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
