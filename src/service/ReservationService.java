package service;

import model.*;

import java.text.SimpleDateFormat;
import java.util.*;

import static api.AdminResource.getAllRooms;
import static service.CustomerService.*;

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

    public static void addRoom(IRoom room){

        roomList.add(room);
        System.out.println(room);
    }

    public static IRoom getARoom(String roomId){
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
    public static void reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate, boolean isFree) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate, isFree);
        String mapKey = (customer.getFirstName() + " " + customer.getLastName());
        if (!reservations.containsKey(mapKey)) {
            reservations.putIfAbsent(mapKey, new ArrayList<>());
        }
        reservations.get(mapKey).add(reservation);
        System.out.println(reservation);
    }
    public static Collection<IRoom> findAvailableRooms(Date checkInDate, Date checkOutDate) {
        Collection<IRoom> availableRooms = new ArrayList<>(); //store rooms that are available, i.e. not reserved
        for (IRoom room : roomList){
            if (!isRoomReserved(room, checkInDate, checkOutDate)){
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }
    public static boolean isRoomReserved(IRoom room, Date checkInDate, Date checkOutDate){
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
    public static boolean isDateWithinRange (Date checkInDate, Date checkOutDate, Reservation reservation) {
        return !(checkInDate.before(reservation.getCheckOutDate()) || checkOutDate.after(reservation.getCheckInDate()));
    }

    public static void getCustomersReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter customer Email:" );
        String email = scanner.next();
        String customer = getCustomer(email).getFullName();
        for (String reservation : reservations.keySet()) {
            if (reservation.equalsIgnoreCase(customer)) {
                ArrayList<Reservation> allReservations = reservations.get(reservation);
                for (ArrayList<Reservation> value: allReservations) {
                    System.out.println(value);
                }
            }
        }

    }
    public static void printAllReservations() {
        for (String reservation : reservations.keySet()) {
            System.out.println(reservations.get(reservation));
        }
    }

    public static void createCustomer() {
        Scanner newCustomer = new Scanner(System.in);
        System.out.println("Enter customer email: ");
        String customerEmail = newCustomer.nextLine();
        System.out.println("Enter customer first name: ");
        String customerFirstName = newCustomer.nextLine();
        System.out.println("Enter customer last name: ");
        String customerLastName = newCustomer.nextLine();
        addCustomer(customerEmail, customerFirstName, customerLastName);
    }
    public static void findARoom() {
        Date checkIn = null;
        Date checkOut = null;
        var isFree = false;
        try {
            Scanner dates = new Scanner(System.in);
            System.out.println("Enter check-in date: mm-dd-yyyy");
            String checkInDate = dates.nextLine();
            System.out.println("Check-in Date: " + checkInDate);
            checkIn = new SimpleDateFormat("MM-dd-yyyy").parse(checkInDate);
            System.out.println("Enter check-out date: mm-dd-yyyy");
            String checkOutDate = dates.nextLine();
            System.out.println("Check-out Date: " + checkOutDate);
            checkOut = new SimpleDateFormat("MM-dd-yyyy").parse(checkOutDate);
            System.out.println("Will this room be free? Y or N");
            String cost = dates.nextLine();
            switch (cost.toUpperCase()) {
                case "Y":
                    isFree = true;
                    break;
                case "N":
                    break;
                default:
                    System.out.println("Input incorrect. \nPlease enter Y or N");
            }
        } catch (Exception e) {
            System.out.println(e);
            System.err.println("Input incorrect. \nPlease enter Y or N");
        }
        System.out.println("Book a room? Y or N");
        Scanner book = new Scanner(System.in);
        String reserve = book.nextLine();
        try {
            switch (reserve.toUpperCase()) {
                case "Y":
                    System.out.println("Here are all available hotel rooms");
                    System.out.println(findAvailableRooms(checkIn, checkOut));
                    System.out.println("Enter room number");
                    String roomNumber = book.nextLine();
                    System.out.println("Enter customer email");
                    String customerEmail = book.nextLine();
                    reserveARoom(getCustomer(customerEmail), getARoom(roomNumber), checkIn, checkOut, isFree);
                    break;
                case "N":
                    break;
                default:
                    System.out.println("Input incorrect. \nPlease enter Y or N");
            }
        } catch (Exception e) {
            System.out.println(e);
            System.err.println("Input incorrect. \nPlease enter Y or N");
        }
    }
    public static void roomList() {
        List<IRoom> rooms = new ArrayList<>(getAllRooms());
        for(IRoom room:rooms){
            System.out.println(room);
        }
    }
    public static void addRooms() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a room number");
        String roomNumber = scanner.nextLine();
        System.out.println("Please enter a room price");
        Double roomPrice = Double.valueOf(scanner.nextLine());
        System.out.println("Please enter a room type : (1) => SINGLE, (2) => DOUBLE");
        String roomTypeInput = scanner.nextLine();
        IRoom.RoomType roomType = null;
        switch (roomTypeInput) {
            case "1" -> roomType = IRoom.RoomType.SINGLE;
            case "2" -> roomType = IRoom.RoomType.DOUBLE;
            default -> System.out.println("Please enter a valid room type (1 or 2)");
        }
        System.out.println("Will this room be free? Y or N");
        String cost = scanner.nextLine();
        IRoom room = null;
        switch (cost.toUpperCase()) {
            case "Y" -> room = new FreeRoom(roomNumber, roomPrice, roomType, true);
            case "N" -> room = new Room(roomNumber, roomPrice, roomType, false);
            default -> System.out.println("Input incorrect. \nPlease enter Y or N");
        }
        if (!roomList.contains(room)) {
            addRoom(room);
            System.out.println("Room added");
        }
        else{
            System.out.println("Room already exists");
        }
    }
    public static void addTestRooms(){
        List<IRoom> testRooms = new ArrayList<>();
        for(int i = 1; i < 10; i++) {
            String roomNumber = String.valueOf(i);
            if (i % 2 == 0){
                Double roomPrice = (double) i;
                IRoom room = new Room(roomNumber, roomPrice, IRoom.RoomType.SINGLE, false);
                if(!testRooms.contains(room)) {
                    testRooms.add(room);
                    addRoom(room);
                }
            }
            else {
                Double roomPrice = 0.0;
                IRoom room = new FreeRoom(roomNumber, roomPrice, IRoom.RoomType.DOUBLE, true);
                if(!testRooms.contains(room)) {
                    testRooms.add(room);
                    addRoom(room);

                }
            }
        }
        System.out.println("TEST ROOMS CREATED\n");
    }
    public static void addTestCustomers() {
        var names = List.of("Jeff", "Todd", "Clare", "Ashley", "Pasq");
        for(String name:names){
            String customerEmail = name + "@gmail.com";
            addCustomer(customerEmail, name, "Tester");
        }
        System.out.println("TEST CUSTOMERS CREATED\n");
    }
    public static void addTestReservations() {
        try{
            List<Customer> customers = new ArrayList<>(getCustomers());
            List<IRoom> rooms = new ArrayList<>(getRooms());
            String checkInDate = "01-01-2021";
            Date checkIn = new SimpleDateFormat("MM-dd-yyyy").parse(checkInDate);
            String checkOutDate = "01-02-2021";
            Date checkOut = new SimpleDateFormat("MM-dd-yyyy").parse(checkOutDate);
            for (IRoom room : rooms) {
                for (Customer customer:customers){
                    reserveARoom(customer, room, checkIn, checkOut, room.isFree());
                    checkIn = addDays(checkIn, 2);
                    checkOut = addDays(checkOut, 2);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("Test Reservations not created");
        }
    System.out.println("TEST RESERVATIONS CREATED");
    }
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
}