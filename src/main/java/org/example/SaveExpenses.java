package org.example;

import BankApp.Expense;
import BankApp.ExpenseStorage;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveExpenses {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        String filePath = "src/main/ExpenseStorage.json";
        FileWriter fileWriter = new FileWriter(filePath);

        List<Expense> expenses = ExpenseStorage.getExpenses();
        //expenses.add(new Expense(500, LocalDate.now(), new User("Em", "i"), EExpenseCategory.ENTERTAINMENT));
        System.out.println(expenses);
        gson.toJson(expenses, fileWriter);
        fileWriter.close();
    }
}
