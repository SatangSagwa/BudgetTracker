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

public class IncomeStorage {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final String filePath = "src/main/IncomeStorage.json";

    private Map<String, Income> incomes = new HashMap<>();
    private Integer index = 0;

    public Map <String, Income> getIncomes() {
        return incomes;
    }

    public void listIncomes() {
        for (String key : incomes.keySet()) {
            System.out.print(key + ": ");
            System.out.printf("%s - User: %s %s - Sum: %.2f\n",
                    incomes.get(key).getDate(),
                    incomes.get(key).getUser().getFirstName(),
                    incomes.get(key).getUser().getLastName(),
                    incomes.get(key).getAmount());
        }
    }

    public void listCategories() {
        int i = 1;
        for (EIncomeCategory category : EIncomeCategory.values()) {
            System.out.println(i + ": " + category);
            i++;
        }
    }

    public void addIncome(Income income) throws IOException {
        loadIncomes();
        //Increase index to highest id in expenses map
        for (String key : incomes.keySet()) {
            if (index <= incomes.get(key).getId()) {
                index = incomes.get(key).getId();
            }
        }
        index++;
        income.setId(index);
        String str = index.toString();
        incomes.put(str, income);
        saveIncomes();
    }

    public void removeIncome(Integer id) {
        loadIncomes();
        try {
            incomes.remove(id.toString());
            System.out.println("Income " + id + " has been removed.");
            saveIncomes();
        } catch (Exception e) {
            System.out.println("ID not found!");
        }
    }

    public List<Income> findIncomesByDate(int year, int month) {
        loadIncomes();
        String searchDateString = year + "-" + month;
        if (month < 10) {
            searchDateString = year + "-0" + month;
        }
        System.out.println(searchDateString);
        List<Income> searchMatches = new ArrayList<>();
        for (String key : incomes.keySet()) {
            String compareDate = incomes.get(key).getDate();
            String compareDateStr = compareDate.substring(0, 7);
            System.out.println(compareDateStr);
            if (compareDateStr.equals(searchDateString)) {
                searchMatches.add(incomes.get(key));
            }
        }
        return searchMatches;
    }

    public void loadIncomes() {
        Type type = new TypeToken<Map<String, Income>>() {}.getType();
        try {
            FileReader fr = new FileReader(filePath);
            incomes = gson.fromJson(fr, type);
            if (incomes == null) {
                incomes = new HashMap<>();
                System.out.println("DEBUG INCOMES EMPTY");
            }
            System.out.println("Incomes loaded");
            //listExpenses();
        } catch (Exception e) {
            System.out.println("Incomes not found!");
        }
    }

    public void saveIncomes() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        gson.toJson(incomes, fw);
        fw.close();
        System.out.println("Incomes saved");
    }
}
