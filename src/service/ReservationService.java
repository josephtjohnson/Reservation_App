package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.util.*;

public class ReservationService {
    private static ReservationService reservationService;
    public Collection<Reservation> reservations = new HashSet<>();
    public Collection<IRoom> rooms = new HashSet<>();

    public static ReservationService getInstance() {
        if (null == reservationService) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    public void addRoom(String roomNumber, Double roomPrice, IRoom.RoomType roomType, boolean isFree){
        IRoom room = new Room(roomNumber, roomPrice, roomType, isFree);
        rooms.add(room);
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

        return null;
    }
    public Collection<Reservation> getCustomersReservation(Customer customer) {
    return null;
    }

}
