package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static HotelResource hotelResource;
    public static final CustomerService customerService = CustomerService.getInstance();
    public static final ReservationService reservationService = ReservationService.getInstance();

    public static HotelResource getInstance() {
        if (null == hotelResource) {
            hotelResource = new HotelResource();
        }
        return hotelResource;
    }

    public static Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        return reservationService.findAvailableRooms(checkIn, checkOut);
    }

    public static void createCustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);
    }
    
    public static void bookARoom(String customerEmail, IRoom room, Date checkIn, Date checkOut, boolean isFree) {
        reservationService.reserveARoom(customerService.getCustomer(customerEmail), room, checkIn, checkOut, isFree);
    }
    
    public static IRoom getRoom(String roomNumber) { return reservationService.getARoom(roomNumber);}
    public static void addRoom(IRoom room) {reservationService.addRoom(room);}
    public static Customer getCustomer(String customerEmail) {return customerService.getCustomer(customerEmail);}
    public static void getCustomerReservations (String customer) {
        reservationService.getCustomerReservations(customer);}
}
