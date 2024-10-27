package BankApp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputManager {

    private static Scanner scanner = new Scanner(System.in);


    /*
    Method overloading polymorphism

    Method for int input without parameters.
    Looping until user enters an int or q.
    Tries to return int or -1 if q has been entered.
    Else, prints error message and waits for new input.
     */
    public static int intInput() {
        while (true) {
            if (scanner.hasNext()) {
                try {
                    return scanner.nextInt();
                } catch (InputMismatchException e) {
                    if (scanner.next().equalsIgnoreCase("q")) {
                        return -1;
                    } else {
                        scanner.nextLine();
                        System.out.printf("\nInvalid input!\n" +
                                "Please enter an integer.\n");
                    }
                }
            }
        }
    }
    /*
    Overloaded method
    Int input between two limit parameters - min, max.
    Returns input if it is between min and max or q.
    Returns -1 if q is input, else prints error message
     */
    public static int intInput(int min, int max) {
        while (true) {
            if (scanner.hasNext()) {
                    try {
                        int input = scanner.nextInt();
                        if (input >= min && input <= max) {
                            return input;
                        }
                        else {
                            scanner.nextLine();
                            System.out.printf("\nInvalid input!\n" +
                                    "Please enter a number between %d-%d.\n", min, max);
                        }
                    } catch (InputMismatchException e) {
                        if (scanner.next().equalsIgnoreCase("q")) {
                            return -1;
                        } else {
                            scanner.nextLine();
                            System.out.printf("\nInvalid input!\n" +
                                    "Please enter a number between %d-%d.\n", min, max);
                        }
                    }
                }
            }
        }

    /*
    Double input method
    Waits fot scanner, returns scanner double
    or prints an error message until a double has been input
     */
    public static Double doubleInput() {
        while (true) {
            if (scanner.hasNext()) {
                try {
                    return scanner.nextDouble();
                } catch (InputMismatchException e) {
                    scanner.nextLine();
                    System.out.printf("\nInvalid input!\n" +
                            "Please enter a number.\n");
                    }
                }
            }
        }


    /*
    Returns scanner string input
     */
    public static String stringInput() {
        while (true) {
            if (scanner.hasNext()) {
                try {
                    return scanner.next();
                } catch (InputMismatchException e) {
                    scanner.nextLine();
                    System.out.printf("\nInvalid input!\n");
                }
            }
        }
    }
}
