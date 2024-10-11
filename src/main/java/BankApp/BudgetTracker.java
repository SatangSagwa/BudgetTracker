package BankApp;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;

public class BudgetTracker {

    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        UserManager userManager = new UserManager();
        System.out.println(userManager.userID);

        userManager.addUser(new User("Emilia", "I"));
        userManager.addUser(new User("Em", "II"));
        userManager.getUsers().put(new StringBuilder("003"), new User ("Jacy", "Bun"));
        userManager.printUsers();
        User testUser = userManager.getUser(new StringBuilder("004"));
        System.out.println(testUser.getFirstName());


        try {
            FileReader fr = new FileReader("src/main/ExpenseStorage.json");
           // users.add(gson.fromJson(fr, User.class));
        } catch (IOException e) {
            e.printStackTrace();
        }



        System.out.println("Welcome to the budget tracker!\n" +
                           "------------------------------");


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
