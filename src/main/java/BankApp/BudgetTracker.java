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
                        //View all
                        case 1:
                            viewAllTransactionHistory();
                        //By date
                        case 2:
                            viewTransactionHistoryByDate();
                        //By user
                        case 3:
                            viewTransactionHistoryByUser();
                        //By category
                        case 4:
                            viewTransactionHistoryByCategory();
                    }

                //Edit users
                case 4:
            }
        }
    }
    /*
    Prints welcome message
    Loads json for users, incomes and expenses
    If there are no existing users, the user is prompted to add a new user.

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
                                        if (expenseStorage.getExpenses().get(key).getUser() == newUser) {
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
     */
    private static void startMenu() throws IOException {
        System.out.println("Welcome to the budget tracker!\n" +
                "------------------------------");
        userManager.loadUsers();
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
            //Choose existing user OR make new user?
            System.out.println("Choose an existing user");
            user = userManager.getUser();
        }
    }

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
                        "USER: " + user.getFirstName() + " " + user.getLastName() + "\n" +
                        "1. Add expense\n" +
                        "2. Remove expense\n" +
                        "3. Edit expense\n" +
                        "4. Change user");
            }
            case "income" -> {
                System.out.println("INCOMES: \n" +
                        "----------\n" +
                        "USER: " + user.getFirstName() + " " + user.getLastName() + "\n" +
                        "1. Add income\n" +
                        "2. Remove income\n" +
                        "3. Edit income\n" +
                        "4. Change user");
            }
            case "history" -> {
                System.out.println("HISTORY: \n" +
                        "-------------\n");
                System.out.println("1. View all\n" +
                        "2. View by date" +
                        "3. View by user" +
                        "4. View by category");
            }
        }
        return InputManager.intInput(1, 4);
    }

    private static void addTransaction(String type) {
        if (type.equals("expense")) {
            System.out.println("ADD EXPENSE: \n" +
                    "-------------\n" +
                    "USER: " + user.getFirstName() + " " + user.getLastName());

        } else if (type.equals("income")) {
            System.out.println("ADD INCOME: \n" +
                    "-------------\n" +
                    "USER: " + user.getFirstName() + " " + user.getLastName());
        }
        //Input
        System.out.println("Enter the total sum: ");
        int sum = InputManager.intInput();
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
        int year = InputManager.intInput(2000, 2030);
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
        } else
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
                    System.out.println(expense.toString());
                    System.out.println("-------------------------");
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
                    System.out.println(income.toString());
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
                                    int newAmount = InputManager.intInput();
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
        if (!hasMatch) {
            System.out.println("No matches for specified date.");
        }
    }

    private static void changeUser() {
        user = userManager.getUser();
    }

    private static void viewAllTransactionHistory() {
        expenseStorage.loadExpenses();
        incomeStorage.loadIncomes();
        Map<String, Expense> expenses = expenseStorage.getExpenses();
        Map<String, Income> incomes = incomeStorage.getIncomes();

        System.out.println("1. View all transactions\n" +
                "2. View all expenses\n" +
                "3. View all incomes");

        int option = InputManager.intInput(1, 3);
        switch (option) {
            case 1:
                System.out.println("All transactions: ");
                if (expenses.isEmpty() && incomes.isEmpty()) {
                    System.out.println("There is no transaction history");
                } else {
                    System.out.println("Expenses:");
                    expenseStorage.listExpenses();
                    System.out.println("--------------------------------------------");
                    System.out.println("Incomes:");
                    incomeStorage.listIncomes();

                }
                break;

            case 2:
                System.out.println("All expenses: ");
                expenseStorage.listExpenses();
                break;

            case 3:
                System.out.println("All incomes: ");
                incomeStorage.listIncomes();
                break;
        }
    }
}