import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class Inventory {
    List<Suppliers> suppliers;
    List<Stock> stock;
    List<Sales> sales;
    Inventory() {
        this.suppliers = new ArrayList<>();
        this.stock = new ArrayList<>();
        this.sales = new ArrayList<>();
    }
}
class InventoryService {
    private static final String FILE_PATH = "Inventory.json";
      Inventory inventory;
    private Gson gson = new Gson();
    public InventoryService() {
        this.inventory =loadInventory();
        if(this.inventory == null) {
            this.inventory = new Inventory();
        }
    }

    private Inventory loadInventory() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (FileReader reader = new FileReader(FILE_PATH)) {
                Type type= new TypeToken<Inventory>() {}.getType();
                return gson.fromJson(reader, type);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Inventory();
    }

    private void SaveInventory() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(inventory, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addSupplier(Suppliers supplier) {
        inventory.suppliers.add(supplier);
        SaveInventory();
    }
    public void addStock(Stock stock) {
        inventory.stock.add(stock);
        SaveInventory();
    }
    public void addSales(Sales sales) {
        inventory.sales.add(sales);
        SaveInventory();
    }

    public void displayInventory() {
        System.out.println("Information of Suppliers:\n");

        for(Suppliers supplier: inventory.suppliers) {
            System.out.println("ID: " + supplier.id + " Name: " +supplier.name + " Address: " + supplier.address + " Contact " + supplier.contact);

        }
        System.out.println("\nInformation about Stocks:\n");
        for(Stock stock: inventory.stock){
            System.out.println("ID: "+ stock.id + " Product: " + stock.Product_Name +" SupplierID: " + stock.Supplier_ID + " Quantity: " + stock.Quantity + " Price: " + stock.Price);
        }
        System.out.println("\nInformation of Sales:\n");
        for(Sales sales: inventory.sales){
            System.out.println("ID: " + sales.id + " QuantitySold: " + sales.quantitySold + " Total Price: " + sales.totalprice);
        }
    }
}
