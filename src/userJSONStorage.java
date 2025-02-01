import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.*;


public class userJSONStorage
{
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILENAME = "users.json";

    public static void saveUsers(List<User> users) {
        try (Writer writer = new FileWriter(FILENAME)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<User> loadUsers()
    {
        try (Reader reader = new FileReader(FILENAME)) {
            return gson.fromJson(reader, new TypeToken<List<User>>() {}.getType());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static User signinUsers(List<User> users,String email,String password)
    {
        for (User user : users)
        {
            if (user.getEmail().equals(email)) {
                if(user.getPassword().equals(password))
                  return user;
                else return new User("","","","");
            }
        }
         return new User("","","","");
    }
    public static boolean changeName(List<User> users, String email, String newName)
    {
        for (User user : users)
        {
            if (user.getEmail().equals(email)) {
                user.setName(newName);
                userJSONStorage.saveUsers(users);
                return true;
            }
        }
        return false;
    }
    public static boolean changePassword(List<User> users, String email, String password)
    {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                user.setPassword(password);
                userJSONStorage.saveUsers(users);
                return true;
            }
        }
        return false;
    }

}