package com.pluralsight;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<String> occupiedRooms = new ArrayList<>();
        ArrayList<String> dirtyRooms =  new ArrayList<>();
        ArrayList<String> availableRooms = new ArrayList<>();
        Room room1 = new Room("100", 2, 129.99, random.nextBoolean(), random.nextBoolean());
        Room room2 = new Room("101", 2, 129.99, random.nextBoolean(), random.nextBoolean());
        Room room3 = new Room("102", 2, 129.99, random.nextBoolean(), random.nextBoolean());
        Room room4 = new Room("103", 1, 64.99, random.nextBoolean(), random.nextBoolean());
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);
        System.out.printf("%s %s %s %s %s %s%n",
                "Room", "Beds", "Price", "Occupied", "Dirty", "Available");
        for  (Room room : rooms) {
            System.out.printf("%s %d $%.2f %b %b %b%n",
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
        System.out.printf("%nOccupied (%d): %s%n",  occupiedRooms.size(), occupiedRooms);
        System.out.printf("Dirty (%d): %s%n", dirtyRooms.size(), dirtyRooms);
        System.out.printf("Available (%d): %s%n",  availableRooms.size(), availableRooms);
    }

}
