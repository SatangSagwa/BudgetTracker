package BankApp;

public class Income extends Transaction {
    private EIncomeCategory category;
    public Income(double amount, String date, User user, EIncomeCategory category) {
        super(amount, user);
        this.category = category;
    }

    public EIncomeCategory getCategory() {
        return category;
    }

    public void setCategory(EIncomeCategory category) {
        this.category = category;
    }
}
