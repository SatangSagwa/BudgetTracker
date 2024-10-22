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
                                "Please enter a number or Q to quit.\n");
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
                                    "Please enter a number between %d-%d or Q quit.\n", min, max);
                        }
                    } catch (InputMismatchException e) {
                        if (scanner.next().equalsIgnoreCase("q")) {
                            return -1;
                        } else {
                            scanner.nextLine();
                            System.out.printf("\nInvalid input!\n" +
                                    "Please enter a number between %d-%d or Q quit.\n", min, max);
                        }
                    }
                }
            }
        }



    public static Float floatInput() {
        while (true) {
            if (scanner.hasNext()) {
                try {
                    return scanner.nextFloat();
                } catch (InputMismatchException e) {
                    if (scanner.next().equalsIgnoreCase("q")) {
                        return -1f;
                    } else {
                        scanner.nextLine();
                        System.out.printf("\nInvalid input!\n" +
                                "Please enter a number or Q to quit.\n");
                    }
                }
            }
        }
    }

    public static float floatInput(float min, float max) {
        while (true) {
            if (scanner.hasNext()) {
                try {
                    float input = scanner.nextFloat();
                    if (input >= min && input <= max) {
                        return input;
                    }
                    else {
                        scanner.nextLine();
                        System.out.printf("\nInvalid input!\n" +
                                "Please enter a number between %f-%f or Q quit.\n", min, max);
                    }
                } catch (InputMismatchException e) {
                    if (scanner.next().equalsIgnoreCase("q")) {
                        return -1;
                    } else {
                        scanner.nextLine();
                        System.out.printf("\nInvalid input!\n" +
                                "Please enter a number between %f-%f or Q quit.\n", min, max);
                    }
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
