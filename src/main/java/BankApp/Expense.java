package BankApp;

import java.time.LocalDate;

public class Expense extends Transaction {
    private EExpenseCategory category;

    public Expense(double amount, LocalDate date, User user, EExpenseCategory category) {
        super(amount, date, user);
        this.category = category;
    }


    public EExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(EExpenseCategory category) {
        this.category = category;
    }
}
