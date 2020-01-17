package by.lugovskoi.entity;

public class PetNumber extends Number {

    private boolean allowPets;

    public PetNumber(int id, int roomsCount, RoomClass roomClass) {
        super(id, roomsCount, roomClass);
        allowPets = true;
    }

    public boolean isAllowPets() {
        return allowPets;
    }
}