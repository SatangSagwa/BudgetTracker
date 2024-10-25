package BankApp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseStorage {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final String filePath = "src/main/ExpenseStorage.json";

    private Map<String, Expense> expenses = new HashMap<>();
    private Integer index = 0;

    //FIX INDEX! ADD INDEX TO EXPENSE!
    public Map<String, Expense> getExpenses() {
        return expenses;
    }

    public void listCategories() {
        int i = 1;
        for (EExpenseCategory category : EExpenseCategory.values()) {
            System.out.println(i + ": " + category);
            i++;
        }
    }

    public void addExpense(Expense expense) throws IOException {
        loadExpenses();
        //Increase index to highest id in expenses map
        for (String key : expenses.keySet()) {
            if (index <= expenses.get(key).getId()) {
                index = expenses.get(key).getId();
            }
        }
        index++;
        expense.setId(index);
        String str = index.toString();
        expenses.put(str, expense);
        saveExpenses();
    }


    public void removeExpense(Integer id) {
        loadExpenses();
        try {
            expenses.remove(id.toString());
            System.out.println("Expense " + id + " has been removed.");
            saveExpenses();
        } catch (Exception e) {
            System.out.println("ID not found!");
        }
    }

    public List<Expense> findExpensesByDate(int year, int month) {
        loadExpenses();
        String searchDateString = year + "-" + month;
        if (month < 10) {
            searchDateString = year + "-0" + month;
        }
        List<Expense> searchMatches = new ArrayList<>();
        for (String key : expenses.keySet()) {
            String compareDate = expenses.get(key).getDate();
            String compareDateStr = compareDate.substring(0, 7);
            if (compareDateStr.equals(searchDateString)) {
                searchMatches.add(expenses.get(key));
            }
        }
        return searchMatches;
    }

    public void loadExpenses() {
        Type type = new TypeToken<Map<String, Expense>>() {}.getType();
        try {
            FileReader fr = new FileReader(filePath);
            expenses = gson.fromJson(fr, type);
            if (expenses == null) {
                expenses = new HashMap<>();
                //System.out.println("DEBUG EXPENSE EMPTY");
            }
            //System.out.println("Expenses loaded");
            //listExpenses();
        } catch (Exception e) {
            System.out.println("Expenses not found!");
        }
    }

    public void saveExpenses() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        gson.toJson(expenses, fw);
        fw.close();
        //System.out.println("Expenses saved");
    }
}
