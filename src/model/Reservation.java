package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Reservation extends ArrayList{
  private final Customer customer;
  private final IRoom room;
  private final Date checkInDate;
  private final Date checkOutDate;
  private final boolean isFree;

  public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate, boolean isFree) {
    this.customer = customer;
    this.room = room;
    this.checkInDate = checkInDate;
    this.checkOutDate = checkOutDate;
    this.isFree = isFree;
  }
  public Customer getCustomer() { return customer; }

  public IRoom getRoom() { return room; }

  public Date getCheckInDate() { return checkInDate; }

  public Date getCheckOutDate() { return checkOutDate; }

  public boolean getIsFree() { return isFree; }


  @Override
  public String toString() {
    return customer.getFullName() + " -- " + room + " " + "Dates: " + checkInDate + " - " + checkOutDate;
  }
  
  @Override
  public int hashCode() {
    int hash = 7;
    hash =  31 * hash + Objects.hashCode(this.customer) + Objects.hashCode(this.room) + Objects.hashCode(this.checkInDate) + Objects.hashCode(this.checkOutDate) + Objects.hashCode(this.isFree)
    return hash;
  }
  
  @Override
  public boolean equals (Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (obj instanceof Reservation) {
      Reservation test = (Reservation) obj;
      if (test.customer == this.customer && test.room == this.room && test.checkInDate == this.checkInDate 
          && test.checkOutDate == this.checkOutDate && test.isFree == this.isFree) {
        return true;
      }
    }
    return false;
  }
  
}
