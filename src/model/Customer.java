package model;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Customer{
  private final String firstName;
  private final String lastName;
  private String email;
  
  public Customer(String firstName, String lastName, String email){
    boolean keepRunning = true;
    String emailRegex = "^(.+)@(.+).(.+)$";
    Pattern pattern = Pattern.compile(emailRegex);
    this.firstName = firstName;
    while (keepRunning){
      if(firstName != null) {
        keepRunning = false;
      }
      else {
        try {
          throw new IllegalArgumentException("First name cannot be empty. ");
        }
        catch(IllegalArgumentException e) {
          System.out.println(e + "\nPlease enter a customer first name. ");
          Scanner scanner = new Scanner(System.in);
          firstName = scanner.next();
        }
      }
    }
    keepRunning = true;
    this.lastName = lastName;
    while (keepRunning){
      if(lastName != null) {
        keepRunning = false;
      }
      else {
        try {
          throw new IllegalArgumentException("Last name cannot be empty. ");
        }
        catch(IllegalArgumentException e) {
          System.out.println(e + "\nPlease enter a customer last name. ");
          Scanner scanner = new Scanner(System.in);
          lastName = scanner.next();
        }
      }
    }
    keepRunning = true;
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
            System.out.println(e + "\nPlease enter a valid email address: ");
            Scanner scanner = new Scanner(System.in);
            this.email = scanner.next();
          }
        }
    }
  }
  public String getFirstName() {
    return firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public String getFullName() { return firstName + " " + lastName;}
  public String getEmail() {
    return email;
  }
  @Override
  public String toString(){
    return firstName + " " + lastName + " " + email + " ";
  }
}
