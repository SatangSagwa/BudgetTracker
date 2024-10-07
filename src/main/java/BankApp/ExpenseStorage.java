package BankApp;

import com.google.gson.Gson;
import org.example.SaveExpenses;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class ExpenseStorage {
    //public static List<Expense> expenses = new ArrayList<>();
    public static HashMap<Expense, Transaction> expenses = new HashMap<>();

    public static void addExpense(Expense expense) throws IOException {
        expenses.put(expense, new Transaction(expense.getAmount(), expense.getDate(), expense.getUser()));
        Gson gson = new Gson();
        String filePath = "src/main/ExpenseStorage.json";
        FileWriter fileWriter = new FileWriter(filePath);
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

    public Expense getExpense(Expense expense) {
        return expense;
    }


    public static void removeExpense(Expense expense) throws IOException {
        expenses.remove(expense);
        SaveExpenses.main(null);
        System.out.printf("Transaction: %s has successfully been removed.", expense.toString());
    }

    public static HashMap<Expense, Transaction> getExpenses() {
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
