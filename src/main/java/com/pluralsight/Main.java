package com.pluralsight;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<String> occupiedRooms = new ArrayList<>();
        ArrayList<String> dirtyRooms = new ArrayList<>();
        ArrayList<String> availableRooms = new ArrayList<>();
        Room room1 = new Room("100", 2, 129.99, random.nextBoolean(), random.nextBoolean());
        Room room2 = new Room("101", 2, 129.99, random.nextBoolean(), random.nextBoolean());
        Room room3 = new Room("102", 2, 129.99, random.nextBoolean(), random.nextBoolean());
        Room room4 = new Room("103", 1, 64.99, random.nextBoolean(), random.nextBoolean());
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);
        System.out.printf("%-6s %-5s %-9s %-9s %-6s %-9s%n",
                "Room", "Beds", "Price", "Occupied", "Dirty", "Available");
        for (Room room : rooms) {
            System.out.printf("%-6s %-5d $%-8.2f %-9b %-6b %-9b%n",
                    room.getRoomName(), room.getNumberOfBeds(), room.getPrice(),
                    room.isOccupied(), room.isDirty(), room.isAvailable());
            if (room.isOccupied()) {
                occupiedRooms.add(room.getRoomName());
            }
            if (room.isDirty()) {
                dirtyRooms.add(room.getRoomName());
            }
            if (room.isAvailable()) {
                availableRooms.add(room.getRoomName());
            }
        }
        System.out.printf("%nOccupied (%d): %s%n", occupiedRooms.size(), occupiedRooms);
        System.out.printf("Dirty (%d): %s%n", dirtyRooms.size(), dirtyRooms);
        System.out.printf("Available (%d): %s%n", availableRooms.size(), availableRooms);

        System.out.println("Would you like to create a reservation for a room?: ");
        if (sc.nextLine().equalsIgnoreCase("Yes")) {
            System.out.println("""
                    Please choose a room type:
                    King - (King sized bed)
                    Double - (Double sized bed)
                    Answer with "King" or "Double"
                    """);
            String roomType = sc.nextLine().trim();
            while (!roomType.equalsIgnoreCase("king") && !roomType.equalsIgnoreCase("double")) {
                System.out.println("Please answer with 'King' or 'Double': ");
                roomType = sc.nextLine().trim();
            }


            System.out.println("Please enter the number of nights you would like to reserve for (e.g. 5): ");
            int numberOfNights = 0;
            //? While numberOfNights < 1 (so this will run because the line before we set it to 0)
            //? Check if the user's input contained an integer. If it did, store that integer as the new value of
            //? numberOfNights. If that new value is STILL less than 1, we know they entered 0 or less (negative)
            //? The else handles if the user's input does NOT contain an integer. It will prompt them to enter a whole
            //? number, this pass through the loop ends, and numberOfNights will still = 0 so it will run the loop again until
            //? the user's input contains an integer AND that integer is a number of 1 or more.
            while (numberOfNights < 1) {
                if (sc.hasNextInt()) {
                    numberOfNights = sc.nextInt();
                    if (numberOfNights < 1) {
                        System.out.println("Please enter a number of 1 or more (e.g. 5): ");
                    }
                } else {
                    System.out.println("Please enter a whole number (e.g. 5): ");
                }
                //? This line absorbs the user's bad answer and throws it away (we do not store it in a variable).
                //? Without this line if they typed abc it would run the else statement and loop forever.
                //? After a GOOD answer, it clears the leftover Enter from the input buffer.
                sc.nextLine();
            }
            System.out.println("Are you reserving days during a weekend? (e.g. 'Yes' or 'No'): ");
            String weekendAnswer = sc.nextLine().trim();
            while (!weekendAnswer.equalsIgnoreCase("yes") && !weekendAnswer.equalsIgnoreCase("no")) {
                System.out.println("Please answer with 'Yes' or 'No': ");
                weekendAnswer = sc.nextLine().trim();
            }
            boolean weekend = weekendAnswer.equalsIgnoreCase("yes");
            Reservation reservation = new Reservation(roomType, numberOfNights, weekend);

            System.out.printf("%s room, %d nights, weekend: %b%n", reservation.getRoomType(), reservation.getNumberOfNights(), reservation.isWeekend());
            System.out.printf("Price per night: $%.2f   Total: $%.2f%n", reservation.getPrice(), reservation.getReservationTotal());
        }

    }
}

