package BankApp;

public class Expense extends Transaction {
    private EExpenseCategory category;
    private int id = 0;

    public Expense(double amount, User user, EExpenseCategory category) {
        super(amount, user);
        this.amount = amount;
        this.user = user;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                ", category = " + category +
                '}';
    }
}
