
import java.time.LocalDate;
import java.util.Scanner;
import java.io.IOException;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Suppliers details: \n");
        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Address: ");
        String address = sc.nextLine();
        System.out.print("Contact: ");
        String contact = sc.nextLine();

        InventoryService ins = new InventoryService();
        ins.addSupplier(new Suppliers(id, name, address, contact));

        System.out.println("Enter Stock details: \n");

        System.out.print("Product ID: ");
        int stockid = sc.nextInt();
        sc.nextLine();
        System.out.print("Product Name: ");
        String stockname = sc.nextLine();
        System.out.print("Supplier ID: ");
        int supplierid = sc.nextInt();
        System.out.print("Product Quantity: ");
        int quantity = sc.nextInt();

        System.out.print("Product Price: ");
        double price = sc.nextDouble();
        ins.addStock(new Stock(stockid, stockname, supplierid, quantity, price));

        System.out.println("Enter Sales details: \n");

        System.out.print("Product ID: ");
        int saleid = sc.nextInt();
        System.out.print("Product Quantity: ");
        int quantitysold = sc.nextInt();
        System.out.print("Total Price: ");
        double totalprice = sc.nextDouble();

        ins.addSales(new Sales(saleid, quantitysold, totalprice));

        ins.displayInventory();





    }
}