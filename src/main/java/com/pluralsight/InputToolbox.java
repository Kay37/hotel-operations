
package com.pluralsight;

import java.util.Scanner;

public class InputToolbox {

    //? Creating one shared Scanner for the whole program. Every question will go through this.
    private static Scanner sc = new Scanner(System.in);

    //? Creating a Private constructor: will not create an InputToolbox object - will just call its methods.
    private InputToolbox() {

    }

    //! methods go below here
    //* This method will ask a question and keep asking it until the user types a whole number from min to max.
    public static int promptInt(String question, int min, int max) {
        System.out.print(question);
        //? This is a variable / box to hold the user's answer.
        int number = 0;
        //? This is a flag meaning "do we have a good answer yet?" It starts as false.
        boolean valid = false;

        //?While not valid (keep repeating until it is valid)
        while (!valid) {
            //? LOOKS at what the user typed - Is this a whole number?
            if (sc.hasNextInt()) {
                //? After looking and seeing it is a whole number, it READS the number and stores it in number.
                number = sc.nextInt();
                //? Input buffer - absorbing extra enter
                sc.nextLine();
                //? Is the number (user's answer) in range? We got the minimum value and max value when we
                //? called the method (look at method parameters)
                if (number >= min && number <= max) {
                    valid = true;
                    //? we only reach this else if when the number is not at least the minimum or at most the max
                } else if (max == Integer.MAX_VALUE) {
                    System.out.print("Please enter a number of " + min + " or more: ");
                } else {
                    System.out.print("Please enter a number from " + min + " to " + max + ": ");
                }
            } else {
                sc.nextLine();
                System.out.print("Please enter a whole number: ");
            }
        }
        return number;
    }
}
