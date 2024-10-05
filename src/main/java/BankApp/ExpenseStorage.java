package BankApp;

import java.util.ArrayList;
import java.util.List;

public class ExpenseStorage {
    private static List<Expense> expenses = new ArrayList<>();

    public static void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public static Expense getExpense(int id) {
        return expenses.get(id);
    }

    public static void removeExpense(Expense expense) {
        expenses.remove(expense);
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
