package by.lugovskoi.entity;

public class Number {

    private int id;

    private int roomsCount;

    private RoomClass roomClass;

    private boolean booked;

    public Number(int id, int roomsCount, RoomClass roomClass, boolean booked) {
        this.id = id;
        this.roomsCount = roomsCount;
        this.roomClass = roomClass;
        this.booked = booked;
    }

    public Number(int roomsCount, RoomClass roomClass, boolean booked) {
        this.roomsCount = roomsCount;
        this.roomClass = roomClass;
        this.booked = booked;
    }

    public void setRoomClass(RoomClass roomClass) {
        this.roomClass = roomClass;
    }

    public void setMachRooms(int sumRooms) {
        this.roomsCount = sumRooms;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoomClass getRoomClass() {
        return roomClass;
    }

    public int getMachRooms() {
        return roomsCount;
    }

    public int getId() {
        return id;
    }

    public int getRoomsCount() {
        return roomsCount;
    }

    public void setRoomsCount(int roomsCount) {
        this.roomsCount = roomsCount;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public boolean isBooked() {
        return booked;
    }
}
