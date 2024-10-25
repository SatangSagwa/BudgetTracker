package BankApp;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class BudgetTracker {
    private static final UserManager userManager = new UserManager();
    private static final ExpenseStorage expenseStorage = new ExpenseStorage();
    private static final IncomeStorage incomeStorage = new IncomeStorage();
    private static User user;


    public static void main(String[] args) throws IOException {
        startMenu();

        while (true) {
            int option = optionMenu("start");

            switch (option) {
                //Expenses
                case 1:
                    //Print expense options, get option input
                    option = optionMenu("expense");
                    switch (option) {
                        //Add a new expense
                        case 1:
                            addTransaction("expense");
                            break;

                        //Remove an expense
                        case 2:
                            removeTransaction("expense");
                            break;

                        case 3:
                            editTransaction("expense");
                            break;

                        case 4:
                            changeUser();
                            break;
                    }
                    break;
                //Incomes
                case 2:
                    option = optionMenu("income");
                    switch (option) {
                        //Add a new expense
                        case 1:
                            addTransaction("income");
                            break;

                        //Remove an expense
                        case 2:
                            removeTransaction("income");
                            break;

                        case 3:
                            editTransaction("income");
                            break;

                        case 4:
                            changeUser();
                            break;
                }
                    break;
                //Display history
                case 3:
                    option = optionMenu("history");
                    switch (option) {
                        //By Date
                        case 1:
                            viewTransactionHistory("all");
                            break;
                        //By User
                        case 2:
                            viewTransactionHistory("user");
                            break;
                        //By Category
                        case 3:
                            viewTransactionHistory("category");
                            break;
                    }
                    break;

                //Edit users
                case 4:
                    option = optionMenu("user");
                    switch (option) {
                        //View users
                        case 1:
                            userManager.printUsers();
                            break;
                        //Change user
                        case 2:
                            changeUser();
                            break;
                        //Edit user
                        case 3:
                            editUser();
                            break;
                        //Add user
                        case 4:
                            addUser();
                            break;
                        //Remove user
                        case 5:
                            removeUser();
                            break;
                }
                    break;

                case -1:
                    System.out.println("Terminating program...");
                    System.exit(0);
            }
        }
    }

    /*
    Prints welcome message
    Loads user map
    If there are no existing users, the user is prompted to add a new user.
     */
    private static void startMenu() throws IOException {
        System.out.println("Welcome to the budget tracker!\n" +
                "------------------------------");
        //Gets all users
        userManager.loadUsers();
        if (userManager.getUsers().isEmpty()) {
            System.out.println("There are no users!");
            System.out.println("Please add a new user");
            System.out.print("First Name: ");
            String firstName = InputManager.stringInput();
            System.out.print("\nLast Name: ");
            String lastName = InputManager.stringInput();
            user = new User(firstName, lastName);
            userManager.addUser(user);

        } else {
            //Choose existing user OR make new user?
            System.out.println("Choose an existing user");
            user = userManager.getUser();
            System.out.println("User has been set to: " + user);
        }
    }

    /*
    Prints menu of options depending on type parameter
    Type cases: start expense income history user
    Returns int input
     */
    private static int optionMenu(String type) {
        switch (type) {
            case "start" -> {
                System.out.println();
                System.out.println("Please enter your option: ");
                System.out.println("1. Edit expenses");
                System.out.println("2. Edit incomes");
                System.out.println("3. Display history");
                System.out.println("4. Edit users");
                System.out.println("Q. Quit");
            }
            case "expense" -> {
                System.out.println("EXPENSES: \n" +
                        "----------\n" +
                        "USER: " + user + "\n" +
                        "1. Add expense\n" +
                        "2. Remove expense\n" +
                        "3. Edit expense\n" +
                        "4. Change user\n" +
                        "Q. Go back");
            }
            case "income" -> {
                System.out.println("INCOMES: \n" +
                        "----------\n" +
                        "USER: " + user + "\n" +
                        "1. Add income\n" +
                        "2. Remove income\n" +
                        "3. Edit income\n" +
                        "4. Change user\n" +
                        "Q. Go back");
            }
            case "history" -> {
                System.out.println("HISTORY: \n" +
                        "-------------");
                System.out.println("1. View all\n" +
                        "2. View by user\n" +
                        "3. View by category\n" +
                        "Q. Go back");
                return InputManager.intInput(1, 3);
            }

            case "user" -> {
                System.out.println("USERS: \n" +
                        "------------");
                System.out.println("1. View all users\n" +
                        "2. Change user\n" +
                        "3. Edit existing user\n" +
                        "4. Add new user\n" +
                        "5. Remove existing user\n" +
                        "Q. Go back");
                return InputManager.intInput(1, 5);
            }
        }
        //Default return
        return InputManager.intInput(1, 4);
    }

    /*
    Prints user list
    Gets user from users map by int input for id
    Returns user with specified id
    Sets current user to returned user.
     */
    private static void changeUser() {
        System.out.println("Current user: " + user);
        user = userManager.getUser();
        System.out.println("User has been set to: " + user);
    }

    /*
    Gets inputs for name
    Instantiates new user with input values
    Adds user to users map
     */
    private static void addUser() throws IOException {
        System.out.println("Enter first name: ");
        String firstName = InputManager.stringInput();
        System.out.println("Enter last name: ");
        String lastName = InputManager.stringInput();

        User newUser = new User(firstName, lastName);
        userManager.addUser(newUser);
        System.out.printf("%s %s has been added to the user list.", newUser.getFirstName(), newUser.getLastName());
    }

    /*
    Gets user by id
    If the user to remove is the same as the current user, it will not be removed.
    Else, gets user list and removes user
    Saves users map
     */
    private static void removeUser() throws IOException {
        System.out.println("Choose a user to remove: ");
        User userToRemove = userManager.getUser();
        if (userToRemove.getId() == user.getId()) {
            System.out.println("Can not remove current user: " + user +
                    "\nPlease change user and try again.");
        }
        else {
            String id = String.valueOf(userToRemove.getId());
            userManager.getUsers().remove(id);
            userManager.saveUsers();
            System.out.printf("%s has been removed from the user list.", userToRemove);
        }
    }

    private static void editUser() {
        System.out.println("Choose a user to edit: ");
        User editUser = userManager.getUser();
        System.out.println("Current first name: " + editUser.getFirstName());
        System.out.println("Enter new first name: ");
        String newFirstName = InputManager.stringInput();
        System.out.println("Current last name: " + editUser.getLastName());
        System.out.println("Enter new last name: ");
        String newLastName = InputManager.stringInput();
        editUser.setFirstName(newFirstName);
        editUser.setLastName(newLastName);
        System.out.printf("User has been changed to %s ", editUser);
    }

    private static void addTransaction(String type) {
        if (type.equals("expense")) {
            System.out.println("ADD EXPENSE: \n" +
                    "-------------");

        } else if (type.equals("income")) {
            System.out.println("ADD INCOME: \n" +
                    "-------------");
        }
        System.out.println("USER: " + user);
        //Input
        System.out.println("Enter the total sum: ");
        double sum = InputManager.doubleInput();
        System.out.println("Enter category: ");

        int category = 0;

        if (type.equals("expense")) {
            expenseStorage.listCategories();
            category = InputManager.intInput(1, EExpenseCategory.values().length);
        } else if (type.equals("income")) {
            incomeStorage.listCategories();
            category = InputManager.intInput(1, EIncomeCategory.values().length);
        }
        System.out.println("Enter year of transaction: ");
        int year = InputManager.intInput(2000, LocalDate.now().getYear() + 50);
        System.out.println("Enter month of transaction: ");
        int month = InputManager.intInput(1, 12);
        System.out.println("Enter day of transaction: ");
        int day = InputManager.intInput(1, 31);
        try {
            LocalDate date = LocalDate.of(year, month, day);
            //Adding expense to map
            if (type.equals("expense")) {
                Expense expense = new Expense(sum, user, date, EExpenseCategory.values()[category - 1]);
                expenseStorage.addExpense(expense);
                System.out.println("Expense successfully added!");
            } else if (type.equals("income")) {
                Income income = new Income(sum, user, date, EIncomeCategory.values()[category - 1]);
                incomeStorage.addIncome(income);
                System.out.println("Income successfully added!");
            }
        } catch (Exception e) {
            System.out.println("Invalid date!");
        }
    }

    private static void removeTransaction(String type) {
        //True if match is found for specified ID
        boolean hasMatch = false;
        if (type.equals("expense")) {
            System.out.println("REMOVE EXPENSE: \n" +
                    "--------------\n");
        } else if (type.equals("income")) {
            System.out.println("REMOVE INCOME: \n" +
                    "--------------\n");
        }

        System.out.println("Enter year of transaction to remove: ");
        int yearToRemove = InputManager.intInput();
        System.out.println("Enter the month of transaction to remove: ");
        int monthToRemove = InputManager.intInput();

        //Compare the date to all expenses, add matches to list
        if (type.equals("expense")) {
            List<Expense> matches = expenseStorage.findExpensesByDate(yearToRemove, monthToRemove);
            if (matches.isEmpty()) {
                System.out.println("No matches");
                //If there are matches:
            } else {
                //Print all matching expenses
                for (Expense expense : matches) {
                    System.out.printf(expense.toString());
                    System.out.println("-------------------------------------------------------");
                }
                //Input for ID to remove
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
        } else if (type.equals("income")) {
            List<Income> matches = incomeStorage.findIncomesByDate(yearToRemove, monthToRemove);
            if (matches.isEmpty()) {
                System.out.println("No matches");
                //If there are matches:
            } else {
                //Print all matching expenses
                for (Income income : matches) {
                    System.out.printf(income.toString());
                    System.out.println("-------------------------");
                }
                //Input for ID to remove
                System.out.println("Enter ID to remove: ");
                int id = InputManager.intInput();
                for (Income income : matches) {
                    if (income.getId() == id) {
                        expenseStorage.removeExpense(income.getId());
                        hasMatch = true;
                    }
                }
                if (hasMatch) {
                    System.out.println("Expense successfully removed!");
                } else {
                    System.out.println("ID not found!");
                }
            }
        }
    }

    private static void editTransaction(String type) throws IOException {
        if (type.equals("expense")) {
            System.out.println("EDIT EXPENSE: \n" +
                    "-------------\n");

        } else if (type.equals("income")) {
            System.out.println("EDIT INCOME: \n" +
                    "-------------\n");
        }
        boolean hasMatch = false;
        System.out.println("Enter year of transaction to edit: ");
        int yearToEdit = InputManager.intInput();
        System.out.println("Enter the month of transaction to edit: ");
        int monthToEdit = InputManager.intInput();

        if (type.equals("expense")) {
            List<Expense> matches = expenseStorage.findExpensesByDate(yearToEdit, monthToEdit);
            if (matches.isEmpty()) {
                System.out.println("No matches for specified date.");
            } else {
                for (Expense expense : matches) {
                    System.out.println(expense.toString());
                    System.out.println("-------------------------");
                }

                System.out.println("Enter ID to edit: ");
                int id = InputManager.intInput();
                for (Expense expense : matches) {
                    if (expense.getId() == id) {
                        hasMatch = true;
                    }
                    if (hasMatch) {
                        System.out.println("What would you like to edit?");
                        System.out.println("1. Amount\n" +
                                "2. Category\n" +
                                "3. User");
                        int editOption = InputManager.intInput(1, 3);
                        switch (editOption) {
                            case 1:
                                //Edit amount for an expense
                                System.out.println("Current amount: " + expense.getAmount());
                                System.out.println("Enter new amount: ");
                                int newAmount = InputManager.intInput();
                                expense.setAmount(newAmount);
                                expenseStorage.saveExpenses();
                                break;

                                //Edit category
                            case 2:
                                System.out.println("Current category: " + expense.getCategory());
                                System.out.println("Enter new category: ");
                                expenseStorage.listCategories();
                                int newCategory = InputManager.intInput(1, EExpenseCategory.values().length);
                                expense.setCategory(EExpenseCategory.values()[newCategory - 1]);
                                System.out.println("New category set to " + expense.getCategory());
                                expenseStorage.saveExpenses();
                                break;

                                //Edit user
                            case 3:
                                System.out.println("Current user: " + expense.getUser().getFirstName() + " " + expense.getUser().getLastName());
                                User newUser = userManager.getUser();
                                expense.setUser(newUser);
                                System.out.println("New user set to " + expense.getUser().getFirstName() + " " + expense.getUser().getLastName());
                                expenseStorage.saveExpenses();
                                break;
                        }
                        break;
                    }
                }
            }
            } else if (type.equals("income")) {
                List<Income> matches = incomeStorage.findIncomesByDate(yearToEdit, monthToEdit);
                if (matches.isEmpty()) {
                    System.out.println("No matches for specified date.");
                } else {
                    for (Income income : matches) {
                        System.out.println(income.toString());
                        System.out.println("-------------------------");
                    }

                    System.out.println("Enter ID to edit: ");
                    int id = InputManager.intInput();
                    for (Income income : matches) {
                        if (income.getId() == id) {
                            hasMatch = true;
                    }
                        if (hasMatch) {
                            System.out.println("What would you like to edit?");
                            System.out.println("1. Amount\n" +
                                    "2. Category\n" +
                                    "3. User");
                            int editOption = InputManager.intInput(1, 3);
                            switch (editOption) {
                                case 1:
                                    //Edit amount for an expense
                                    System.out.println("Current amount: " + income.getAmount());
                                    System.out.println("Enter new amount: ");
                                    double newAmount = InputManager.doubleInput();
                                    income.setAmount(newAmount);
                                    incomeStorage.saveIncomes();

                                    //Edit category
                                case 2:
                                    System.out.println("Current category: " + income.getCategory());
                                    System.out.println("Enter new category: ");
                                    incomeStorage.listCategories();
                                    int newCategory = InputManager.intInput(1, EIncomeCategory.values().length);
                                    income.setCategory(EIncomeCategory.values()[newCategory - 1]);
                                    System.out.println("New category set to " + income.getCategory());
                                    incomeStorage.saveIncomes();

                                    //Edit user
                                case 3:
                                    System.out.println("Current user: " + income.getUser().getFirstName() + " " + income.getUser().getLastName());
                                    User newUser = userManager.getUser();
                                    income.setUser(newUser);
                                    System.out.println("New user set to " + income.getUser().getFirstName() + " " + income.getUser().getLastName());
                                    incomeStorage.saveIncomes();
                            }
                        }
                }
            }
        }
    }

    private static void viewTransactionHistory(String type) {
        expenseStorage.loadExpenses();
        incomeStorage.loadIncomes();
        Map<String, Expense> expenses = expenseStorage.getExpenses();
        Map<String, Income> incomes = incomeStorage.getIncomes();

        System.out.println("Enter year of transactions to view ");
        int year = InputManager.intInput();
        System.out.println("Enter the month of transactions to view: ");
        int month = InputManager.intInput();

        List<Expense> expenseMatches = expenseStorage.findExpensesByDate(year, month);
        List<Income> incomeMatches = incomeStorage.findIncomesByDate(year, month);

        boolean expensesFound = false;
        boolean incomesFound = false;

        if (type.equals("all")) {
            System.out.println("1. View all transactions\n" +
                    "2. View all expenses\n" +
                    "3. View all incomes");

            int option = InputManager.intInput(1, 3);
            switch (option) {
                //All transactions
                case 1:
                    System.out.println("Expenses: ");
                    for (Expense expense : expenseMatches) {
                        System.out.printf(expense.toString());
                        expensesFound = true;
                    }

                    System.out.println("Incomes: ");
                    for (Income income : incomeMatches) {
                        System.out.printf(income.toString());
                        incomesFound = true;
                    }
                    break;

                //Expenses
                case 2:
                    incomesFound = true;
                    System.out.println("Expenses: ");
                    for (Expense expense : expenseMatches) {
                        System.out.printf(expense.toString());
                        expensesFound = true;
                    }
                    break;
                //Incomes
                case 3:
                    expensesFound = true;
                    System.out.println("Incomes: ");
                    for (Income income : incomeMatches) {
                        System.out.printf(income.toString());
                        incomesFound = true;
                    }
                    break;
                }

                if (!expensesFound && !incomesFound) {
                    System.out.println("No transactions found for specified date.");
                } else if (!expensesFound) {
                    System.out.println("No expenses found for specified date.");
                } else if (!incomesFound) {
                    System.out.println("No incomes found for specified date.");
                }


        }

        else if (type.equals("user")) {
            User user = userManager.getUser();


            System.out.println("Expenses: ");
            for (Expense expense : expenseMatches) {
                if (expense.getUser().getId() == user.getId()) {
                    System.out.printf(expense.toString());
                    expensesFound = true;
                }
            }

            System.out.println("Incomes: ");
            for (Income income : incomeMatches) {
                if (income.getUser().getId() == user.getId()) {
                    System.out.printf(income.toString());
                    incomesFound = true;
                }
            }

            if (!expensesFound && !incomesFound) {
                System.out.println("No transactions found for specified date.");
            } else if (!expensesFound) {
                System.out.println("No expenses found for specified date.");
            } else if (!incomesFound) {
                System.out.println("No incomes found for specified date.");
            }

        } else if (type.equals("category")) {
            System.out.println("Which type of category do you want to search for?");
            System.out.println("1. Expense\n" +
                    "2. Income");

            int option = InputManager.intInput(1, 2);
            int category;
            switch (option) {
                //Expenses
                case 1:
                    expenseStorage.listCategories();
                    category = InputManager.intInput(1, EExpenseCategory.values().length);
                    incomesFound = true;

                    System.out.println("Expenses: ");
                    for (Expense expense : expenseMatches) {
                        if (expense.getCategory().ordinal() == category) {
                            System.out.printf(expense.toString());
                            expensesFound = true;
                        }
                    }
                    break;
                //Incomes
                case 2:
                    incomeStorage.listCategories();
                    category = InputManager.intInput(1, EIncomeCategory.values().length);
                    expensesFound = true;

                    System.out.println("Incomes: ");
                    for (Income income : incomeMatches) {
                        if (income.getCategory().ordinal() == category) {
                            System.out.printf(income.toString());
                            incomesFound = true;
                    }
                }
                    break;
            }
            if (!expensesFound && !incomesFound) {
                System.out.println("No transactions found for specified date.");
            } else if (!expensesFound) {
                System.out.println("No expenses found for specified date.");
            } else if (!incomesFound) {
                System.out.println("No incomes found for specified date.");
            }
        }
    }
}
