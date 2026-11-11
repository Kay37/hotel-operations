
package com.pluralsight;

import java.util.Scanner;

public class InputToolbox {

    //? Creating one shared Scanner for the whole program. Every question will go through this.
    private static Scanner sc = new Scanner(System.in);

    //? Creating a Private constructor: will not create an InputToolbox object - will just call its methods.
    private InputToolbox() {

    }

    //! methods go below here
    //! ----------------------------------------------------------------------------------------------------------------

    //! PROMPT FOR AN INT
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
                    //? we only reach this else if when the number is not at least the minimum or at most the max.
                    //? the code will run without this, all it does is pick a nicer looking error message. Instead
                    //? of telling a kid wanting to get on a rollercoaster "You must be between 4 feet tall and
                    //? 2 billion feet tall" we just say at least 4 feet tall.
                } else if (max == Integer.MAX_VALUE) {
                    System.out.print("Please enter a number of " + min + " or more: ");
                    //? This prints the full range - "from 1 to 4".
                } else {
                    System.out.print("Please enter a number from " + min + " to " + max + ": ");
                }
                //? We get to this else if they typed something like abc. It throws away the abc, then tells
                //? the user what we need.
            } else {
                sc.nextLine();
                System.out.print("Please enter a whole number: ");
            }
        }
        //? Hands the good number back to whoever asked.
        return number;
    }

    //? Whole number of at least min, with no upper limit.
    //? This is the same name with one fewer parameter. This method will call the above method and pass the
    //? question and min along. For the max it uses the biggest number an int can hold. (no maximum)
    public static int promptInt(String question, int min) {
        return promptInt(question, min, Integer.MAX_VALUE);
    }

    //? Any whole number at all.
    //? It's the same idea but with both limits wide open. This would be used for negative values acceptable.
    public static int promptInt(String question) {
        return promptInt(question, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    //! ----------------------------------------------------------------------------------------------------------------

    //! PROMPT FOR A CHOICE
    //! ----------------------------------------------------------------------------------------------------------------
    //* This method will ask a question and keep asking it until the user types option 1 or option 2.
    //* It also hands back their choice in lowercase.

    //? Returns a string, the option they picked. Takes the question + two allowed answers. The answers are parameters.
    public static String promptChoice(String question, String option1, String option2) {
        //? Creates the prompt. i.e. a question about room type, with king and double as the choices, the user would see
        //? "Room type? (king/double): "
        System.out.print(question + " (" + option1 + "/" + option2 + "): ");
        //? Reads their whole line of typing (answer). .trim() chops off spaces trailing and leading.
        String answer = sc.nextLine().trim();
        //? Keep looking while the answer is not option 1 and not option 2. If it matches either one, the condition
        //? becomes false and the loop ends.
        while (!answer.equalsIgnoreCase(option1) && !answer.equalsIgnoreCase(option2)) {
            //? Providing them the valid options they can choose.
            System.out.print("Please answer with '" + option1 + "' or '" + option2 + "': ");
            //? Storing their new answer
            answer = sc.nextLine().trim();
        }
        //? Return the user's choice converted to lowercase.
        return answer.toLowerCase();

    }
    //! ----------------------------------------------------------------------------------------------------------------

    //! PROMPT FOR A YES/NO
    //! ----------------------------------------------------------------------------------------------------------------
    //* This method will ask a yes/no question. It will hand back true for yes, false for no.

    //? Returns a boolean, because a yes/no question is a true/false question.
    public static boolean promptYesNo(String question) {
        //? Using our promptChoice method.
        String answer = promptChoice(question, "yes", "no");
        //? answer equals is a true or false. and if "yes" (converted to lowercase by promptChoice) then it gets
        //? handed back.
        return answer.equals("yes");
    }
    //! ----------------------------------------------------------------------------------------------------------------

    //! PROMPT FOR A STRING
    //! ----------------------------------------------------------------------------------------------------------------
    //* This method will ask a question and keep asking until the user types something (not just Enter).
    //* This would be for questions where the answer can be anything - a menu letter, a room number, or a password.
    public static String promptString(String question) {
        //? Shows the question
        System.out.println(question);
        //? Reads their response and trims
        String answer = sc.nextLine().trim();
        //? While their answer is empty, or they just pressed Enter, keep asking.
        while (answer.isEmpty()) {
            //? Ask them to retry with an answer.
            System.out.println("Please type an answer: ");
            //? Reads a new answer so loop can end.
            answer = sc.nextLine().trim();
        }
        //? Return what they typed. Not lowercased, as things like a password need their capital letters kept.
        return answer;
    }

    //! ----------------------------------------------------------------------------------------------------------------
}
