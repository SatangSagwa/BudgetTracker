package BankApp;

import java.util.HashMap;

public class UserManager {
    public HashMap<StringBuilder, User> users = new HashMap<>();
    public StringBuilder userID = new StringBuilder("00");
    public static int index = 1;

    public HashMap<StringBuilder, User> getUsers() {
        return users;
    }

    public void printUsers() {
        System.out.println("USERS: ");
        for (StringBuilder key : users.keySet()) {
            System.out.println(key.toString() + " " + users.get(key).getFirstName() + " " + users.get(key).getLastName());
        }
        System.out.println("-----------------------------------------");
    }

    public User getUser(StringBuilder ID) {
        for (StringBuilder key : users.keySet()) {
            if (ID.toString().equals(key.toString())) {
                return users.get(key);
            }
        }
        System.out.println("ID: " + ID + " not found");
        return new User("", "");
    };



    public void addUser(User user) {
        StringBuilder id = userID.append(index++);
        getUsers().put(new StringBuilder(id), user);
        System.out.println("User with ID: " + id + " has been added to the user list");
        userID.deleteCharAt(2);
    }
}
