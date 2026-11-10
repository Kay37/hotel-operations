package com.pluralsight;

public class Hotel {

    //! our backing variables
    private String name;
    private int numberOfSuites;
    private int numberOfRooms;
    private int bookedSuites;
    private int bookedBasicRooms;

    //! constructor that sets initial value for # of suites and rooms. bookedSuites and bookedBasicRooms default to 0.
    public Hotel(String name, int numberOfSuites, int numberOfRooms) {
        this.name = name;
        this.numberOfSuites = numberOfSuites;
        this.numberOfRooms = numberOfRooms;
        this.bookedSuites = 0;
        this.bookedBasicRooms = 0;
    }
    //! this constructor specifies the # of bookedSuites and bookedBasicRooms.
    public Hotel(String name, int numberOfSuites, int numberOfRooms, int bookedSuites, int bookedBasicRooms) {
        this.name = name;
        this.numberOfSuites = numberOfSuites;
        this.numberOfRooms = numberOfRooms;
        this.bookedSuites = bookedSuites;
        this.bookedBasicRooms = bookedBasicRooms;
    }

    //! our plain getters.
    public String getName() {
        return name;
    }

    public int getNumberOfSuites() {
        return numberOfSuites;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public int getBookedSuites() {
        return bookedSuites;
    }

    public int getBookedBasicRooms() {
        return bookedBasicRooms;
    }

    //! derived getters. no backing variables. They are calculated from total minus booked, so always accurate after
    //! a booking.
    public int getAvailableSuites() {
        return numberOfSuites - bookedSuites;
    }

    public int getAvailableRooms() {
        return numberOfRooms - bookedBasicRooms;
    }

    //! method to book one or more rooms, and specify if it is a suite or basic room.
    public boolean bookRoom(int roomCount, boolean suite) {
        //? A booking needs at least one room (no negative numbers or 0)
        if (roomCount < 1) {
            return false;
        }
        //? if they specify that they want to book suites
        if (suite) {
            //? and it's possible because available suites is at least the total roomCount
            if (getAvailableSuites() >= roomCount) {
                //? bookedSuites = bookedSuites + roomCount. We're adding to the existing bookings instead of replacing
                bookedSuites += roomCount;
                // ? sends an answer of true back to whoever called bookRoom(), meaning "the booking worked"
                return true;
            } else {
                // ? sends an answer of false back to whoever called bookRoom(), meaning "the booking failed"
                return false;
            }
            //? else if not a suite, (suite = false)
        } else {
            //? then check if basic rooms that are available is at least the total roomCount.
            if (getAvailableRooms() >= roomCount) {
                //? bookedBasicRooms = bookedBasicRooms + roomCount (bookedBasicRooms will either be specified or 0
                //? depending on which constructor is used)
                //? We're adding to the existing bookings instead of replacing
                bookedBasicRooms += roomCount;
                // ? sends an answer of true back to whoever called bookRoom(), meaning "the booking worked"
                return true;
            } else {
                // ? sends an answer of false back to whoever called bookRoom(), meaning "the booking failed"
                return false;
            }
        }
    }


}
