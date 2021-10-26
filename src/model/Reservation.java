package model;

import java.util.Date;

public class Reservation {
  private Customer customer;
  private IRoom room;
  private Date checkInDate;
  private Date checkOutDate;
  
  public Reservation() {
    this.customer = customer;
    this.room = room;
    this.checkInDate = checkInDate;
    this.checkOutDate = checkOutDate;
  }
  
  @Override
  public String toString() {
    return "model.Reservation: "+ customer + "" + room + "" + "Dates: " + checkInDate + "-" + checkOutDate;
  }
}
