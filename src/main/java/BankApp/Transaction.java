package BankApp;

import java.time.LocalDate;

public class Transaction {
    private User user;
    private double sum;
    private LocalDate date;

    public Transaction(double sum, LocalDate date, User user) {
        this.sum = sum;
        this.date = date;
        this.user = user;
    }
}
