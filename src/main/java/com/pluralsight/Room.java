package com.pluralsight;

public class Room {

    // ! Fields that define what a Room is / contains
    private String roomName;
    private int numberOfBeds;
    private double price;
    private boolean occupied;
    private boolean dirty;

    //! creating a public constructor to set the value of each backing variable
    public Room(String roomName, int numberOfBeds, double price, boolean occupied, boolean dirty) {
        this.roomName = roomName;
        this.numberOfBeds = numberOfBeds;
        this.price = price;
        this.occupied = occupied;
        this.dirty = dirty;
    }

    //!getters

    public String getRoomName() {
        return roomName;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public double getPrice() {
        return price;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public boolean isDirty() {
        return dirty;
    }

    public boolean isAvailable() {
        return !dirty && !occupied;
    }

    //? Checks a guest in if the room is clean and empty.
    //? Returns true if it worked, false if the room wasn't available.
    public boolean checkIn() {
        if (isAvailable()) {
            occupied = true;
            dirty = true;
            return true;
        }
        return false;
    }

    //? Checks the guest out. Only works if someone is in the room.
    //? The room stays dirty until housekeeping cleans it.
    public boolean checkOut() {
        if (occupied) {
            occupied = false;
            return true;
        }
        return false;
    }

    //? Cleans the room. Housekeeping can't clean while a guest is still inside.
    public boolean cleanRoom() {
        if (!occupied) {
            dirty = false;
            return true;
        }
        return false;
    }

}
