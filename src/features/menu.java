package features;
import java.io.IOException;
import java.util.Scanner;

public class menu {
    public static void chooseMainOption() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        while(option < 1 || option > 3){
            System.out.println("Choose one option.");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            if(scanner.hasNextInt()){
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        registration.gettingRegistationData();
                        break;
                    case 2:
                        login.gettingLoginData();
                        if (login.isCorrectLoginData()) {
                            System.out.println("You are logged in.");
                            chooseOptionAfterLogin();
                        } else {
                            System.out.println("You are not logged in.");
                        }
                        break;
                    case 3:
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Wrong option.");
                }
            }
            else{
                break;
            }
            scanner.close();
        }
    }
    public static void chooseOptionAfterLogin() throws IOException{
        Scanner scanner = new Scanner(System.in);
        int loggedOption = 1;
        while(loggedOption < 1 || loggedOption > 4){
            System.out.println("Choose one option.");
            System.out.println("1. Creating tasks.");
            System.out.println("2. Showing tasks.");
            System.out.println("3. Updating status of task.");
            System.out.println("4. Exit");
            if(scanner.hasNextInt()){
                loggedOption = scanner.nextInt();
                switch (loggedOption) {
                    case 1:
                        task.gettingDataToTask();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Wrong option.");
                }
            }
            else{
                break;
            }
        }
        scanner.close();
    }
}