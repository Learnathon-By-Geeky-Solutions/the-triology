import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.*;
import java.io.*;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        List<Product> products = productJSONStorage.loadProducts();
        List<User> users = userJSONStorage.loadUsers();
        User current_user = new User("","","","");
        System.out.println("----------------E_Commerce--------------");
        boolean signed_in = false;
        while(!signed_in) {
            System.out.print("Already have an account? : \n 1-Yes\n 2-No\n");
            int have_account;
            have_account = sc.nextInt();
            sc.nextLine();
            if (have_account == 1) {
                System.out.print("Enter your email : ");
                String email = sc.nextLine();
                System.out.print("Enter your password : ");
                String password = sc.nextLine();
                try {
                    current_user = userJSONStorage.signinUsers(users, email, password);
                    if (current_user.getEmail() != null) {
                        signed_in = true;
                        System.out.println("Welcome " + current_user.getName() + "!");
                    } else
                        System.out.println("Invalid email or password");
                } catch (Exception e) {
                    System.out.println("Error Signing in");
                }
            } else {
                System.out.println("Please enter your name : ");
                String name = sc.nextLine();
                System.out.println("Enter your email : ");
                String email = sc.nextLine();
                System.out.println("Enter your password : ");
                String password = sc.nextLine();
                System.out.print("Enter type : ");
                String type = sc.nextLine();
                try {
                    current_user = new User(name, email, password,type);
                    users.add(current_user);
                    signed_in = true;
                    userJSONStorage.saveUsers(users);
                    System.out.println("Welcome " + current_user.getName() + "!");
                } catch (Exception e) {
                    System.out.println("Something went wrong");
                }
            }
        }

        while(signed_in)
        {
            System.out.println("Menu : ");
            System.out.println("1. See Products.");
            if(Objects.equals(current_user.getType(), "Vendor"))
                System.out.println("2. Add Product.");
            else
                System.out.println("2. Buy Product.");

            System.out.println("3. Change Name.");
            System.out.println("4. Change Password.");
            System.out.println("0. Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice)
            {
                case 0:
                {
                    signed_in = false;
                    break;
                }
                case 1:
                {

                    List<Product> p = productJSONStorage.loadProducts();
                    for(Product p1 : p)
                    {
                        System.out.println(p1.getName() + ", " + p1.getPrice()+" TK, " + p1.getQuantity() + " pieces in stock ");
                    }
                    break;
                }
                case 2:
                {
                    if(Objects.equals(current_user.getType(), "Vendor"))
                    {
                        System.out.println("Enter product name : ");
                        String name = sc.nextLine();
                        System.out.println("Enter product price : ");
                        double price = sc.nextDouble();
                        System.out.println("Enter product quantity : ");
                        int quantity = sc.nextInt();
                        try{
                            Product p1 = new Product(name, price, quantity);
                            products.add(p1);
                            productJSONStorage.saveProduct(products);
                        }
                        catch(Exception e) {
                            System.out.println("Something went wrong");
                        }
                    }
                    else
                    {
                        System.out.println("Enter the product name you want to buy : ");
                        String name = sc.nextLine();
                        System.out.println("Enter quantity : ");
                        int quantity = sc.nextInt();
                        double amount = productJSONStorage.buyProduct(products, name, quantity);
                        System.out.println("Pay "+ amount + " Tk.");
                        double pay;
                        pay=sc.nextDouble();
                        if(pay<amount)
                            System.out.println("Not enough money");
                        else
                        {
                            System.out.println("Your product has been purchased");
                            if(pay>amount)
                                System.out.println("Heres the change : " + (pay-amount) + " Tk.");
                            productJSONStorage.updateProduct(products, name, quantity);
                        }
                    }

                    break;
                }
                case 3:
                {
                    System.out.println("Enter new name : ");
                    String name = sc.nextLine();
                    try
                    {
                      if(userJSONStorage.changeName(users,current_user.getEmail(),name))
                        System.out.println("Name Changed Successfully");
                      else
                          System.out.println("The user doesn't exist");
                    }
                    catch(Exception e)
                    {
                        System.out.println("Something went wrong");
                    }
                    break;
                }
                case 4:
                {
                    System.out.println("Enter new password : ");
                    String password1 = sc.nextLine();
                    System.out.println("confirm new password : ");
                    String password2 = sc.nextLine();
                    if(!(password2.equals(password1)))
                        System.out.println("Passwords do not match");
                    else
                    {
                        try
                        {
                            if(userJSONStorage.changePassword(users,current_user.getEmail(),password1))
                                System.out.println("Password Changed Successfully");
                            else
                                System.out.println("The user doesn't exist");
                        }
                        catch(Exception e)
                        {
                            System.out.println("Something went wrong");
                        }
                    }
                    break;
                }
                default:
                {
                    System.out.println("Please enter a valid choice");
                    break;
                }

            }
        }


    }

}