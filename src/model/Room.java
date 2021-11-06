package model;

public class Room implements IRoom{
    private final String roomNumber;
    private final Double roomPrice;
    private final RoomType roomType;
    public boolean isFree;

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
    public boolean isFree() { return isFree; }

    @Override
    public String toString() {
        return "--Room Number: " + roomNumber  + " --Price: " + roomPrice + " --Type: " + roomType;
    }
}
