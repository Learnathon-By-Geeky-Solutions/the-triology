public class Transaction {
    private String date;
    private double amount;
    private String category;
    private String description;

    public Transaction(String date, double amount, String category, String description) {
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %.2f | %s", date, category, amount, description);
    }
}
