package by.lugovskoi.entity;

public class PetNumber extends Number {

    private boolean allowPets;

    public PetNumber(int id, int roomsCount, RoomClass roomClass, boolean booked) {
        super(id, roomsCount, roomClass, booked);
        allowPets = true;
    }

    public boolean isAllowPets() {
        return allowPets;
    }
}