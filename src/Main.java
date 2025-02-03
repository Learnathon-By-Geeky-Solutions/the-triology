import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FinanceManager financeManager = new FinanceManager();

        while (true) {
            System.out.println("\n-- Personal Finance Tracker --");
            System.out.println("1. Add a Transaction");
            System.out.println("2. Show All Transactions");
            System.out.println("3. Show Balance");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();

                    System.out.print("Enter Amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Enter Category (e.g., Food, Rent): ");
                    String category = scanner.nextLine();

                    System.out.print("Enter Description: ");
                    String description = scanner.nextLine();

                    Transaction transaction = new Transaction(date, amount, category, description);
                    financeManager.addTransaction(transaction);
                    System.out.println("Transaction added successfully!");
                    break;

                case 2:
                    financeManager.showTransactions();
                    break;

                case 3:
                    financeManager.showBalance();
                    break;

                case 4:
                    System.out.println("Exiting... Have a nice day!");
                    return;

                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
}
