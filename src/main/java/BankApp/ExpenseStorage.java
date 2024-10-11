package BankApp;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class ExpenseStorage {
    private static final Gson gson = new Gson();
    private static final String filePath = "src/main/ExpenseStorage.json";

    private static HashMap<String, Expense> expenses = new HashMap<>();

    public static void loadExpenses() {
        Gson gson = new Gson();
        try {
            FileReader fr = new FileReader("src/main/ExpenseStorage.json");
            expenses.put(gson.fromJson(fr, String.class), gson.fromJson(fr, Expense.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addExpense(String id, Expense expense) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        expenses.put(id, expense);
        gson.toJson(expenses, fileWriter);
        fileWriter.close();

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

    public static Expense getExpense(Expense expense) {
        return expense;
    }


    public static void removeExpense(Expense expense) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        expenses.remove(expense);
        gson.toJson(expenses, fileWriter);
        fileWriter.close();
        System.out.printf("Transaction: %s has successfully been removed.", expense.toString());
    }

    public static HashMap<String, Expense> getExpenses() {
        return expenses;
    }

    public void listExpenses() {
        for (Transaction transaction : expenses.values()) {
            System.out.printf("%s - User: %s %s - Sum: %.2f",
                    transaction.getDate().toString(),
                    transaction.getUser().getFirstName(),
                    transaction.getUser().getLastName(),
                    transaction.getAmount());
        }
    }

}
