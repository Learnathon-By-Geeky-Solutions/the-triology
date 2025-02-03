import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.ArrayList;

public class FinanceManager {
    private List<Transaction> transactions;

    public FinanceManager() {
        this.transactions = loadTransactions();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        saveTransactions();
    }

    public void showTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions recorded.");
            return;
        }
        transactions.forEach(System.out::println);
    }

    public double getBalance() {
        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
    }

    public void showBalance() {
        System.out.println("Current Balance: " + getBalance());
    }

    private void saveTransactions() {
        try (FileWriter writer = new FileWriter("transactions.json")) {
            Gson gson = new Gson();
            gson.toJson(transactions, writer);
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

    private List<Transaction> loadTransactions() {
        try (Reader reader = new FileReader("transactions.json")) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Transaction>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
