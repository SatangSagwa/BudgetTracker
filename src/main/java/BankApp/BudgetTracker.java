package BankApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BudgetTracker {

    public static void main(String[] args) throws IOException {
        UserManager userManager = new UserManager();
        ExpenseStorage expenseStorage = new ExpenseStorage();

        System.out.println("Welcome to the budget tracker!\n" +
                           "------------------------------");
        userManager.loadUsers();
        User user;

        if (userManager.getUsers().isEmpty()) {
            userManager.addUser(new User("Test", "Testson"));
            System.out.println("There are no users!");
            System.out.println("Please add a new user");
            System.out.print("First Name: ");
            String firstName = InputManager.stringInput();
            System.out.print("\nLast Name: ");
            String lastName = InputManager.stringInput();
            user = new User(firstName, lastName);
            userManager.addUser(user);
        } else {
            user = userManager.getUser();
        }

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
                    System.out.println("EXPENSES: \n" +
                            "----------\n" +
                            "USER: " + user.getFirstName() + " " + user.getLastName() + "\n" +
                            "1. Add expense\n" +
                            "2. Remove expense\n" +
                            "3. Edit expense\n" +
                            "4. Display expense history\n" +
                            "5. Change user");
                    option = InputManager.intInput(1, 4);

                    switch (option) {
                        case 1:
                            System.out.println("ADD EXPENSE: \n" +
                                    "-------------\n" +
                                    "USER: " + user.getFirstName() + " " + user.getLastName());
                            System.out.println("Enter the total sum: ");
                            int sum = InputManager.intInput();
                            System.out.println("Enter expense category: ");
                            expenseStorage.listCategories();
                            int category = InputManager.intInput(1, EExpenseCategory.values().length);
                            expenseStorage.addExpense(new Expense(sum, user, EExpenseCategory.values()[category - 1]));
                            System.out.println("Expense successfully added!");
                            //expenseStorage.listExpenses();
                            break;

                        case 2:
                            boolean hasMatch = false;
                            System.out.println("REMOVE EXPENSE: \n" +
                                    "-------------\n");
                            System.out.println("Enter year of transaction to remove: ");
                            int year = InputManager.intInput();
                            System.out.println("Enter the month of transaction to remove: ");
                            int month = InputManager.intInput();
                            List<Expense> matches = expenseStorage.findExpensesByDate(year, month);
                            if (matches.isEmpty()) {
                                System.out.println("No matches");
                            } else {
                                for (Expense expense : matches) {
                                    System.out.println(expense.toString());
                                    System.out.println("-------------------------");
                                }
                                System.out.println("Enter ID to remove: ");
                                int id = InputManager.intInput();
                                for (Expense expense : matches) {
                                    if (expense.getId() == id) {
                                        expenseStorage.removeExpense(expense.getId());
                                        hasMatch = true;
                                    }
                                }
                                if (hasMatch) {
                                    System.out.println("Expense successfully removed!");
                                } else {
                                    System.out.println("ID not found!");
                                }
                            }
                            break;
                        case 3:
                            System.out.println("EDIT EXPENSE: \n" +
                                                "-------------\n");
                            boolean editHasMatch = false;
                            System.out.println("Enter year of transaction to edit: ");
                            int yearToEdit = InputManager.intInput();
                            System.out.println("Enter the month of transaction to edit: ");
                            int monthToEdit = InputManager.intInput();
                            List<Expense> matches1 = expenseStorage.findExpensesByDate(yearToEdit, monthToEdit);

                            if (matches1.isEmpty()) {
                                System.out.println("No matches for specified date.");
                            } else {
                                for (Expense expense : matches1) {
                                    System.out.println(expense.toString());
                                    System.out.println("-------------------------");
                                }

                                System.out.println("Enter ID to edit: ");
                                int id = InputManager.intInput();
                                for (Expense expense : matches1) {
                                    if (expense.getId() == id) {
                                        editHasMatch = true;
                                    }

                                    if (editHasMatch) {
                                        System.out.println("What would you like to edit?");
                                        System.out.println("1. Amount\n" +
                                                "2. Category\n" +
                                                "3. User");
                                        int editOption = InputManager.intInput(1, 3);
                                        switch (editOption) {
                                            case 1:
                                                System.out.println("Current amount: " + expense.getAmount());
                                                System.out.println("Enter new amount: ");
                                                int newAmount = InputManager.intInput();
                                                expense.setAmount(newAmount);
                                                expenseStorage.saveExpenses();
                                                break;

                                            case 2:
                                                System.out.println("Current category: " + expense.getCategory());
                                                System.out.println("Enter new category: ");
                                                expenseStorage.listCategories();
                                                int newCategory = InputManager.intInput(1, EExpenseCategory.values().length);
                                                expense.setCategory(EExpenseCategory.values()[newCategory - 1]);
                                                System.out.println("New category set to " + expense.getCategory());
                                                expenseStorage.saveExpenses();
                                                continue;

                                            case 3:
                                                System.out.println("Current user: " + expense.getUser().getFirstName() + " " + expense.getUser().getLastName());
                                                User newUser = userManager.getUser();
                                                expense.setUser(newUser);
                                                System.out.println("New user set to " + expense.getUser().getFirstName() + " " + expense.getUser().getLastName());
                                                expenseStorage.saveExpenses();
                                        }
                                    } else {
                                        System.out.println("ID not found!");
                                    }
                                }
                            } break;
                        case 4:
                            System.out.println("EXPENSE HISTORY: \n" +
                                    "-------------\n");
                            System.out.println("1. View all\n" +
                                    "2. View by date" +
                                    "3. View by user" +
                                    "4. View by category");
                            int historyOption = InputManager.intInput(1, 4);
                            switch (historyOption) {
                                case 1:
                                    expenseStorage.listExpenses();
                                    break;

                                case 2:
                                    System.out.println("Enter year to view: ");
                                    int historyYear = InputManager.intInput();
                                    System.out.println("Enter the month of transaction to edit: ");
                                    int historyMonth = InputManager.intInput();
                                    List<Expense> historyMatches = expenseStorage.findExpensesByDate(historyYear, historyMonth);

                                    if (historyMatches.isEmpty()) {
                                        System.out.println("No matches for specified date.");
                                    } else {
                                        for (Expense expense : historyMatches) {
                                            System.out.println(expense.toString());
                                            System.out.println("-------------------------");
                                        }
                                    }
                                    break;
                                case 3:
                                    User newUser = userManager.getUser();
                                    List<Expense> userMatches = new ArrayList<Expense>();

                                    for (String key : expenseStorage.getExpenses().keySet()) {
                                        if(expenseStorage.getExpenses().get(key).getUser() == newUser) {
                                            userMatches.add(expenseStorage.getExpenses().get(key));
                                        }
                                    }

                                    if (userMatches.isEmpty()) {
                                        System.out.println("No matches found for " + newUser.getFirstName() + " " + newUser.getLastName());
                                    } else {
                                        for (Expense expense : userMatches) {
                                            System.out.println(expense.toString());
                                        }
                                    }
                            }


                    }
                case 2:

                case 3:

                case 4:

                case -1:
                    //System.exit(0);
            }
        }
    }
}
