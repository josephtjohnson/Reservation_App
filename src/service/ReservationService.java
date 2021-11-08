package service;

import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.SimpleDateFormat;
import java.util.*;

import static api.HotelResource.getAllCustomerReservations;

public class ReservationService {
    private static ReservationService reservationService;
    public static HashMap<String,Reservation> reservations = new HashMap<>();
    public static Collection<IRoom> roomList = new ArrayList<>();
    public static ReservationService getInstance() {
        if (null == reservationService) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    public void addRoom(IRoom room){
        roomList.add(room);
    }

    public IRoom getARoom(String roomId){
        for(IRoom room : roomList){
            if(room.getRoomNumber().equals(roomId)){
                return room;
            }
        }
        System.out.println("Room not found. Please verify room number and try again.");
        return null;
    }
    public static Collection<IRoom> getRooms(){
        return roomList;
    }
    public void reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate, boolean isFree) {
    Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate, isFree);
    reservations.put(customer.getFirstName() + " " + customer.getLastName(), reservation);
    }
    static boolean dateInRange(Date checkInDate, Date checkOutDate, Reservation reservation) {
        return checkInDate.before(reservation.getCheckOutDate()) || checkOutDate.after(reservation.getCheckInDate());
    }
    public static void findARoom(Date checkInDate, Date checkOutDate) {
        Collection<IRoom> rooms = getRooms();
        for (Reservation value : reservations.values()) {
            if (dateInRange(checkInDate, checkOutDate, value)) {
                rooms.remove(value.getRoom());
            }
        }
        Collection<IRoom> availableRooms = rooms.stream().toList();
        for (IRoom room:availableRooms) {
            System.out.println(room);
        }
    }

    public static Collection<Collection> getCustomersReservation(Customer customer) {
        Collection<Collection> customerReservations = new ArrayList<>();
        for (String key : reservations.keySet()){
            if(key.equals(customer.getFullName())) {
                customerReservations.add(reservations.values());
            }
            return customerReservations;
        }
        System.out.println("No reservations found for this guest.");
        return null;
    }
    public void printAllReservations() {
        reservations.forEach((key, value) -> System.out.println("\nCustomer Name: "+ key + value));
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
}


