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
            userManager.printUsers();
            //userManager.saveUsers();
        } else {
            //userManager.loadUsers();
            //userManager.printUsers();
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

            }
        }
    }
}
