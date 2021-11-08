package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import service.CustomerService;
import service.ReservationService;

import java.util.*;

import static service.CustomerService.getCustomers;
import static service.ReservationService.getRooms;
import static service.ReservationService.reservations;

public class AdminResource {
    private static AdminResource adminResource = null;
    public static final Collection<IRoom> rooms = new HashSet<>();
    public static final CustomerService customerService = CustomerService.getInstance();
    public static final ReservationService reservationService = ReservationService.getInstance();

    public static AdminResource getInstance() {
        if(null == adminResource){
            AdminResource adminResource = new AdminResource();
        }
        return adminResource;
    }
    public Customer getCustomer(String email){
        return CustomerService.getCustomer(email);
    }
    public static void addRoom(List<IRoom> rooms){
        for (IRoom room : rooms) {
            reservationService.addRoom(room);
        }
    }
    public static Collection<IRoom> getAllRooms(){
        return getRooms();
    }
    public static Collection<Customer> getAllCustomers(){
        return getCustomers();
    }
    public static void displayAllReservations(){
        reservationService.printAllReservations();
    }
    public static void customerList() {
        CustomerService.customerList();
    }
    public static void roomList() {
        ReservationService.roomList();
    }
    public static void addRooms() {
        ReservationService.addRooms();
    }
    public static void addTestData() {
        ReservationService.addTestCustomers();
        ReservationService.addTestRooms();
        ReservationService.addTestReservations();
    }
}
