package BankApp;

import org.example.SaveExpenses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExpenseStorage {
    public static List<Expense> expenses = new ArrayList<>();


    public static void addExpense(Expense expense) throws IOException {
        expenses.add(expense);
        SaveExpenses.main(null);
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

    public static Expense getExpense(int id) {
        return expenses.get(id);
    }

    public static void removeExpense(Expense expense) throws IOException {
        expenses.remove(expense);
        SaveExpenses.main(null);
        System.out.printf("Transaction: %s has successfully been removed.", expense.toString());
    }

    public static List<Expense> getExpenses() {
        return expenses;
    }

    public static void listExpenses() {
        for (Expense expense : expenses) {
            System.out.printf("%s - User: %s %s - Sum: %.2f - Category: %s",
                    expense.getDate().toString(),
                    expense.getUser().getFirstName(),
                    expense.getUser().getLastName(),
                    expense.getAmount(),
                    expense.getCategory().name());
        }
    }

}
