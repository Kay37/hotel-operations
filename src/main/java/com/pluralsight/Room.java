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

    public void checkIn() {
        if (isAvailable()) {
            occupied = true;
            dirty = true;
            System.out.println("\nChecked in to room " + roomName + ".");
        } else {
            System.out.println("\nRoom " + roomName + " isn't available.");
        }

    }

    public void checkOut() {
        if (occupied) {
            occupied = false;
            System.out.println("\nChecked out of room " + roomName + ".");
        } else {
            System.out.println("\nRoom " + roomName + " is not occupied.");
        }

    }

    public void cleanRoom() {
        if (!occupied) {
            dirty =  false;
            System.out.println("\nRoom " + roomName + " is cleaned.");
        } else {
            System.out.println("\nRoom " + roomName + " is occupied and cannot be cleaned at this time.");
        }

    }
}
