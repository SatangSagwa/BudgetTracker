package BankApp;

import java.io.IOException;

public class BudgetTracker {

    public static void main(String[] args) throws IOException {
        UserManager userManager = new UserManager();
        //Gson gson = new Gson();
        //userManager.loadUsers();
        ExpenseStorage expenseStorage = new ExpenseStorage();

        //userManager.addUser(new User("Emilia", "I"));
        //userManager.addUser(new User("Em", "II"));
        //userManager.printUsers();

        //ONLY FOR TESTING!
        //User testUser = userManager.getUser(new StringBuilder("002"));
        //System.out.println(testUser.getFirstName());
        //ONLY FOR TESTING!


        System.out.println("Welcome to the budget tracker!\n" +
                           "------------------------------");


        if (userManager.getUsers().isEmpty()) {
            System.out.println("There are no users!");
            System.out.println("Please add a new user");
            System.out.print("First Name: ");
            String firstName = InputManager.stringInput();
            System.out.print("\nLast Name: ");
            String lastName = InputManager.stringInput();
            User newUser = new User(firstName.toString(), lastName.toString());
            userManager.addUser(newUser);
            userManager.addUser((new User("Test", "TestYson")));
        } else {
            //userManager.loadUsers();
            //userManager.printUsers();
        }
        userManager.printUsers();
        //userManager.saveUsers();
        System.out.println("Enter ID to get: ");
        int id = InputManager.intInput();
        User getUser = userManager.getUser(id);
        System.out.println(getUser.getFirstName() + " " + getUser.getLastName());

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
                            "USER: " + getUser.getFirstName() + " " + getUser.getLastName() + "\n" +
                            "1. Add expense\n" +
                            "2. Remove expense\n" +
                            "3. Edit expense\n" +
                            "4. Display expense history");
                    option = InputManager.intInput(1, 4);
                    switch (option) {
                        case 1:
                            System.out.println("ADD EXPENSE: \n" +
                                               "-------------\n" +
                                    "USER: " + getUser.getFirstName() + " " + getUser.getLastName());
                            System.out.println("Enter the total sum: ");
                            int sum = InputManager.intInput();
                            expenseStorage.listCategories();

                            //expenseStorage.addExpense(new Expense());
                    }
            }
        }
    }
}
