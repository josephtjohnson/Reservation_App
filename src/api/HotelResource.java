package api;

import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    public CustomerService getCustomer(String email) {
        
    }
    public void createACustomer(String email, String firstName, String lastName) {

    }
    public IRoom getRoom(String roomNumber){

    }
    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {

    }
    public Collection<Reservation> getCustomerReservations(String customerEmail) {

    }
    public Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate) {

    }
}
