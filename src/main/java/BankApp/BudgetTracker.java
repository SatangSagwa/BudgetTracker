package BankApp;

import java.io.IOException;

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
                            //LocalDate date = LocalDate.now();
                            expenseStorage.addExpense(new Expense(sum, user, EExpenseCategory.values()[category-1]));
                            expenseStorage.listExpenses();
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
