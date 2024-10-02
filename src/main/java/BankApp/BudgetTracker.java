package BankApp;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BudgetTracker {

    private static final int QUIT = -1;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome to the budget tracker!");
        System.out.println("Please enter your option: ");
        System.out.println("1. Edit expenses");
        System.out.println("2. Edit incomes");
        System.out.println("3. Display history");
        System.out.println("4. Edit users");
        System.out.println("Q. Quit");

        int option = intInput(-1, 3);

        switch (option) {
            case 1:
                System.out.println("1. Add transaction");
                System.out.println("2. Remove transaction");
                System.out.println("3. Edit transaction");
                System.out.println("Q. Quit");
                int transactionOption = intInput(1, 3);
                switch (transactionOption) {
                    case 1:
                        System.out.println("Enter the total sum: ");
                        Expense expense = new Expense(sum, LocalDate.now(), user, category);

                }
        }
    }

    public static int intInput(int min, int max) {
        while (true) {
            try {
                if (scanner.hasNextInt()) {
                    return scanner.nextInt();
                }
                else {
                    System.out.println("Please enter an integer between " + min + " and " + max);
                }
            } catch (InputMismatchException e) {
                if (scanner.next().toLowerCase().equals("q")) {
                    return QUIT;
                }
                else {
                    scanner.nextLine();
                    System.out.println("Please enter an integer between " + min + " and " + max);
                }
            }
        }
    }
}
