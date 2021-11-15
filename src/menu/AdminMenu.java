package menu;

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
                        addTestData();
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

}
