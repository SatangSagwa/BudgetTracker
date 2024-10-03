package BankApp;

import java.time.LocalDate;

public class BudgetTracker {

    public static void main(String[] args) {

        System.out.println("Welcome to the budget tracker!\n" +
                           "------------------------------");
        System.out.println("Please enter your option: ");
        System.out.println("1. Edit expenses");
        System.out.println("2. Edit incomes");
        System.out.println("3. Display history");
        System.out.println("4. Edit users");
        System.out.println("Q. Quit");

        int option = InputManager.intInput(1, 4);

        switch (option) {
            case 1:
                System.out.println("EXPENSES\n" +
                                   "---------");
                System.out.println("1. Add transaction");
                System.out.println("2. Remove transaction");
                System.out.println("3. Edit transaction");
                System.out.println("Q. Quit");
                int transactionOption = InputManager.intInput(1, 3);

                switch (transactionOption) {
                    case 1:
                        Expense expense = new Expense(0, LocalDate.now(), null, EExpenseCategory.OTHER);
                        System.out.println("Enter the total sum: ");


                }
        }
    }
}
