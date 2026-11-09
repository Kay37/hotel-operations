package com.pluralsight;

public class Reservation {

    //! Fields that define what a reservation is / contains
    private String roomType;
    private int numberOfNights;
    private boolean weekend;

    //! creating a public constructor to set the value of each backing variable
    public Reservation(String roomType, int numberOfNights, boolean weekend) {
        this.roomType = roomType;
        this.numberOfNights = numberOfNights;
        this.weekend = weekend;
    }

    //! getters and setters for storing and retrieving information into/from fields/variables.
    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public boolean isWeekend() {
        return weekend;
    }

    public void setWeekend(boolean weekend) {
        this.weekend = weekend;
    }

    public double getPrice() {
        double price = 0;
        if (getRoomType().equalsIgnoreCase("king")) {
            price = 139.00;
        } else if (getRoomType().equalsIgnoreCase("double")) {
            price = 124.00;
        }
        if  (weekend) {
            price *= 1.10;
        }
     return price;
    }
    public double getReservationTotal() {
        return getPrice() * numberOfNights;
    }
}

