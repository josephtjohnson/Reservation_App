public class Room implements IRoom{
    private String roomNumber;
    private Double roomPrice;
    private RoomType roomType;
    private boolean isFree;

    @Override
    public String getRoomNumber() {
        return null;
    }

    @Override
    public Double getRoomPrice() {
        return null;
    }

    @Override
    public RoomType getRoomType() {
        return null;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public String toString() {
        return "--Room: " + roomNumber  + "--Price: " + roomPrice + "--Type: " + roomType + "--Free: " + isFree;
    }
}
