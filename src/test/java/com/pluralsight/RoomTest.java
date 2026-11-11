package com.pluralsight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {


    //! checkIn() tests
    //! ----------------------------------------------------------------------------------------------------------------
    @Test
    public void checkIn_cleanEmptyRoom_becomesOccupiedAndDirty() {
        //* Arrange
        //? Setting up the situation. A clean, empty room (occupied & dirty = false)
        Room room = new Room("100", 2, 129.99, false, false);

        //* Act
        //? Run the method being tested. checkIn() hands back true or false, and we're storing that answer in result
        boolean result = room.checkIn();

        //* Assert
        //? Checking the result. assertTrue means "this must be true, or the test fails".
        assertTrue(result);
        //? Room should now be occupied & dirty.
        assertTrue(room.isOccupied());
        assertTrue(room.isDirty());
    }

    @Test
    public void checkIn_dirtyOccupiedRoom_should_returnFalse() {
        //* Arrange
        Room room = new Room("100", 2, 129.99, true, true);

        //* Act
        boolean result = room.checkIn();

        //* Assert
        assertFalse(result);
        assertTrue(room.isOccupied());
        assertTrue(room.isDirty());
    }

    @Test
    public void checkIn_dirtyEmptyRoom_should_returnFalse() {
        //* Arrange
        Room room = new Room("100", 2, 129.99, false, true);

        //* Act
        boolean result = room.checkIn();

        //* Assert
        assertFalse(result);
        assertFalse(room.isOccupied());
    }

    @Test
    public void checkIn_cleanOccupiedRoom_should_returnFalse() {
        //* Arrange
        Room room = new Room("100", 2, 129.99, true, false);

        //* Act
        boolean result = room.checkIn();

        //* Assert
        assertFalse(result);
        assertTrue(room.isOccupied());
    }

    //! ----------------------------------------------------------------------------------------------------------------

    //! checkOut() tests
    //! ----------------------------------------------------------------------------------------------------------------
    @Test
    public void checkOut_occupiedDirtyRoom_becomesEmptyDirtyRoom() {
        //* Arrange
        //? Setting up the situation. A dirty, occupied room (occupied & dirty = true)
        Room room = new Room("100", 2, 129.99, true, true);

        //* Act
        //? Running the method being tested. checkOut() hands back true or false, and we're storing that answer in result
        boolean result = room.checkOut();

        //* Assert
        //? Checking the result.
        assertTrue(result);
        assertFalse(room.isOccupied());
        assertTrue(room.isDirty());
    }
    @Test
    public void checkOut_emptyRoom_should_returnFalse() {
        //* Arrange
        Room room = new Room("100", 2, 129.99, false, true);

        //* Act
        boolean result = room.checkOut();

        //* Assert
        assertFalse(result);
        assertFalse(room.isOccupied());
        assertTrue(room.isDirty());
    }

    //! ----------------------------------------------------------------------------------------------------------------

    //! cleanRoom() tests
    //! ----------------------------------------------------------------------------------------------------------------
    @Test
    public void cleanRoom_emptyDirtyRoom_becomesEmptyCleanRoom() {
        //* Arrange
        Room room = new Room("100", 2, 129.99, false, true);

        //* Act
        boolean result = room.cleanRoom();

        //* Assert
        assertTrue(result);
        assertFalse(room.isOccupied());
        assertFalse(room.isDirty());
    }

    @Test
    public void cleanRoom_alreadyCleanRoom_should_returnFalse() {
        //* Arrange
        Room room = new Room("100", 2, 129.99, false, false);

        //* Act
        boolean result = room.cleanRoom();

        //* Assert
        assertFalse(result);
        assertFalse(room.isOccupied());
        assertFalse(room.isDirty());
    }

    @Test
    public void cleanRoom_occupiedDirtyRoom_should_returnFalse() {
        //* Arrange
        Room room = new Room("100", 2, 129.99, true, true);

        //* Act
        boolean result = room.cleanRoom();

        //* Assert
        assertFalse(result);
        assertTrue(room.isOccupied());
        assertTrue(room.isDirty());
    }

    //! ----------------------------------------------------------------------------------------------------------------
}