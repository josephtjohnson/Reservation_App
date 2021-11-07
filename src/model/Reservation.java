package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {
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
    return "\nReservation: " + customer + room + " " + "Dates: " + checkInDate + " - " + checkOutDate;
  }
}
