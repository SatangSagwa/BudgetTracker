package BankApp;

import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private Map <Integer, User> users = new HashMap<Integer, User>();
    private int index = 1;

    public void addUser (User user) {
        users.put(index++, user);
        System.out.printf("%s %s have been added to the user list.",
                            user.getFirstName(),
                            user.getLastName());
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    public void printUsers() {
        System.out.println("USERS: ");
        for (Integer key : users.keySet()) {
            System.out.println(key.toString() + ": " + users.get(key).getFirstName() + " " + users.get(key).getLastName());
        }
        System.out.println("-----------------------------------------");
    }

    //Iterate HashMap - users to find int ID
    public User getUser(Integer ID) {
        //loadUsers();
        for (Integer key : users.keySet()) {
            if (ID.toString().equals(key.toString())) {
                return users.get(key);
            }
        }
        System.out.println("ID: " + ID + " not found");
        return new User(null, null);
    }


    /*
    private final String filePath = "src/main/UserStorage.json";

    public Map<Integer, User> users;
    private int index = 1;

    public UserManager() {
        this.users = new HashMap<>();
    }

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

    public Map<Integer, User> getUsers() {
        return users;
    }

    public void printUsers() {
        loadUsers();
        System.out.println("USERS: ");
        for (Integer key : users.keySet()) {
            System.out.println(key.toString() + ": " + users.get(key).toString());
        }
        System.out.println("-----------------------------------------");
    }

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
