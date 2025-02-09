package features;

import java.io.Console;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class registration {
    public String id;
    public static String firstName;
    public static String lastName;
    public static String email;
    public static String password;
    
    public static void gettingRegistationData() throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("First name:");
        do{
            firstName = scanner.nextLine();
        }while(!isCorrectName(firstName));
        do{
            System.out.println("Last name:");
            lastName = scanner.nextLine();
        }while(!isCorrectName(lastName));
        do{
            System.out.println("Email:");
            email = scanner.nextLine();
        }while(!isCorrectMail(email));
        do{
            Console console = System.console();
            if (console == null) {
                System.out.println("Console is not available.");
                password = scanner.nextLine();  
            } else {
                char[] passwordArray = console.readPassword("Password: ");
                password = new String(passwordArray);
            }
        }while(!isCorrectPassword(password));     
        scanner.close();
        }
        public static void createAccount(){
            try(FileWriter writer = new FileWriter("users.txt",true)){
                System.out.println("Creating account ...");
            }catch(IOException e){
                System.out.println("Write error: " + e.getMessage());
            }
        }
        
        private static boolean isCorrectName(String name){
        if(name.length() < 3){
            System.out.println("Your name is too brief!");
            return false;
        }
        else if(!name.matches("^[a-zA-Z]+$")){
            System.out.println("Please enter only letters!");
            return false;
        }
        return true;
        }
        private static boolean isCorrectMail(String mail){
            if(mail.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\r\n")){
                System.out.println("Your email is not correct!");
                return false;
            }
            return true;
        }
        private static boolean isCorrectPassword(String password){
            if(password.length() < 6){
                System.out.println("Your password should has minimum 6 chars!");
                return false;
            }
            return true;
        }
}
