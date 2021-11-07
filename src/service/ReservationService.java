package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private static ReservationService reservationService;
    public static HashMap<String,Reservation> reservations = new HashMap<>();
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
    public void reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate, boolean isFree) {
    Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate, isFree);
    reservations.put(customer.getFirstName() + " " + customer.getLastName(), reservation);
    }
    static boolean dateInRange(Date checkInDate, Date checkOutDate, Reservation reservation) {
        return checkInDate.before(reservation.getCheckOutDate()) || checkOutDate.after(reservation.getCheckInDate());
    }
    public static void findARoom(Date checkInDate, Date checkOutDate) {
        Collection<IRoom> rooms = getRooms();
        for (Reservation value : reservations.values()) {
            if (dateInRange(checkInDate, checkOutDate, value)) {
                rooms.remove(value.getRoom());
            }
        }
        Collection<IRoom> availableRooms = rooms.stream().toList();
        for (IRoom room:availableRooms) {
            System.out.println(room);
        }
    }

    public static Collection<Collection> getCustomersReservation(Customer customer) {
        Collection<Collection> customerReservations = new ArrayList<>();
        for (String key : reservations.keySet()){
            if(key.equals(customer.getFullName())) {
                customerReservations.add(reservations.values());
            }
            return customerReservations;
        }
        System.out.println("No reservations found for this guest.");
        return null;
    }
    public void printAllReservations() {
        reservations.forEach((key, value) -> System.out.println("\nCustomer Name: "+ key + value));
        }
    }

