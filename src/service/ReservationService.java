package service;

import api.HotelResource;
import model.*;
import java.text.SimpleDateFormat;
import java.util.*;
import static api.AdminResource.*;

public class ReservationService {
    private static ReservationService reservationService;
    public static HashMap<String, ArrayList<Reservation>> reservations = new HashMap<>();
    public static Collection<IRoom> roomList = new HashSet<>();

    public static ReservationService getInstance() {
        if (null == reservationService) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    public void addRoom(IRoom room) {
        if (!roomList.contains(room)) {
            roomList.add(room);
            System.out.println(room);
        }
        else {
            System.out.println("Room already in database");
        }
    }

    public IRoom getARoom(String roomNumber) {
        for (IRoom room : roomList) {
            if (room.getRoomNumber().equals(roomNumber)) {
                return room;
            }
        }
        System.out.println("Room not found. Please verify room number and try again.");
        return null;
    }

    public Collection<IRoom> getRooms() {
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
        for (IRoom room : roomList) {
            if (!isRoomReserved(room, checkInDate, checkOutDate)) {
                availableRooms.add(room);
            }
        }
    return availableRooms;
    }

    public boolean isRoomReserved(IRoom room, Date checkInDate, Date checkOutDate) {
        if (reservations.isEmpty()) return false; //if no reservation has been made, then all rooms are free
        for (ArrayList<Reservation> reservations : reservations.values()) {
            for (Reservation reservation : reservations) {
                IRoom reservedRoom = reservation.getRoom();
                if (reservedRoom.getRoomNumber().equals(room.getRoomNumber())) {
                    // if the room has been reserved but the new date is not within the reserved room's date, then it is free.
                    if (isDateWithinRange(checkInDate, checkOutDate, reservation)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isDateWithinRange(Date checkInDate, Date checkOutDate, Reservation reservation) {
        return !(checkOutDate.before(reservation.getCheckInDate()) || checkInDate.after(reservation.getCheckOutDate()));
    }

    public void printAllReservations() {
        for (String reservation : reservations.keySet()) {
            System.out.println(reservations.get(reservation));
        }
    }

    public void roomList() {
        List<IRoom> rooms = new ArrayList<>(roomList);
        for (IRoom room : rooms) {
            System.out.println(room);
        }
    }

    public void getCustomerReservations(String customer) {
        ArrayList<ArrayList<Reservation>> customerReservations = new ArrayList<>();
        for (String reservation : reservations.keySet()) {
            if (reservation.equalsIgnoreCase(customer)) {
                ArrayList<Reservation> allReservations = reservations.get(reservation);
                for (ArrayList<Reservation> value : allReservations) {
                    customerReservations.add(value);
                }
            }
        }
        for (ArrayList res : customerReservations) {
            System.out.println(res);
        }
    }

    public void addTestRooms() {
        List<IRoom> testRooms = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            String roomNumber = String.valueOf(i);
            if (i % 2 == 0) {
                Double roomPrice = (double) i;
                IRoom room = new Room(roomNumber, roomPrice, IRoom.RoomType.SINGLE, false);
                if (!testRooms.contains(room)) {
                    testRooms.add(room);
                    HotelResource.addRoom(room);
                }
            } else {
                Double roomPrice = 0.0;
                IRoom room = new FreeRoom(roomNumber, roomPrice, IRoom.RoomType.DOUBLE, true);
                if (!testRooms.contains(room)) {
                    testRooms.add(room);
                    HotelResource.addRoom(room);

                }
            }
        }
        System.out.println("TEST ROOMS CREATED\n");
    }

    public void addTestCustomers() {
        var names = List.of("Jeff", "Todd", "Clare", "Ashley", "Pasq");
        for (String name : names) {
            String customerEmail = name + "@gmail.com";
            HotelResource.createCustomer(customerEmail, name, "Tester");
        }
        System.out.println("TEST CUSTOMERS CREATED\n");
    }

    public void addTestReservations() {
        try {
            List<Customer> customers = new ArrayList<>(customerService.getCustomers());
            List<IRoom> rooms = new ArrayList<>(roomList);
            String checkInDate = "01-01-2021";
            Date checkIn = new SimpleDateFormat("MM-dd-yyyy").parse(checkInDate);
            String checkOutDate = "01-02-2021";
            Date checkOut = new SimpleDateFormat("MM-dd-yyyy").parse(checkOutDate);
            for (IRoom room : rooms) {
                for (Customer customer : customers) {
                    reservationService.reserveARoom(customer, room, checkIn, checkOut, room.isFree());
                    checkIn = addDays(checkIn, 2);
                    checkOut = addDays(checkOut, 2);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Test Reservations not created");
        }
        System.out.println("TEST RESERVATIONS CREATED");

    }
    Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
}
