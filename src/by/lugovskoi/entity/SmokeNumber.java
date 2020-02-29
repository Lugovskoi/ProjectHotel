package by.lugovskoi.entity;

public class SmokeNumber extends Number {

    private boolean allowSmoke;

    public SmokeNumber(int id, int roomsCount, RoomClass roomClass, boolean booked){
        super(id, roomsCount, roomClass, booked);
        this.allowSmoke = true;
    }

    public boolean isAllowSmoke() {
        return allowSmoke;
    }
}
