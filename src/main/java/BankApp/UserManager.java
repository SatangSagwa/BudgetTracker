package BankApp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private Map<String, User> users = new HashMap<>();
    private Integer index = 0;

    /*
    Param user - user to add to users map
    Loads user map
    Iterates users map to find largest id
    Sets user id to index + 1
    Saves user map to json
     */
    public void addUser (User user) throws IOException {
        loadUsers();
        for (String key : users.keySet()) {
            if (index <= users.get(key).getId()) {
                index = users.get(key).getId();
            }
        }
        index++;
        user.setId(index);
        String str = index.toString();
        users.put(str, user);
        System.out.printf("%s %s have been added to the user list.\n",
                            user.getFirstName(),
                            user.getLastName());
        saveUsers();
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void printUsers() {
        getUsers();
        for (String key : users.keySet()) {
            System.out.printf("%s: %s %s\n", users.get(key).getId(), users.get(key).getFirstName(), users.get(key).getLastName());
        }
        System.out.println("-----------------------------------------");
    }

    /*
    Loads and prints all users
    Gets id to get by input
    Iterates users map to find and return user with id
    else print error message
     */
    public User getUser() {
        loadUsers();
        printUsers();

        while (true) {
            System.out.println("Enter ID to get: ");
            Integer id = InputManager.intInput();
            for (String key : users.keySet()) {
                if (id.toString().equals(key.toString())) {
                    return users.get(key);
                }
            }
            System.out.println("ID: " + id + " not found");
        }
    }

    /*
    Sets type to map to read json as correct values
    Try set users to json UserStorage
        If null, declare as hashmap to prevent it from being null
     */
    public void loadUsers() {
        Type type = new TypeToken<Map<String, User>>() {}.getType();
        String filePath = "src/main/UserStorage.json";
        try {
            FileReader fr = new FileReader(filePath);
            users = gson.fromJson(fr, type);
            if (users == null) {
                users = new HashMap<>();
            }
        } catch (Exception e) {
            System.out.println("UserStorage not found");
        }
    }

    /*
    Saves users map to json UserStorage
     */
    public void saveUsers() throws IOException {
        String filePath = "src/main/UserStorage.json";
        FileWriter fileWriter = new FileWriter(filePath);
        gson.toJson(users, fileWriter);
        fileWriter.close();
    }
}
