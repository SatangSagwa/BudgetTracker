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

    //Polymorphism achieved by overriding
    @Override
    public String toString() {
        return  String.format("%s, ID = %s, amount = %.2f, user = %s, category = %s\n",
                date,
                id,
                amount,
                user,
                category);
    }
}
