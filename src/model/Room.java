package model;

public class Room implements IRoom{
    private String roomNumber;
    private Double roomPrice;
    private RoomType roomType;
    private boolean isFree;

    public Room(String roomNumber, Double roomPrice, RoomType roomType, boolean isFree){
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.roomType = roomType;
        this.isFree = isFree;
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
    public RoomType getRoomType() {

        return roomType;
    }

    @Override
    public boolean isFree() {

        return false;
    }

    @Override
    public String toString() {
        return "--model.Room: " + roomNumber  + "--Price: " + roomPrice + "--Type: " + roomType;
    }
}
