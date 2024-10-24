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
        printUsers();
        saveUsers();
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void printUsers() {
        getUsers();
        System.out.println("USERS: ");
        for (String key : users.keySet()) {
            System.out.printf("%s: %s %s\n", users.get(key).getId(), users.get(key).getFirstName(), users.get(key).getLastName());
        }
        System.out.println("-----------------------------------------");
    }

    //Iterate HashMap - users to find int ID
    public User getUser() {
        loadUsers();
        printUsers();
        System.out.println("Enter ID to get: ");
        Integer id = InputManager.intInput();
        for (String key : users.keySet()) {
            if (id.toString().equals(key.toString())) {
                System.out.println(users.get(key).getFirstName() + " " + users.get(key).getLastName());
                return users.get(key);
            }
        }
        System.out.println("ID: " + id + " not found");
        return new User(null, null);
    }

    public void loadUsers() {
        Type type = new TypeToken<Map<String, User>>() {}.getType();
        String filePath = "src/main/UserStorage.json";
        try {
            FileReader fr = new FileReader(filePath);
            users = gson.fromJson(fr, type);
            if (users == null) {
                users = new HashMap<>();
                System.out.println("DEBUG EMPTY");
            }
            System.out.println("Users have been loaded");
        } catch (Exception e) {
            System.out.println("UserStorage not found");
        }
    }

    public void saveUsers() throws IOException {
        String filePath = "src/main/UserStorage.json";
        FileWriter fileWriter = new FileWriter(filePath);
        gson.toJson(users, fileWriter);
        fileWriter.close();
        System.out.println("Users have been saved");
    }


    /*
    private final String filePath = "src/main/UserStorage.json";

    public Map<Integer, User> users;
    private int index = 1;

    public UserManager() {
        this.users = new HashMap<>();
    }
    DONE
    public void addUser(User user) {
        this.users.put(index++, user);
        System.out.println("User added.");
    }

    public void loadUsers() {
        Gson gson = new Gson();
        try {
            FileReader fr = new FileReader(filePath);
            users = gson.fromJson(fr, HashMap.class);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void saveUsers() throws IOException {
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter(filePath);
        gson.toJson(users, fileWriter);
        fileWriter.close();
        System.out.println("Users have been saved");
    }
    DONE
    public Map<Integer, User> getUsers() {
        return users;
    }
    DONE
    public void printUsers() {
        loadUsers();
        System.out.println("USERS: ");
        for (Integer key : users.keySet()) {
            System.out.println(key.toString() + ": " + users.get(key).toString());
        }
        System.out.println("-----------------------------------------");
    }
    DONE
    public User getUser(Integer ID) {
        loadUsers();
        for (Integer key : users.keySet()) {
            if (ID.toString().equals(key.toString())) {
                return users.get(key);
            }
        }
        System.out.println("ID: " + ID + " not found");
        return new User("", "");
    }
    */

}
