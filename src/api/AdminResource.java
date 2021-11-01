package api;

import model.Customer;
import model.IRoom;
import model.Room;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

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
        return customerService.getCustomer(email);
    }
    public void addRoom(List<IRoom> rooms){
        for (IRoom room : rooms) {
            reservationService.addRoom(room);
        }
    }
    public Collection<IRoom> getAllRooms(){
        return reservationService.getRooms();
    }
    public Collection<Customer> getAllCustomers(){
        return customerService.getCustomers();
    }
    public void displayAllReservations(){
        reservationService.printAllReservations();
    }
}
