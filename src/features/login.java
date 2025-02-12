package features;

import java.io.Console;
import java.util.Scanner;

public class Login {
    public static String email;
    public static String password;

    public static void gettingLoginData(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Email:");
        do{
            email = scanner.nextLine();
        }while(!Registration.isCorrectMail(email));
        do{
            Console console = System.console();
            if (console == null) {
                System.out.println("Console is not available.");
                password = scanner.nextLine();  
            } else {
                char[] passwordArray = console.readPassword("Password: ");
                password = new String(passwordArray);
            }
        }while(!Registration.isCorrectPassword(password)); 
        scanner.close();
    }

    public static boolean isCorrectLoginData(){
        User.gettingData();
        for (User u : User.users) {
            if(u.email.equals(email) && u.password.equals(Registration.hashPassword(password))){
                return true;
            }
        }
        return false;
    }
    public static void login(){
        gettingLoginData();
        if(isCorrectLoginData()){
            System.out.println("You are logged in.");
            User user = User.getUserByEmail(email);
            AccessCode accessCode = new AccessCode(user.id);
            accessCode.deleteCodeByUserId(user.id);
            accessCode.printCode();
            
        }else{
            System.out.println("You are not logged in.");
        }
    }
    
}
