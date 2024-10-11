package BankApp;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class ExpenseStorage {
    private final Gson gson = new Gson();
    private final String filePath = "src/main/ExpenseStorage.json";

    private HashMap<StringBuilder, Expense> expenses = new HashMap<>();
    private StringBuilder expenseID = new StringBuilder("00");
    private int index = 1;

    public void loadExpenses() {
        Gson gson = new Gson();
        try {
            FileReader fr = new FileReader(filePath);
            expenses.put(gson.fromJson(filePath, StringBuilder.class), gson.fromJson(filePath, Expense.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addExpense(Expense expense) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        StringBuilder id = expenseID.append(index++);
        expenses.put(new StringBuilder(id), expense);
        gson.toJson(expenses, fileWriter);
        fileWriter.close();
        expenseID.deleteCharAt(2);

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
        for (StringBuilder key : expenses.keySet()) {
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

    public HashMap<StringBuilder, Expense> getExpenses() {
        return expenses;
    }

    public void listExpenses() {
        for (StringBuilder key : expenses.keySet()) {
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

}
