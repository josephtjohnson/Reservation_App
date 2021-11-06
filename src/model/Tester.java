package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tester {

  public static void main(String[] args) throws ParseException {
    testFunction();
    testBadEmail();
    testBadFirstName();
    testBadLastName();
    testReservation();
  }
  public static void testFunction() {
    Customer testCustomer = new Customer("first", "second", "j@domain.com");
    System.out.println(testCustomer); // should match format for toString() override in class model.Customer

    Room room = new Room("101", 10.0, IRoom.RoomType.SINGLE, false);
    System.out.println(room); // should match format for toString() override in class model.Room
    Room room2 = new Room("102", 10.0, IRoom.RoomType.SINGLE, true);
    System.out.println(room2); // should match format for toString() override in class model.Room
    FreeRoom room3 = new FreeRoom("103", 0.0, IRoom.RoomType.DOUBLE, true);
    System.out.println(room3); // should match format for toString() override in class model.FreeRoom
  }
  public static void testBadEmail() {
    Customer customer2 = new Customer("first", "second", "email"); // should throw IllegalArgumentException
  }
  public static void testBadFirstName() {
    Customer customer3 = new Customer(null, "second", "j@gmail.com"); // should throw IllegalArgumentException
  }
  public static void testBadLastName() {
    Customer customer4 = new Customer("Todd", null, "j@gmail.com"); // should throw IllegalArgumentException
  }
  public static void testReservation() throws ParseException {
    Customer customer5 = new Customer("John","Holiday","jholiday@test.com");
    Room room4 = new Room("201", 100.0, IRoom.RoomType.SINGLE, false);
    Date checkInDate = new SimpleDateFormat("MM-dd-yyyy").parse("11-06-2021");
    Date checkOutDate = new SimpleDateFormat("MM-dd-yyyy").parse("11-06-2021");
    Reservation reservation = new Reservation(customer5, room4, checkInDate, checkOutDate);
    System.out.println(reservation);
  }

}
