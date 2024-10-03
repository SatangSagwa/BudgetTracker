package BankApp;

import java.time.LocalDate;

public class Transaction {
    private User user;
    private double amount;
    private LocalDate date;

    public Transaction(double amount, LocalDate date, User user) {
        this.amount = amount;
        this.date = date;
        this.user = user;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
