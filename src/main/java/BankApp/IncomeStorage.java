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

    //Map of all incomes
    private Map<String, Income> incomes = new HashMap<>();
    private Integer index = 0;

    public Map <String, Income> getIncomes() {
        return incomes;
    }

    //iterates and prints all Enum income categories
    public void listCategories() {
        int i = 1;
        for (EIncomeCategory category : EIncomeCategory.values()) {
            System.out.println(i + ": " + category);
            i++;
        }
    }

    /*
    Parameter income - income to add to incomes map
    Loads all incomes
    Iterates incomes map, comparing index to find the highest id.
    Sets index to highest id + 1.
    Puts index and income in map
    Saves incomes
     */
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

    /*
    Param id - id of income to remove
    Try - remove income with id and save
    Catch - If id isn't found
     */
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

    /*
    Param year, month - year and month to search for
    Loads incomes
    Concatenates a date-string depending on input
    Iterates incomes map and compares string to each incomes' date
    If it is matching, the income is added to a list.
    List is returned.
     */
    public List<Income> findIncomesByDate(int year, int month) {
        loadIncomes();
        String searchDateString = year + "-" + month;
        if (month < 10) {
            searchDateString = year + "-0" + month;
        }
        List<Income> searchMatches = new ArrayList<>();
        for (String key : incomes.keySet()) {
            String compareDate = incomes.get(key).getDate();
            String compareDateStr = compareDate.substring(0, 7);
            if (compareDateStr.equals(searchDateString)) {
                searchMatches.add(incomes.get(key));
            }
        }
        return searchMatches;
    }

    /*
    Sets typetoken to return correct datatype
    Sets incomess map to json IncomeStorage values
    if map is empty, it is declared as a hashmap to not cause the map to be null.
     */
    public void loadIncomes() {
        Type type = new TypeToken<Map<String, Income>>() {}.getType();
        try {
            FileReader fr = new FileReader(filePath);
            incomes = gson.fromJson(fr, type);
            if (incomes == null) {
                incomes = new HashMap<>();
            }
        } catch (Exception e) {
            System.out.println("Incomes not found!");
        }
    }

    //Saves incomes to json IncomeStorage
    public void saveIncomes() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        gson.toJson(incomes, fw);
        fw.close();
    }
}
