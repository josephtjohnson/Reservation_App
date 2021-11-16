package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;
import java.util.Collection;
import java.util.Date;
import static service.CustomerService.*;
import static service.ReservationService.*;

public class HotelResource {
    private static final HotelResource hotelResource = null;
    public static final CustomerService customerService = CustomerService.getInstance();
    public static final ReservationService reservationService = ReservationService.getInstance();

    public static HotelResource getInstance() {
        if (null == hotelResource) {
            HotelResource hotelResource = new HotelResource();
        }
        return hotelResource;
    }

    public static void getAllCustomerReservations() {
        getCustomersReservation();
    }

    public static void findARoom() {
        ReservationService.findARoom();
    }

    public static void createCustomer() {
        ReservationService.createCustomer();
    }
}
