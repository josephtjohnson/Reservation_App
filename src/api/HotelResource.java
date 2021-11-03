package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static final HotelResource hotelResource = null;
    public static final CustomerService customerService = CustomerService.getInstance();
    public static final ReservationService reservationService = ReservationService.getInstance();

    public static HotelResource getInstance() {
        if(null == hotelResource){
            HotelResource hotelResource = new HotelResource();
        }
        return hotelResource;
    }

    public static Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }
    public static void createACustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);

    }
    public static IRoom getRoom(String roomNumber){
        return reservationService.getARoom(roomNumber);
    }
    public static void bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        reservationService.reserveARoom(customerService.getCustomer(customerEmail), room, checkInDate, checkOutDate);
    }
    public static Collection<Reservation> getAllCustomerReservations(String customerEmail) {
        return reservationService.getCustomersReservation(customerService.getCustomer(customerEmail));
    }
    public static Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate, boolean isFree) {
        return reservationService.findRooms(checkInDate, checkOutDate, isFree);
    }
}
