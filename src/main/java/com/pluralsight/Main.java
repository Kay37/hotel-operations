package com.pluralsight;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        //! TOOLS WE NEED
        //! ------------------------------------------------------------------------------------------------------------

        //? Creating a random to allow us to randomly generate.
        Random random = new Random();
        //! ------------------------------------------------------------------------------------------------------------

        //! ROOM CLASS/OBJECT CODE
        //! ------------------------------------------------------------------------------------------------------------
        //? Creating an arraylist to hold a list of my room objects.
        ArrayList<Room> rooms = new ArrayList<>();
        //? Creating four new room objects with public constructor.
        Room room1 =
                new Room("100", 2, 129.99, random.nextBoolean(), random.nextBoolean());
        Room room2 =
                new Room("101", 2, 129.99, random.nextBoolean(), random.nextBoolean());
        Room room3 =
                new Room("102", 2, 129.99, random.nextBoolean(), random.nextBoolean());
        Room room4 =
                new Room("103", 1, 64.99, random.nextBoolean(), random.nextBoolean());
        //? Adding these four new room objects to our rooms ArrayList.
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);
        //! ------------------------------------------------------------------------------------------------------------

        //! EMPLOYEE CLASS/OBJECT CODE
        //! ------------------------------------------------------------------------------------------------------------
        //? Creating an ArrayList to store employee objects
        ArrayList<Employee> employees = new ArrayList<>();
        //? Creating 3 employee objects
        Employee employee1 =
                new Employee(12345, "Mohamed Bilal", "Housekeeping",
                        50.00, 45);
        Employee employee2 =
                new Employee(12346, "Alina Rashid", "Maintenance",
                        35.00, 40);
        Employee employee3 =
                new Employee(12347, "Raymond Melly", "Clerk",
                        25.00, 41);
        //? Adding our employee objects to our employees ArrayList
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        //! ------------------------------------------------------------------------------------------------------------

        //! HOTEL CLASS/OBJECT CODE
        //! ------------------------------------------------------------------------------------------------------------
        //? Creating our hotel object using the 5-parameter constructor (some rooms already booked)
        Hotel hotel = new Hotel("Tres Leches Inn", 5, 20,
                2, 8);
        //! ------------------------------------------------------------------------------------------------------------
        System.out.println("\nHello!");
        System.out.println("To view the employee action menu, please punch in.");
        Employee currentEmployee = null;
        boolean isCorrectID = false;
        while (!isCorrectID) {
            int scEmployeeID = InputToolbox.promptInt("\nEmployee ID: ");

            for (Employee employee : employees) {
                if (scEmployeeID == employee.getEmployeeId()) {
                    currentEmployee = employee;
                    System.out.println("Employee ID: " + scEmployeeID + " accepted.");
                    System.out.println("Welcome back, " + currentEmployee.getName());
                    employee.punchTimeCard();
                    isCorrectID = true;
                }
            }
            if (!isCorrectID) {
                System.out.println("Employee ID: " + scEmployeeID + " rejected. Try again.");
            }
        }
        boolean menuRunning = true;
        while (menuRunning) {
            //? Creating an arraylist to hold a list of room's names that had boolean occupied = true
            ArrayList<String> occupiedRooms = new ArrayList<>();
            //? Creating an arraylist to hold a list of room's names that had boolean dirty = true
            ArrayList<String> dirtyRooms = new ArrayList<>();
            //? Creating an arraylist to hold a list of room's names that had
            //? boolean available = true (occupied and dirty = false)
            ArrayList<String> availableRooms = new ArrayList<>();

            System.out.println("""
                    \nPlease select an option below:
                    A) View room information
                    B) Make a reservation
                    C) View employee hours & pay
                    D) Check into a room
                    E) Check out of a room
                    F) Clean a room
                    G) View hotel information
                    H) Book hotel rooms
                    X) Punch out
                    """);
            String userChoice = InputToolbox.promptString("").toLowerCase();
            switch (userChoice) {
                case "a":
                    //? Printing out a header with fixed widths to the user displaying stored information for the traits
                    //? of the room objects in our ArrayList.
                    System.out.printf("%-6s %-5s %-9s %-9s %-6s %-9s%n",
                            "Room", "Beds", "Price", "Occupied", "Dirty", "Available");
                    //? For every room within our rooms ArrayList, retrieve their information with getters and print out
                    //? (with fixed widths) that information.
                    //? (name, # of beds, price, whether it's occupied, dirty, or available.)
                    for (Room room : rooms) {
                        System.out.printf("%-6s %-5d $%-8.2f %-9b %-6b %-9b%n",
                                room.getRoomName(), room.getNumberOfBeds(), room.getPrice(),
                                room.isOccupied(), room.isDirty(), room.isAvailable());
                        //? If a room was occupied, we're going to use a getter
                        //? to retrieve the room's name and add the name to our occupiedRooms ArrayList.
                        if (room.isOccupied()) {
                            occupiedRooms.add(room.getRoomName());
                        }
                        //? If a room was dirty, we're going to use a getter to retrieve the room's name
                        //? and add the name to our dirtyRooms ArrayList.
                        if (room.isDirty()) {
                            dirtyRooms.add(room.getRoomName());
                        }
                        //? If a room is available (not dirty, not occupied), we're going to
                        //? retrieve the room's name and add the name to our availableRooms ArrayList.
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
                            King - (King sized bed)
                            Double - (Double sized bed)""");
                    String roomType = InputToolbox.promptChoice("Please choose a room type",
                            "king", "double");

                    //? How many nights will they want their reservation to be for?
                    int numberOfNights = InputToolbox.promptInt(
                            "Please enter the number of nights you would like to reserve for (e.g. 5): ",
                            1);
                    //? Will there be a weekend charge? (This question is why the user should be the clerk)
                    boolean weekend = InputToolbox.promptYesNo("Are you reserving days during a weekend?");
                    //? Using the user's input and stored answers to create a new reservation object
                    //? from the Reservation class
                    Reservation reservation = new Reservation(roomType, numberOfNights, weekend);
                    //? Printing out that new reservation object's type, number of nights, and if
                    //? the reservation has a weekend charge.
                    System.out.printf("%n%s room, %d nights, weekend: %b%n", reservation.getRoomType(),
                            reservation.getNumberOfNights(), reservation.isWeekend());
                    //? Printing out the price per night (based on room type, plus 10% on weekends)
                    //? and the total (price per night × nights)
                    System.out.printf("Price per night: $%.2f   Total: $%.2f%n", reservation.getPrice(),
                            reservation.getReservationTotal());
                    break;
                case "c":

                    String passwordAttempt = InputToolbox.promptString(
                            "\nPlease enter the password to view sensitive employee information: ");
                    boolean passwordCorrect = false;
                    while (!passwordCorrect) {
                        if (passwordAttempt.equals("TresLeches123")) {
                            //? Printing out a header for employee information
                            System.out.printf("%-6s %-15s %-14s %-9s %-8s %-9s %s%n",
                                    "ID", "Name", "Department", "Rate", "Regular", "Overtime", "Total Pay");
                            //? For every employee within the employees ArrayList, retrieve their information
                            //? with our getters from Employee.java and print it formatted, and with fixed widths.
                            for (Employee employee : employees) {
                                System.out.printf("%-6d %-15s %-14s $%-8.2f %-8.1f %-9.1f $%.2f%n",
                                        employee.getEmployeeId(), employee.getName(), employee.getDepartment(),
                                        employee.getPayRate(), employee.getRegularHours(), employee.getOvertimeHours(),
                                        employee.getTotalPay());
                            }
                            passwordCorrect = true;
                        } else {
                            while (!passwordAttempt.equalsIgnoreCase("TresLeches123")) {
                                passwordAttempt = InputToolbox.promptString(
                                        "Please enter the correct password: ");
                            }
                        }
                    }
                    break;
                case "d":
                    String roomNumber = InputToolbox.promptString(
                            "\nPlease enter the room number to check in (e.g. 100): ");
                    //? Looks through the list for a room with this number and hands back that Room
                    //? or null if there's no match.
                    Room room = findRoom(rooms, roomNumber);
                    if (room == null) {
                        System.out.println("\nThere is no room " + roomNumber + ".");
                    } else if (room.checkIn()) {
                        System.out.println("\nChecked in to room " + roomNumber + ".");
                    } else {
                        System.out.println("\nRoom " + roomNumber + " isn't available.");
                    }
                    break;
                case "e":
                    roomNumber = InputToolbox.promptString(
                            "\nPlease enter the room number to check out (e.g. 100): ");
                    //? Looks through the list for a room with this number and hands back that Room
                    //? or null if there's no match.
                    room = findRoom(rooms, roomNumber);
                    if (room == null) {
                        System.out.println("There is no room " + roomNumber + ".");
                    } else if (room.checkOut()) {
                        System.out.println("\nChecked out of room " + roomNumber + ".");
                    } else {
                        System.out.println("\nRoom " + roomNumber + " is not occupied.");
                    }
                    break;
                case "f":
                    roomNumber = InputToolbox.promptString(
                            "\nPlease enter the room number to clean (e.g. 100): ");
                    //? Looks through the list for a room with this number and hands back that Room
                    //? or null if there's no match.
                    room = findRoom(rooms, roomNumber);
                    if (room == null) {
                        System.out.println("There is no room " + roomNumber + ".");
                    } else if (room.cleanRoom()) {
                        System.out.println("\nRoom " + roomNumber + " is cleaned.");
                    } else {
                        System.out.println("\nRoom " + roomNumber +
                                " is occupied or already clean and cannot be cleaned at this time.");
                    }
                    break;
                case "g":
                    //? Printing out the hotel's name and how many suites / basic rooms are available
                    System.out.printf("%n%s%n", hotel.getName());
                    System.out.printf("Suites: %d of %d available%n", hotel.getAvailableSuites(),
                            hotel.getNumberOfSuites());
                    System.out.printf("Basic rooms: %d of %d available%n", hotel.getAvailableRooms(),
                            hotel.getNumberOfRooms());
                    break;
                case "h":
                    String hotelRoomChoice = InputToolbox.promptChoice(
                            "Would you like to book a suite or basic room?", "suite", "basic");
                    boolean suite = hotelRoomChoice.equals("suite");
                    int roomCount = InputToolbox.promptInt(
                            "How many rooms would you like to book? (e.g. 2): ", 1);
                    if (hotel.bookRoom(roomCount, suite)) {
                        String roomWord;
                        if (suite) {
                            roomWord = " suite(s).";
                        } else {
                            roomWord = " basic room(s).";
                        }
                        System.out.println("Booked " + roomCount + roomWord);
                    } else {
                        System.out.println("Sorry, not enough rooms available for that booking.");
                    }
                    break;
                case "x":
                    if (InputToolbox.promptYesNo("Are you sure you want to punch out and end your shift?")) {
                        currentEmployee.punchTimeCard();
                        System.out.printf("%s punched out. Total hours this week: %.1f%n",
                                currentEmployee.getName(), currentEmployee.getHoursWorked());
                        menuRunning = false;
                    }
                    break;
                default:
                    System.out.println("Please select a valid option. Refer to the letters on the left. ");
                    break;
            }
        }
    }

    //? Looks through the list for a room with this number & hands back that Room or null if there's no match.
    public static Room findRoom(ArrayList<Room> rooms, String roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomName().equals(roomNumber)) {
                return room;
            }
        }
        return null;
    }

}

