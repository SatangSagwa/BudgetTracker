package BankApp;

import java.time.LocalDate;

public class Transaction {
    public User user;
    public double amount;
    public String date;

    public Transaction(double amount, User user) {
        this.amount = amount;
        this.user = user;
        this.date = LocalDate.now().toString();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }
}
