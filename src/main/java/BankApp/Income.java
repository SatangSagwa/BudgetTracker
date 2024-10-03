package BankApp;

import java.time.LocalDate;

public class Income extends Transaction {
    private EIncomeCategory category;
    public Income(double amount, LocalDate date, User user, EIncomeCategory category) {
        super(amount, date, user);
        this.category = category;
    }

    public EIncomeCategory getCategory() {
        return category;
    }

    public void setCategory(EIncomeCategory category) {
        this.category = category;
    }
}
