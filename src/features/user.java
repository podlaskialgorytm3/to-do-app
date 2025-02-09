package features;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class user {
    public String id;
    public String firstName;
    public String lastName;
    public String email;
    public String password;

    public static ArrayList<user> users = new ArrayList<user>();

    user(String id, String firstName, String lastName, String email, String password){
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
                    user newUser = new user(userData[0], userData[1], userData[2], userData[3], userData[4]);
                    users.add(newUser);
                }
            }
        } catch (IOException e) {
            System.out.println("Read error: " + e.getMessage());
        }
    }

    public static void printUsers() {
        gettingData();
        for (user u : users) {
            System.out.println("ID: " + u.id);
            System.out.println("First Name: " + u.firstName);
            System.out.println("Last Name: " + u.lastName);
            System.out.println("Email: " + u.email);
            System.out.println("Password: " + u.password);
            System.out.println();
        }
    }
}
