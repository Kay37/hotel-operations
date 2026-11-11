package com.pluralsight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    @Test
    public void getPrice_should_be_accurate_for_king_room_type_no_weekend_one_night(){
        //* Arrange
        Reservation testReservation = new Reservation("king", 1, false);

        //* Act
        double price =  testReservation.getPrice();

        //* Assert
        assertEquals(139, price);
    }

    @Test
    public void getPrice_should_be_accurate_for_king_room_type_with_weekend_one_night(){
        //* Arrange
        Reservation testReservation = new Reservation("king", 1, true);

        //* Act
        double price =  testReservation.getPrice();

        //* Assert
        assertEquals(152.9, price);
    }

    @Test
    public void getReservationTotal_should_be_accurate_for_king_room_type_with_no_weekend_three_nights(){
        //* Arrange
        Reservation testReservation = new Reservation("king", 3, true);

        //* Act
        double total =  testReservation.getReservationTotal();

        //* Assert
        assertEquals(458, total, 0.8);
    }

}