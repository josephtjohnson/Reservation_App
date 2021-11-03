package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.util.*;

public class ReservationService {
    private static ReservationService reservationService;
    public Collection<Reservation> reservations = new HashSet<>();
    public Collection<IRoom> roomList = new ArrayList<>();

    public static ReservationService getInstance() {
        if (null == reservationService) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    public void addRoom(IRoom room){
        roomList.add(room);
    }

    public IRoom getARoom(String roomId){
        for(IRoom room : roomList){
            if(room.getRoomNumber().equals(roomId)){
                return room;
            }
        }
        System.out.println("Room not found. Please verify room number and try again.");
        return null;
    }
    public Collection<IRoom> getRooms(){
        return roomList;
    }
    public void reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
    Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
    reservations.add(reservation);
    }
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate, boolean isFree){
        List<IRoom> tempRoomList = new ArrayList<>(roomList);
        for (Reservation reservation : reservations) {
            for (IRoom room : roomList) {
                if((reservation.getCheckInDate().before(checkInDate)
                    && reservation.getCheckOutDate().after(checkOutDate)
                    && reservation.getRoom().equals(room))
                    || (reservation.getCheckInDate().after(checkInDate)
                    && reservation.getCheckOutDate().before(checkOutDate)
                    && reservation.getRoom().equals(room))) {
                    tempRoomList.remove(room);
                }
                if(reservation.getIsFree() != isFree) {
                    tempRoomList.remove(room);
                }
            }
        }
        return tempRoomList;
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
        System.out.println("No reservations found for this guest.");
        return null;
    }
    public void printAllReservations() {
        for(Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }
}
