package model;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Customer{
  private final String firstName;
  private final String lastName;
  private String email;
  
  public Customer(String firstName, String lastName, String email){
    validateEmail(email);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }
  public void validateEmail (String email) throws IllegalArgumentException {
    String emailRegex = "^(.+)@(.+).(.+)$";
    Pattern pattern = Pattern.compile(emailRegex);
    if (!pattern.matcher(email).matches()) throw new IllegalArgumentException("Email is not valid");
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
