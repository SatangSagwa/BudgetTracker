package BankApp;

import java.time.LocalDate;

public class Income extends Transaction {
    private EIncomeCategory category;
    public Income(double amount, User user, LocalDate date, EIncomeCategory category) {
        super(amount, user, date);
        this.category = category;
    }

    public EIncomeCategory getCategory() {
        return category;
    }

    public void setCategory(EIncomeCategory category) {
        this.category = category;
    }
}
