package by.lugovskoi.entity;

public class AllIncludedNumber extends Number {

    private boolean allowSmoke;

    private boolean allowPets;

    public AllIncludedNumber(int id, int roomsCount, RoomClass roomClass){
        super(id, roomsCount, roomClass);
        this.allowSmoke = true;
        this.allowPets = true;
    }

    public boolean isAllowPets() {
        return allowPets;
    }

    public boolean isAllowSmoke() {
        return allowSmoke;
    }

}
