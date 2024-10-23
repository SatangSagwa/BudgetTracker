package BankApp;

import java.time.LocalDate;

public class Income extends Transaction {
    private EIncomeCategory category;

    public Income(double amount, User user, LocalDate date, EIncomeCategory category) {
        super(amount, user, date);
        this.amount = amount;
        this.user = user;
        this.date = date.toString();
        this.category = category;
    }

    public EIncomeCategory getCategory() {
        return category;
    }

    public void setCategory(EIncomeCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {

        return  date +
                ", id = " + id +
                ", amount = " + amount +
                ", user = " + user +
                " category = " + category;
    }
}
