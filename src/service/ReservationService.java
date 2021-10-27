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
        System.out.println("Room not found. Please verify room number and try again.");
        return null;
    }
    public void reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
    Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
    reservations.add(reservation);
    }
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        Collection<IRoom> roomList = new ArrayList<>();
        roomList = rooms;
        for (Reservation reservation : reservations) {
            for (IRoom room : rooms) {
                if((reservation.getCheckInDate().before(checkInDate)
                    && reservation.getCheckOutDate().after(checkOutDate)
                    && reservation.getRoom().equals(room))
                    || (reservation.getCheckInDate().after(checkInDate)
                    && reservation.getCheckOutDate().before(checkOutDate)
                    && reservation.getRoom().equals(room))) {
                    roomList.remove(room);
                }
            }
        }
        return roomList;
    }
    public Collection<Reservation> getCustomersReservation(Customer customer) {
        Collection<Reservation> customerReservations = new ArrayList<>();
        customerReservations = reservations;
        for (Reservation reservation : reservations){
            if(reservation.getCustomer() != customer) {
                customerReservations.remove(reservation);
            }
            return customerReservations;
        }
    return null;
    }

}
