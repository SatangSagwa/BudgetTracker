package BankApp;

import java.time.LocalDate;

public class Expense extends Transaction {
    private EExpenseCategory category;

    public Expense(double amount, User user, LocalDate date, EExpenseCategory category) {
        super(amount, user, date);
        this.amount = amount;
        this.user = user;
        this.date = date.toString();
        this.category = category;
    }

    public EExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(EExpenseCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return  date +
                " id = " + id +
                ", amount=" + amount +
                ", user = " + user.getFirstName() + user.getLastName() +
                ", category = " + category;
    }
}
