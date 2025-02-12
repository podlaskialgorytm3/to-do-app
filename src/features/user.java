package features;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class User {
    public String id;
    public String firstName;
    public String lastName;
    public String email;
    public String password;

    public static ArrayList<User> users = new ArrayList<User>();

    User(String id, String firstName, String lastName, String email, String password){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public static void gettingData() {
        try (BufferedReader br = new BufferedReader(new FileReader("./data/users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(" ");
                if (userData.length == 5) {
                    User newUser = new User(userData[0], userData[1], userData[2], userData[3], userData[4]);
                    users.add(newUser);
                }
            }
        } catch (IOException e) {
            System.out.println("Read error: " + e.getMessage());
        }
    }

    public static User getUserByEmail(String email) {
        gettingData();
        for (User u : users) {
            if (u.email.equals(email)) {
                return u;
            }
        }
        return null;
    }

    public static void printUser(User user){
        System.out.println("ID: " + user.id);
        System.out.println("First Name: " + user.firstName);
        System.out.println("Last Name: " + user.lastName);
        System.out.println("Email: " + user.email);
        System.out.println("Password: " + user.password);
    }

    public static void printUsers() {
        gettingData();
        for (User u : users) {
            System.out.println("ID: " + u.id);
            System.out.println("First Name: " + u.firstName);
            System.out.println("Last Name: " + u.lastName);
            System.out.println("Email: " + u.email);
            System.out.println("Password: " + u.password);
            System.out.println();
        }
    }
}
