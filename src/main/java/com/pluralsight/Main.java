package com.pluralsight;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //? Creating scanner to receive user input
        Scanner sc = new Scanner(System.in);
        //? Creating a random to allow us to randomly generate.
        Random random = new Random();

        boolean menuRunning = true;
        while (menuRunning) {
            System.out.println("""
                    \nWelcome! Please select an option below: 
                    A) View room information
                    B) Make a reservation
                    C) View employee hours & pay
                    """);
            String userChoice = sc.nextLine().toLowerCase();
            switch (userChoice) {
                case "a":
                    //? Creating an arraylist to hold a list of my room objects.
                    ArrayList<Room> rooms = new ArrayList<>();
                    //? Creating an arraylist to hold a list of room's names that had boolean occupied = true
                    ArrayList<String> occupiedRooms = new ArrayList<>();
                    //? Creating an arraylist to hold a list of room's names that had boolean dirty = true
                    ArrayList<String> dirtyRooms = new ArrayList<>();
                    //? Creating an arraylist to hold a list of room's names that had boolean available = true (occupied and dirty = false)
                    ArrayList<String> availableRooms = new ArrayList<>();
                    //? Creating four new room objects with public constructor.
                    Room room1 = new Room("100", 2, 129.99, random.nextBoolean(), random.nextBoolean());
                    Room room2 = new Room("101", 2, 129.99, random.nextBoolean(), random.nextBoolean());
                    Room room3 = new Room("102", 2, 129.99, random.nextBoolean(), random.nextBoolean());
                    Room room4 = new Room("103", 1, 64.99, random.nextBoolean(), random.nextBoolean());
                    //? Adding these four new room objects to our rooms ArrayList.
                    rooms.add(room1);
                    rooms.add(room2);
                    rooms.add(room3);
                    rooms.add(room4);

                    //? Printing out a header with fixed widths to the user displaying stored information for the traits
                    //? of the room objects in our ArrayList.
                    System.out.printf("%-6s %-5s %-9s %-9s %-6s %-9s%n",
                            "Room", "Beds", "Price", "Occupied", "Dirty", "Available");
                    //? For every room within our rooms ArrayList, retrieve their information with getters and print out
                    //? (with fixed widths) that information (name, # of beds, price, whether it's occupied, dirty, or available.)
                    for (Room room : rooms) {
                        System.out.printf("%-6s %-5d $%-8.2f %-9b %-6b %-9b%n",
                                room.getRoomName(), room.getNumberOfBeds(), room.getPrice(),
                                room.isOccupied(), room.isDirty(), room.isAvailable());
                        //? If a room was occupied, we're going to use a getter to retrieve the room's name and add the name to
                        //? our occupiedRooms ArrayList.
                        if (room.isOccupied()) {
                            occupiedRooms.add(room.getRoomName());
                        }
                        //? If a room was dirty, we're going to use a getter to retrieve the room's name and add the name to
                        //? our dirtyRooms ArrayList.
                        if (room.isDirty()) {
                            dirtyRooms.add(room.getRoomName());
                        }
                        //? If a room is available (not dirty, not occupied), we're going to retrieve the room's name and add the
                        //? name to our availableRooms ArrayList.
                        if (room.isAvailable()) {
                            availableRooms.add(room.getRoomName());
                        }
                    }
                    //? Print out the size of our occupiedRooms ArrayList (size = how many String are in the ArrayList)
                    //? And print out the String ArrayList that contains the names of occupied rooms.
                    System.out.printf("%nOccupied (%d): %s%n", occupiedRooms.size(), occupiedRooms);
                    //? Print out the size of our dirtyRooms ArrayList (size = how many String are in the ArrayList)
                    //? And print out the String ArrayList that contains the names of dirty rooms.
                    System.out.printf("Dirty (%d): %s%n", dirtyRooms.size(), dirtyRooms);
                    //? Print out the size of our availableRooms ArrayList (size = how many String are in the ArrayList)
                    //? And print out the String ArrayList that contains the names of available rooms.
                    System.out.printf("Available (%d): %s%n", availableRooms.size(), availableRooms);
                    break;
                case "b":
                    //? What room type?
                    System.out.println("""
                            Please choose a room type:
                            King - (King sized bed)
                            Double - (Double sized bed)
                            Answer with "King" or "Double"
                            """);
                    String roomType = sc.nextLine().trim();
                    //? If the answer for the room type is not king or double, we know they gave an invalid input.
                    //? We prompt them again and again while their answer is not the input we want.
                    while (!roomType.equalsIgnoreCase("king") && !roomType.equalsIgnoreCase("double")) {
                        System.out.println("Please answer with 'King' or 'Double': ");
                        roomType = sc.nextLine().trim();
                    }

                    //? How many nights will they want their reservation to be for?
                    System.out.println("Please enter the number of nights you would like to reserve for (e.g. 5): ");
                    //? Creating number of nights variable for our loop.
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
                    //? Will there be a weekend charge? (This question is why the user should be the clerk)
                    System.out.println("Are you reserving days during a weekend? (e.g. 'Yes' or 'No'): ");
                    //? Storing their answer as a String and converting this String to a boolean later
                    String weekendAnswer = sc.nextLine().trim();
                    //? While their answer is not yes or no, we know they gave an invalid input. We will continually
                    //? prompt them to answer within our desired format.
                    while (!weekendAnswer.equalsIgnoreCase("yes") && !weekendAnswer.equalsIgnoreCase("no")) {
                        System.out.println("Please answer with 'Yes' or 'No': ");
                        weekendAnswer = sc.nextLine().trim();
                    }
                    //? Creating our weekend boolean if the user's input is yes.
                    boolean weekend = weekendAnswer.equalsIgnoreCase("yes");
                    //? Using the user's input and stored answers to create a new reservation object from the Reservation class
                    Reservation reservation = new Reservation(roomType, numberOfNights, weekend);
                    //? Printing out that new reservation object's type, number of nights, and if the reservation has a weekend charge.
                    System.out.printf("%n%s room, %d nights, weekend: %b%n", reservation.getRoomType(), reservation.getNumberOfNights(), reservation.isWeekend());
                    //? Printing out the price per night (based on room type, plus 10% on weekends) and the total (price per night × nights)
                    System.out.printf("Price per night: $%.2f   Total: $%.2f%n", reservation.getPrice(), reservation.getReservationTotal());
                    break;
                case "c":
                    //? Creating an ArrayList to store employee objects
                    ArrayList<Employee> employees = new ArrayList<>();
                    //? Creating 3 employee objects
                    Employee employee1 = new Employee(12345, "Mohamed Bilal", "Housekeeping", 50.00, 45);
                    Employee employee2 = new Employee(12346, "Alina Rashid", "Maintenance", 35.00, 40);
                    Employee employee3 = new Employee(12347, "Raymond Melly", "Clerk", 25.00, 41);
                    //? Adding our employee objects to our employees ArrayList
                    employees.add(employee1);
                    employees.add(employee2);
                    employees.add(employee3);
                    //? Printing out a header for employee information
                    System.out.printf("%-6s %-15s %-14s %-9s %-8s %-9s %s%n",
                            "ID", "Name", "Department", "Rate", "Regular", "Overtime", "Total Pay");
                    //? For every employee within the employees ArrayList, retrieve their information with our getters
                    //? from Employee.java and print it formatted, and with fixed widths.
                    for (Employee employee : employees) {
                        System.out.printf("%-6d %-15s %-14s $%-8.2f %-8.1f %-9.1f $%.2f%n",
                                employee.getEmployeeId(), employee.getName(), employee.getDepartment(),
                                employee.getPayRate(), employee.getRegularHours(), employee.getOvertimeHours(),
                                employee.getTotalPay());
                    }
                    break;
                default:
                    System.out.println("Please select a valid option. Refer to the letters on the left. ");
                    menuRunning = false;
                    break;
            }
        }
    }
}

