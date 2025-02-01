import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.*;


public class productJSONStorage
{
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILENAME = "products.json";

    public static void saveProduct(List<Product> products)
    {
        try (Writer writer = new FileWriter(FILENAME)) {
            gson.toJson(products, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Product> loadProducts()
    {
        try (Reader reader = new FileReader(FILENAME)) {
            return gson.fromJson(reader, new TypeToken<List<Product>>() {}.getType());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static Product searchProduct(List<Product> products, String productName)
    {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);
    }

    public static double buyProduct(List<Product> products, String name , int quantity)
    {
        double total =-1;
        for(Product product : products)
        {
            if(product.getName().equalsIgnoreCase(name) && product.getQuantity() >= quantity)
            {
                total = product.getPrice() * quantity;
            }
        }
        return total;
    }

    public static void updateProduct(List<Product> products, String productName, int newQuantity)
    {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                product.setQuantity(product.getQuantity()-newQuantity);

            }
        }
        productJSONStorage.saveProduct(products);
    }
    public static boolean deleteProduct(List<Product> products, String productName)
    {
        return products.removeIf(product -> product.getName().equalsIgnoreCase(productName));
    }
}