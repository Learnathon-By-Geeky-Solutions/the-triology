public class Stock {
    int id;
    String Product_Name;
    int Supplier_ID;
    int Quantity;
    Double Price;

    Stock(int id, String Product_Name, int Supplier_ID, int Quantity, Double Price) {
        this.id = id;
        this.Product_Name = Product_Name;
        this.Supplier_ID = Supplier_ID;
        this.Quantity = Quantity;
        this.Price = Price;
    }

}
