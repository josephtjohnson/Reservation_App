package model;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Customer{
  private String firstName;
  private String lastName;
  private String email;
  
  public Customer(String firstName, String lastName, String email){
    boolean keepRunning = true;
    String emailRegex = "^(.+)@(.+).(.+)$";
    Pattern pattern = Pattern.compile(emailRegex);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    while (keepRunning){
        if(pattern.matcher(this.email).matches()) {
          keepRunning = false;
        }
        else {
          try {
            throw new IllegalArgumentException("Invalid email format (ex. j@email.com). ");
          }
          catch(IllegalArgumentException e) {
            System.out.println(e + "Please enter a valid email address: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            this.email = input;
          }
        }
    }
    System.out.println("Customer information saved.");
  }
  public String getFirstName() {
    return firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public String getEmail() {
    return email;
  }
  @Override
  public String toString(){
    return "Customer Account Information: " + firstName + " " + lastName + " " + email;
  }
}
