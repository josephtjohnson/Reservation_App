package model;

public class Tester {

  public static void main(String[] args) {
    testFunction();
    testBadEmail();
    testBadFirstName();
    testBadLastName();
  }
  public static void testFunction() {
    Customer customer = new Customer("first", "second", "j@domain.com");
    System.out.println(customer); // should match format for toString() override in class model.Customer

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

}
