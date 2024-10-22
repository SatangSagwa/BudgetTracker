package BankApp;

public class Expense extends Transaction {
    private EExpenseCategory category;

    public Expense(double amount, User user, EExpenseCategory category) {
        super(amount, user);
        this.amount = amount;
        this.user = user;
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
        return "Date: " + getDate().toString() +
                "\nCategory: " + category +
                "\nAmount= " + getAmount() +
                "\nUser= " + getUser();
    }
}
