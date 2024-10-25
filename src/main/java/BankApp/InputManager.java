package BankApp;

import java.util.InputMismatchException;
import java.util.Scanner;

//Class for managing user input
public class InputManager {

    private static Scanner scanner = new Scanner(System.in);

    /*
    Metod för int input utan parametrar
    Loopar tills användaren skriver in ett tal eller q (= -1)
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
    Metod för int input med int parametrar
    Loopar tills användaren skriver in ett tal mellan min och max eller q (= -1)
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
    Metod för string input
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
