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
                        findARoom();
                        System.out.println("\n");
                        break;

                    case 2:
                        getAllCustomerReservations();
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
    }
