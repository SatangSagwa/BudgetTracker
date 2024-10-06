package BankApp;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BudgetTracker {

    public static void main(String[] args) throws IOException {

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
                            //List categories
                            for (EExpenseCategory expenseCategory : expenseCategories) {
                                i++;
                                System.out.printf("%d: %s\n", i, expenseCategory);
                            }
                            int categoryID = InputManager.intInput(1, i);
                            EExpenseCategory category = expenseCategories.get(categoryID - 1);

                            //Create expense with specified values
                            Expense expense = new Expense(sum, LocalDate.now(), user, category);
                            //Add expense to expenses array and save to json
                            ExpenseStorage.addExpense(expense);
                            continue;

                        case 2:
                            System.out.println("REMOVE TRANSACTION\n" +
                                    "-------------------");
                            System.out.println("Enter year of transaction: ");
                            int year = InputManager.intInput();
                            System.out.println("Enter month of transaction: ");
                            int month = InputManager.intInput();
                            //Set days to 1 to only compare year and month
                            LocalDate searchDate = LocalDate.of(year, month, 1);

                            //List for all expenses
                            List<Expense> expenses = ExpenseStorage.getExpenses();
                            //Empty list only for matching expenses
                            List<Expense> expenseMatches = new ArrayList<>();

                            //Iterate expenses
                            for (Expense expense1 : expenses) {
                                //Get date of each expense
                                LocalDate expenseDate = expense1.getDate();
                                //Set dateMinusDays to only compare years and months
                                LocalDate dateMinusDays = LocalDate.of(expenseDate.getYear(), expenseDate.getMonth(), 1);
                                //Add to matches list if it matches the search date
                                if (dateMinusDays.equals(searchDate)) {
                                    expenseMatches.add(expense1);
                                }
                            }

                            if (expenseMatches.isEmpty()) {
                                System.out.printf("No transactions found at %s - %s\n", year, month);
                            } else {
                                System.out.println("Matches:");
                                int j = 0;
                                for (Expense expense1 : expenseMatches) {
                                    j++;
                                    System.out.printf("%d: Date: %s - Amount: %s - Category: %s\n",
                                            j,
                                            expense1.getDate(),
                                            expense1.getAmount(),
                                            expense1.getCategory());
                                }
                                System.out.println("Which transaction would you like to remove?");
                                int idToRemove = InputManager.intInput(1, expenseMatches.size());
                                ExpenseStorage.removeExpense(expenseMatches.get(idToRemove-1));
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
