package model;

import java.util.Date;

public class Reservation {
  private Customer customer;
  private IRoom room;
  private Date checkInDate;
  private Date checkOutDate;
  private boolean isFree;

  public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
    this.customer = customer;
    this.room = room;
    this.checkInDate = checkInDate;
    this.checkOutDate = checkOutDate;
  }
  public Customer getCustomer() {

    return customer;
  }
  public IRoom getRoom() {

    return room;
  }
  public Date getCheckInDate() {

    return checkInDate;
  }
  public Date getCheckOutDate(){

    return checkOutDate;
  }
  public boolean getIsFree(){
    
    return isFree;
    }

  @Override
  public String toString() {
    return "Reservation: "+ customer + " " + room + " " + " Dates: " + checkInDate + "-" + checkOutDate;
  }
}
