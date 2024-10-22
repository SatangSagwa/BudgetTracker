package BankApp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ExpenseStorage {
    private final Gson gson = new Gson();
    private final String filePath = "src/main/ExpenseStorage.json";

    private Map<String, Transaction> expenses = new HashMap<>();
    private StringBuilder expenseID = new StringBuilder("00");
    private int index = 1;

    public Map<String, Transaction> getExpenses() {
        return expenses;
    }

    public void listExpenses() {
        for (String key : expenses.keySet()) {
            System.out.println(key.toString() + ": " + expenses.get(key).toString());
        }

        for (Transaction transaction : expenses.values()) {
            System.out.printf("%s - User: %s %s - Sum: %.2f",
                    transaction.getDate().toString(),
                    transaction.getUser().getFirstName(),
                    transaction.getUser().getLastName(),
                    transaction.getAmount());
        }
    }

    public void listCategories() {
        int i = 1;
        for (EExpenseCategory category : EExpenseCategory.values()) {
            System.out.println(i + ": " + category);
            i++;
        }
    }

    public void addExpense(Transaction expense) throws IOException {
        loadExpenses();
        StringBuilder id = expenseID.append(index++);
        expenses.put(new StringBuilder(id).toString(), expense);
        expenseID.deleteCharAt(2);
        saveExpenses();
        System.out.printf("%s %s have added a new transaction\n" +
                        "Sum: %.2f\n" +
                        //"Category: %s\n" +
                        "Date: %s\n",
                expense.getUser().getFirstName(),
                expense.getUser().getLastName(),
                expense.getAmount(),
                //expense.getCategory(),
                expense.getDate().toString());
    }

    public void loadExpenses() {
        Type type = new TypeToken<Map<String, Expense>>() {}.getType();
        try {
            FileReader fr = new FileReader(filePath);
            expenses = gson.fromJson(fr, type);
            System.out.println("Expenses loaded");
        } catch (Exception e) {
            System.out.println("Expenses not found!");
        }
    }

    public void saveExpenses() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        gson.toJson(expenses, fw);
        fw.close();
        System.out.println("Expenses saved");
    }

    /*
    private final Gson gson = new Gson();
    private final String filePath = "src/main/ExpenseStorage.json";

    private HashMap<String, Expense> expenses = new HashMap<>();
    private StringBuilder expenseID = new StringBuilder("00");
    private int index = 1;

    public void loadExpenses() {
        Type type = new TypeToken<Map<String, Expense>>() {}.getType();
        try {
            FileReader fr = new FileReader(filePath);
            expenses = gson.fromJson(fr, type);
            System.out.println("Expenses loaded");
        } catch (Exception e) {
            System.out.println("Expenses not found!");
        }
    }

    public void addExpense(Expense expense) throws IOException {
        loadExpenses();
        FileWriter fileWriter = new FileWriter(filePath);
        StringBuilder id = expenseID.append(index++);
        expenses.put(new StringBuilder(id).toString(), expense);
        gson.toJson(expenses, fileWriter);
        fileWriter.close();
        expenseID.deleteCharAt(2);
        System.out.println("Expenses have been saved.");

        System.out.printf("%s %s have added a new transaction\n" +
                "Sum: %.2f\n" +
                "Category: %s\n" +
                "Date: %s\n",
                expense.getUser().getFirstName(),
                expense.getUser().getLastName(),
                expense.getAmount(),
                expense.getCategory(),
                expense.getDate().toString());
    }

    public Expense getExpense(StringBuilder ID) {
        for (String key : expenses.keySet()) {
            if (ID.toString().equals(key.toString())) {
                return expenses.get(key);
            }
        }
        System.out.println("ID: " + ID + " not found!");
        return null;
    }


    public void removeExpense(Expense expense) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        expenses.remove(expense);
        gson.toJson(expenses, fileWriter);
        fileWriter.close();
        System.out.printf("Transaction: %s has successfully been removed.", expense.toString());
    }

    public HashMap<String, Expense> getExpenses() {
        return expenses;
    }

    public void listExpenses() {
        for (String key : expenses.keySet()) {
            System.out.println(key.toString() + ": " + expenses.get(key).toString());
        }

        for (Transaction transaction : expenses.values()) {
            System.out.printf("%s - User: %s %s - Sum: %.2f",
                    transaction.getDate().toString(),
                    transaction.getUser().getFirstName(),
                    transaction.getUser().getLastName(),
                    transaction.getAmount());
        }
    }

    public void listCategories() {
        int i = 1;
        for (EExpenseCategory category : EExpenseCategory.values()) {
            System.out.println(i + ": " + category);
            i++;
        }
    }
*/
}
