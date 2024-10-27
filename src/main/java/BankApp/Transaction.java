package BankApp;

import java.time.LocalDate;

public class Transaction {
    public User user;
    public double amount;
    public String date;
    public int id;

    public Transaction(double amount, User user, LocalDate date) {
        this.amount = amount;
        this.user = user;
        this.date = date.toString();
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Transaction{" +
                "user=" + user +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", id=" + id +
                '}';
    }
}
