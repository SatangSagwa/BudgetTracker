package org.example;

import BankApp.EExpenseCategory;
import BankApp.Expense;
import BankApp.User;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class SaveExpenses {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        String filePath = "src/main/ExpenseStorage.json";
        FileWriter fileWriter = new FileWriter(filePath);

        //HashMap<Integer, Expense> expenses = ExpenseStorage.getExpenses();
        Expense expense = new Expense(500, LocalDate.now(), new User("Emilia", "Ivarsson"), EExpenseCategory.SHOPPING);
        //System.out.println(expenses);
        //gson.toJson(expenses, fileWriter);
        fileWriter.flush();
    }
}
