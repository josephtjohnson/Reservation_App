package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.*;

public class AdminResource {
    private static final AdminResource adminResource = null;
    public static final Collection<IRoom> rooms = new HashSet<>();
    public static final CustomerService customerService = CustomerService.getInstance();
    public static final ReservationService reservationService = ReservationService.getInstance();

    public static AdminResource getInstance() {
        if(null == adminResource){
            AdminResource adminResource = new AdminResource();
        }
        return adminResource;
    }
    public static Collection<IRoom> getAllRooms(){
        return reservationService.getRooms();
    }
    public static Collection<Customer> getAllCustomers(){
        return customerService.getCustomers();
    }
    public static void displayAllReservations(){
        reservationService.printAllReservations();
    }
    public static void customerList() { customerService.customerList(); }
    public static void roomList() {
        reservationService.roomList();
    }
    public static void reserveARoom(Customer customer, IRoom room, Date checkIn, Date checkOut, boolean isFree) {
        reservationService.reserveARoom(customer, room, checkIn, checkOut, isFree);
    }
}
