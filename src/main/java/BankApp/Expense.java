package BankApp;

import java.time.LocalDate;

public class Expense extends Transaction {
    private EExpenseCategory category;

    public Expense(double sum, LocalDate date, User user, EExpenseCategory category) {
        super(sum, date, user);
        this.category = category;
    }

    public EExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(EExpenseCategory category) {
        this.category = category;
    }
}
