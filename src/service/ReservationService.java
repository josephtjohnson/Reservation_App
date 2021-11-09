package service;

import api.AdminResource;
import api.HotelResource;
import model.*;

import java.text.SimpleDateFormat;
import java.util.*;

import static api.AdminResource.getAllRooms;
import static service.CustomerService.getCustomer;

public class ReservationService {
    private static ReservationService reservationService;
    public static HashMap<String,ArrayList<Reservation>> reservations = new HashMap<String,ArrayList<Reservation>>();
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
    public static boolean isRoomReserved(IRoom room, Date checkInDate, Date checkOutDate) {
        if(reservations.isEmpty()) { return false; }
        Collection<Reservation> reservedRooms = new ArrayList<Reservation>();
        for(ArrayList value : reservations.values()) {
            reservedRooms.add((Reservation) value);
        }
        for (Reservation reservation : reservedRooms) {
            IRoom reservedRoom = reservation.getRoom();
            if(room.getRoomNumber().equals(reservedRoom)) {
                if(checkOutDate.before(reservation.getCheckInDate()) || checkInDate.after(reservation.getCheckOutDate())) {
                    return false;
                }
            }
        } return true;

    }
    public static void reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate, boolean isFree) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate, isFree);
        String mapKey = (customer.getFirstName() + " " + customer.getLastName());
        reservations.put(mapKey, reservation);
    }
    public static void findARoom(Date checkInDate, Date checkOutDate) {
        Collection<IRoom> rooms = getRooms();
        Collection<Reservation> allReservations = new ArrayList<Reservation>();
        for (ArrayList value : reservations.values()) {
            allReservations.add((Reservation) value);
            for (Reservation reservation : allReservations){
                if (isRoomReserved(reservation.getRoom(), checkInDate, checkOutDate)) {
                    rooms.remove(reservation.getRoom());
            }
            }
        }
        Collection<IRoom> availableRooms = rooms.stream().toList();
        for (IRoom room:availableRooms) {
            System.out.println(room);
        }
    }

    public static void getCustomersReservation() {
        Collection<Collection> customerReservations = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter customer Email:" );
        String email = scanner.next();
        Customer customer = getCustomer(email);
        System.out.println(customer.getFullName());
        Iterator entries = reservations.entrySet().iterator();
        while(entries.hasNext()){
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            Reservation value = (Reservation) entry.getValue();
            if(key.equalsIgnoreCase(customer.getFullName())) {
                System.out.println(value);
            }
        }
    }
    public void printAllReservations() {
        reservations.forEach((key, value) -> System.out.println("\nCustomer Name: "+ key + " " + value));
        }

    public static void createCustomer() {
        Scanner newCustomer = new Scanner(System.in);
        System.out.println("Enter customer email: ");
        String customerEmail = newCustomer.nextLine();
        System.out.println("Enter customer first name: ");
        String customerFirstName = newCustomer.nextLine();
        System.out.println("Enter customer last name: ");
        String customerLastName = newCustomer.nextLine();
        HotelResource.createACustomer(customerEmail, customerFirstName, customerLastName);
    }
    public static void findARoom() {
        Date checkIn = null;
        Date checkOut = null;
        boolean isFree = false;
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
                    isFree = false;
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
                    findARoom(checkIn, checkOut);
                    System.out.println("Enter room number");
                    String roomNumber = book.nextLine();
                    System.out.println("Enter customer email");
                    String customerEmail = book.nextLine();
                    reserveARoom(getCustomer(customerEmail), getARoom(roomNumber), checkIn, checkOut, isFree);
                    System.out.println("Room reserved!");
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
            case "1":
                roomType = IRoom.RoomType.SINGLE;
                break;
            case "2":
                roomType = IRoom.RoomType.DOUBLE;
                break;
            default:
                System.out.println("Please enter a valid room type (1 or 2)");
        }
        System.out.println("Will this room be free? Y or N");
        String cost = scanner.nextLine();
        IRoom room = null;
        switch (cost.toUpperCase()) {
            case "Y":
                room = new FreeRoom(roomNumber, roomPrice, roomType, true);
                break;
            case "N":
                room = new Room(roomNumber, roomPrice, roomType, false);
                break;
            default:
                System.out.println("Input incorrect. \nPlease enter Y or N");
        }

        List<IRoom> newRooms = new ArrayList<>();
        newRooms.add(room);
        AdminResource.addRoom(newRooms);
    }
    public static void addTestRooms(){
        List<IRoom> testRooms = new ArrayList<>();
        for(int i = 1; i < 10; i++) {
            String roomNumber = String.valueOf(i);
            if (i % 2 == 0){
                Double roomPrice = Double.valueOf(i);
                IRoom room = new Room(roomNumber, roomPrice, IRoom.RoomType.SINGLE, false);
                if(!testRooms.contains(room)) {
                    testRooms.add(room);
                }
            }
            else {
                Double roomPrice = 0.0;
                IRoom room = new FreeRoom(roomNumber, roomPrice, IRoom.RoomType.DOUBLE, true);
                if(!testRooms.contains(room)) {
                    testRooms.add(room);
                }
            }
        }
        AdminResource.addRoom(testRooms);
    }
    public static void addTestCustomers() {
        var names = List.of("Jeff", "Todd", "Clare", "Ashley", "Pasq");
        for(String name:names){
            String customerEmail = name + "@gmail.com";
            String customerFirstName = name;
            String customerLastName = "Tester";
            HotelResource.createACustomer(customerEmail, customerFirstName, customerLastName);
        }
    }
    public static void addTestReservations() {
        try{
            List<Customer> customers = new ArrayList<>(AdminResource.getAllCustomers());
            List<IRoom> rooms = new ArrayList<>(AdminResource.getAllRooms());
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
    }
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
}


