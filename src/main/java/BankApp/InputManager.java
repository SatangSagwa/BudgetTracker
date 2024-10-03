package BankApp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputManager {

    private static Scanner scanner = new Scanner(System.in);

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

    public static int intInput(int min, int max) {
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
                                "Please enter a number between %s-%s or Q to quit.\n", min, max);
                    }
                }
            }
        }
    }
}
