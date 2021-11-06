package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import service.CustomerService;
import service.ReservationService;
import java.util.Collection;
import java.util.Date;

import static service.CustomerService.*;
import static service.ReservationService.findRooms;

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

    public static Customer retrieveCustomer(String email) { return getCustomer(email);
    }

    public static Collection<Customer> getAllCustomers() {return getCustomers();}

    public static void createACustomer(String email, String firstName, String lastName) {
        addCustomer(email, firstName, lastName);

    }
    public static IRoom getRoom(String roomNumber){
        return reservationService.getARoom(roomNumber);
    }

    public static void bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        reservationService.reserveARoom(getCustomer(customerEmail), room, checkInDate, checkOutDate);
    }
    public static Collection<Reservation> getAllCustomerReservations(Customer customer) {
        return reservationService.getCustomersReservation(customer);
    }
    public static void findARoom(Date checkInDate, Date checkOutDate, boolean isFree) {
        findRooms(checkInDate, checkOutDate, isFree);
    }
}
