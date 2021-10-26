package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class ReservationService {
    Collection<Reservation> reservations = new HashSet<Reservation>();
    private static Reservation reservation = null;

    public static Reservation getInstance() {
        if (null == reservation) {
            reservation = new Reservation();
        }
        return reservation;
    }
    public void addRoom(IRoom room){

    }
    public IRoom getARoom(String roomId){
    return null;
    }
    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
    return null;
    }
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
    return null;
    }
    public Collection<Reservation> getCustomersReservation(Customer customer) {
    return null;
    }

}
