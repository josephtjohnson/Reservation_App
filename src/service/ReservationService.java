package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.util.*;

public class ReservationService {
    private static ReservationService reservationService;
    public static Collection<Reservation> reservations = new HashSet<>();
    public static Collection<IRoom> roomList = new ArrayList<>();
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
    public static Collection<IRoom> getRooms(){
        return roomList;
    }
    public void reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
    Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
    reservations.add(reservation);
    }
    public static void findRooms(Date checkInDate, Date checkOutDate, boolean isFree){
        for (Reservation reservation : reservations) {
            for (model.IRoom room : getRooms()) {
                if((reservation.getCheckInDate().before(checkInDate)
                    && reservation.getCheckOutDate().after(checkOutDate)
                    && reservation.getRoom().equals(room))
                    && room.isFree() == true
                    || (reservation.getCheckInDate().after(checkInDate)
                    && reservation.getCheckOutDate().before(checkOutDate)
                    && reservation.getRoom().equals(room))
                    && room.isFree() == true) {
                    System.out.println(room);
                }
                else {
                    System.out.println("No rooms available");
                }
                }
            System.out.println("Room search complete");
            }
        }
    public static Collection<Reservation> getCustomersReservation(Customer customer) {
        Collection<Reservation> customerReservations = new ArrayList<>();
        Collection<Customer> customers = new ArrayList<>(CustomerService.getCustomers());
        for (Reservation reservation : reservations){
            if(reservation.getCustomer().getEmail().equals(customer.getEmail())) {
                customerReservations.add(reservation);
            }
            return customerReservations;
        }
        System.out.println("No reservations found for this guest.");
        return null;
    }
    public Collection<Reservation> getReservations(){
        return reservations;
    }
    public void printAllReservations() {
        for(Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }
}
