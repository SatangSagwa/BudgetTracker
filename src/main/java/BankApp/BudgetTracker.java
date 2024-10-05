package BankApp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BudgetTracker {

    public static void main(String[] args) {

        System.out.println("Welcome to the budget tracker!\n" +
                           "------------------------------");

        List<User> users = new ArrayList<User>();
        users.add(new User("Emilia", "Ivarsson"));
        users.add(new User("Jonas", "Larsen"));

        while (true) {
            System.out.println();
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
                    //Ask for which user
                    System.out.println("Choose a user: ");
                    int i = 1;
                    for (User user : users) {
                        System.out.printf("%d: %s %s\n", i, user.getFirstName(), user.getLastName());
                        i++;
                    }
                    //Get user choice by index
                    User user = users.get(InputManager.intInput(1, users.size())-1);
                    //DEBUG
                    System.out.printf("User: %s %s\n", user.getFirstName(), user.getLastName());

                    System.out.println("1. Add transaction");
                    System.out.println("2. Remove transaction");
                    System.out.println("3. Edit transaction");
                    System.out.println("Q. Quit");
                    int transactionOption = InputManager.intInput(1, 3);

                    switch (transactionOption) {
                        case 1:
                            System.out.println("NEW TRANSACTION\n" +
                                    "----------------");

                            System.out.println("Enter the total sum: ");
                            Float sum = InputManager.floatInput();

                            System.out.println("Enter category: ");
                            List<EExpenseCategory> expenseCategories = Arrays.stream(EExpenseCategory.values()).toList();
                            i = 0;
                            for (EExpenseCategory expenseCategory : expenseCategories) {
                                i++;
                                System.out.printf("%d: %s\n", i, expenseCategory);
                            }
                            int categoryID = InputManager.intInput(1, i);
                            EExpenseCategory category = expenseCategories.get(categoryID - 1);

                            Expense expense = new Expense(sum, LocalDate.now(), user, category);
                            ExpenseStorage.addExpense(expense);
                            System.out.printf("%s %s have added a new transaction\n" +
                                            "Sum: %.2f\n" +
                                            "Category: %s\n" +
                                            "Date: %s\n",
                                    expense.getUser().getFirstName(),
                                    expense.getUser().getLastName(),
                                    expense.getAmount(),
                                    expense.getCategory(),
                                    expense.getDate().toString());
                            continue;

                        case 2:
                            System.out.println("REMOVE TRANSACTION\n" +
                                    "-------------------");
                            List<Expense> expenses = ExpenseStorage.getExpenses();
                            System.out.println("Enter year of transaction: ");
                            int year = InputManager.intInput();
                            System.out.println("Enter month of transaction: ");
                            int month = InputManager.intInput();
                            System.out.println("Enter day of month of transaction: ");
                            int date = InputManager.intInput();
                            LocalDate transactionDate = LocalDate.of(year, month, date);

                            for (Expense expense1 : expenses) {
                                if (expense1.getDate().equals(transactionDate)) {
                                    System.out.println(expense1);
                                }
                            }
                            continue;
                    }
                case 2:


                case 3:

                case 4:

                case -1:
                    System.exit(0);
            }

        }
    }
}
