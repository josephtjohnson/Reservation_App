package service;

import model.*;
import java.util.*;
import static api.AdminResource.getAllRooms;

public class ReservationService {
    private static ReservationService reservationService;
    public static HashMap<String,ArrayList<Reservation>> reservations = new HashMap<>();
    public static Collection<IRoom> roomList = new ArrayList<>();
    public static ReservationService getInstance() {
        if (null == reservationService) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    public void addRoom(IRoom room){

        roomList.add(room);
        System.out.println(room);
    }

    public IRoom getARoom(String roomNumber){
        for(IRoom room : roomList){
            if(room.getRoomNumber().equals(roomNumber)){
                return room;
            }
        }
        System.out.println("Room not found. Please verify room number and try again.");
        return null;
    }
    public Collection<IRoom> getRooms(){
        return roomList;
    }
    public void reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate, boolean isFree) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate, isFree);
        String mapKey = (customer.getFirstName() + " " + customer.getLastName());
        if (!reservations.containsKey(mapKey)) {
            reservations.putIfAbsent(mapKey, new ArrayList<>());
        }
        reservations.get(mapKey).add(reservation);
        System.out.println(reservation);
    }
    public Collection<IRoom> findAvailableRooms(Date checkInDate, Date checkOutDate) {
        Collection<IRoom> availableRooms = new ArrayList<>(); //store rooms that are available, i.e. not reserved
        for (IRoom room : roomList){
            if (!isRoomReserved(room, checkInDate, checkOutDate)){
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }
    public boolean isRoomReserved(IRoom room, Date checkInDate, Date checkOutDate){
        if (reservations.isEmpty()) return false; //if no reservation has been made, then all rooms are free
        for (ArrayList<Reservation> reservations : reservations.values()) {
            for (Reservation reservation : reservations){
                IRoom reservedRoom = reservation.getRoom();
                if (reservedRoom.getRoomNumber().equals(room.getRoomNumber())){
                    // if the room has been reserved but the new date is not within the reserved room's date, then it is free.
                    if (!isDateWithinRange(checkInDate, checkOutDate, reservation)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public boolean isDateWithinRange (Date checkInDate, Date checkOutDate, Reservation reservation) {
        return !(checkInDate.before(reservation.getCheckOutDate()) || checkOutDate.after(reservation.getCheckInDate()));
    }
    public void printAllReservations() {
        for (String reservation : reservations.keySet()) {
            System.out.println(reservations.get(reservation));
        }
    }
    public void roomList() {
        List<IRoom> rooms = new ArrayList<>(getAllRooms());
        for(IRoom room:rooms){
            System.out.println(room);
        }
    }
    public ArrayList getCustomerReservations (String customer) {
        ArrayList<ArrayList<Reservation>> customerReservations = new ArrayList<>();
        for (String reservation : reservations.keySet()) {
            if (reservation.equalsIgnoreCase(customer)) {
                ArrayList<Reservation> allReservations = reservations.get(reservation);
                for (ArrayList<Reservation> value : allReservations) {
                    customerReservations.add(value);
                }
            }
        }
        return customerReservations;
    }

}