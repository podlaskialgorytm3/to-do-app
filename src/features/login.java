package features;

import java.io.Console;
import java.util.Scanner;

public class login {
    public static String email;
    public static String password;

    public static void gettingLoginData(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Email:");
        do{
            email = scanner.nextLine();
        }while(!registration.isCorrectMail(email));
        do{
            Console console = System.console();
            if (console == null) {
                System.out.println("Console is not available.");
                password = scanner.nextLine();  
            } else {
                char[] passwordArray = console.readPassword("Password: ");
                password = new String(passwordArray);
            }
        }while(!registration.isCorrectPassword(password)); 
        scanner.close();
    }

    public static boolean isCorrectLoginData(){
        user.gettingData();
        for (user u : user.users) {
            if(u.email.equals(email) && u.password.equals(registration.hashPassword(password))){
                return true;
            }
        }
        return false;
    }
    
}
