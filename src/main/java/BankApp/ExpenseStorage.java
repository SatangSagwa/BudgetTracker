package BankApp;

import java.util.ArrayList;
import java.util.List;

public class ExpenseStorage {
    List<Expense> expenses = new ArrayList<Expense>();

    private void addExpense(Expense expense) {
        expenses.add(expense);
    }

    private void listExpenses() {
        for (Expense expense : expenses) {
            System.out.println(expense.toString());
        }
    }
}
