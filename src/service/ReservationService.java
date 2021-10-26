package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.util.*;

public class ReservationService {
    Collection<Reservation> reservations = new HashSet<Reservation>();
    Map<String,IRoom> rooms = new HashMap<>();

    private static Reservation reservation = null;

    public static Reservation getInstance() {
        if (null == reservation) {
            reservation = new Reservation();
        }
        return reservation;
    }

    public void addRoom(String roomNumber, Double roomPrice, IRoom.RoomType roomType, boolean isFree){
        IRoom room = new Room(roomNumber, roomPrice, roomType, isFree);
        rooms.put(roomNumber, room);
    }
    public IRoom getARoom(String roomId){
        for(IRoom room : rooms){
            if(room.getRoomNumber().equals(roomId)){
                return room;
            }
        }
        return null;
    }
    public void reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
    Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
    reservations.add(reservation);
    }
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
    return n;
    }
    public Collection<Reservation> getCustomersReservation(Customer customer) {
    return null;
    }

}
