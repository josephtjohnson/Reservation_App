package model;

import java.util.Objects;

public class Room implements IRoom{
    private final String roomNumber;
    private final Double roomPrice;
    private final RoomType roomType;
    private final  boolean isFree;

    public Room(String roomNumber, Double roomPrice, RoomType roomType, boolean isFree){
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.roomType = roomType;
        this.isFree = false;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return roomPrice;
    }

    @Override
    public RoomType getRoomType() { return roomType; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return isFree == room.isFree && Objects.equals(roomNumber, room.roomNumber) && Objects.equals(roomPrice, room.roomPrice) && roomType == room.roomType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, roomPrice, roomType, isFree);
    }

    @Override
    public boolean isFree() { return isFree; }

    @Override
    public String toString() {
        return "Room: " + roomNumber  + " Price: " + roomPrice + " Type: " + roomType;
    }
}
