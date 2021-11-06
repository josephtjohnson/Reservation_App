package model;

public class FreeRoom extends Room{

    public FreeRoom(String roomNumber, Double roomPrice, RoomType roomType, boolean isFree) {
        super(roomNumber, 0.0, roomType, isFree);
    }
    @Override
    public String toString(){
        return super.toString() + " (Free room)";
    }
}
