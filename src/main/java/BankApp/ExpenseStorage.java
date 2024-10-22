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
    //private final Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final String filePath = "src/main/ExpenseStorage.json";

    private Map<String, Expense> expenses = new HashMap<>();
    private Integer index = 0;

    //FIX INDEX! ADD INDEX TO EXPENSE!
    public Map<String, Expense> getExpenses() {
        return expenses;
    }

    public void listExpenses() {
        for (String key : expenses.keySet()) {
            System.out.print(key + ": ");
            System.out.printf("%s - User: %s %s - Sum: %.2f\n",
                    expenses.get(key).getDate(),
                    expenses.get(key).getUser().getFirstName(),
                    expenses.get(key).getUser().getLastName(),
                    expenses.get(key).getAmount());
        }
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
        /*
        System.out.printf("%s %s have added a new transaction\n" +
                        "Sum: %.2f\n" +
                        "Category: %s\n" +
                        "Date: %s\n",
                transaction.getUser().getFirstName(),
                transaction.getUser().getLastName(),
                transaction.getAmount(),
                expenses.get(expenseID.toString()).,
                transaction.getDate().toString()); */

    }

    public void removeExpense(Integer id) {
        loadExpenses();
        try {
            expenses.remove(id);
            saveExpenses();
        } catch (Exception e) {
            System.out.println("ID not found");
        }
    }

    public List<Expense> findExpensesByDate(int year, int month) {
        loadExpenses();
        String searchDateStr = year + "-" + month;
        List<Expense> searchMatches = new ArrayList<>();
        for (String key : expenses.keySet()) {
            String compareDate = expenses.get(key).getDate();
            String compareDateStr = compareDate.substring(0, 7);
            System.out.println(compareDateStr);
            if (compareDateStr.equals(searchDateStr)) {
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
                System.out.println("DEBUG EXPENSE EMPTY");
            }
            System.out.println("Expenses loaded");
            //listExpenses();
        } catch (Exception e) {
            System.out.println("Expenses not found!");
        }
    }

    public void saveExpenses() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        gson.toJson(expenses, fw);
        fw.close();
        System.out.println("Expenses saved");
    }

    /*
    //https://mkyong.com/java/gson-supports-java-8-date-time-types/
    public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

        private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        @Override
        public JsonElement serialize(LocalDate localDate,
                                     Type type,
                                     JsonSerializationContext jsonSerializationContext) {

            return new JsonPrimitive(localDate.format(formatter)); // "yyyy-MM-dd"

        }

        @Override
        public LocalDate deserialize(JsonElement jsonElement,
                                     Type type,
                                     JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

            return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString(), formatter);
        }

    }

     */

    /*
    private final Gson gson = new Gson();
    private final String filePath = "src/main/ExpenseStorage.json";

    private HashMap<String, Expense> expenses = new HashMap<>();
    private StringBuilder expenseID = new StringBuilder("00");
    private int index = 1;

    public void loadExpenses() {
        Type type = new TypeToken<Map<String, Expense>>() {}.getType();
        try {
            FileReader fr = new FileReader(filePath);
            expenses = gson.fromJson(fr, type);
            System.out.println("Expenses loaded");
        } catch (Exception e) {
            System.out.println("Expenses not found!");
        }
    }

    public void addExpense(Expense expense) throws IOException {
        loadExpenses();
        FileWriter fileWriter = new FileWriter(filePath);
        StringBuilder id = expenseID.append(index++);
        expenses.put(new StringBuilder(id).toString(), expense);
        gson.toJson(expenses, fileWriter);
        fileWriter.close();
        expenseID.deleteCharAt(2);
        System.out.println("Expenses have been saved.");

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

    public Expense getExpense(StringBuilder ID) {
        for (String key : expenses.keySet()) {
            if (ID.toString().equals(key.toString())) {
                return expenses.get(key);
            }
        }
        System.out.println("ID: " + ID + " not found!");
        return null;
    }


    public void removeExpense(Expense expense) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        expenses.remove(expense);
        gson.toJson(expenses, fileWriter);
        fileWriter.close();
        System.out.printf("Transaction: %s has successfully been removed.", expense.toString());
    }

    public HashMap<String, Expense> getExpenses() {
        return expenses;
    }

    public void listExpenses() {
        for (String key : expenses.keySet()) {
            System.out.println(key.toString() + ": " + expenses.get(key).toString());
        }

        for (Transaction transaction : expenses.values()) {
            System.out.printf("%s - User: %s %s - Sum: %.2f",
                    transaction.getDate().toString(),
                    transaction.getUser().getFirstName(),
                    transaction.getUser().getLastName(),
                    transaction.getAmount());
        }
    }

    public void listCategories() {
        int i = 1;
        for (EExpenseCategory category : EExpenseCategory.values()) {
            System.out.println(i + ": " + category);
            i++;
        }
    }
*/
}
