package features;
import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in); 

    public static void chooseMainOption() throws IOException {
        while (true) {
            System.out.println("Choose one option.");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            if (scanner.hasNextInt()) {
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        Registration.gettingRegistationData();
                        break;
                    case 2:
                        Login.gettingLoginData();
                        if (Login.isCorrectLoginData()) {
                            System.out.println("You are logged in.");
                            chooseOptionAfterLogin(); 
                        } else {
                            System.out.println("You are not logged in.");
                        }
                        break;
                    case 3:
                        System.out.println("Goodbye!");
                        return; 
                    default:
                        System.out.println("Wrong option.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                if (scanner.hasNextLine()) { 
                    scanner.nextLine(); 
                }
            }
        }
    }

    public static void chooseOptionAfterLogin() throws IOException {
        while (true) {
            System.out.println("Choose one option:");
            System.out.println("1. Creating tasks.");
            System.out.println("2. Showing tasks.");
            System.out.println("3. Updating status of task.");
            System.out.println("4. Exit");

            if (scanner.hasNextInt()) {
                int loggedOption = scanner.nextInt();
                scanner.nextLine();

                switch (loggedOption) {
                    case 1:
                        System.out.println("Creating a task...");
                        Task.gettingDataToTask();
                        break;
                    case 2:
                        System.out.println("Showing tasks... (tu dodaj kod)");
                        break;
                    case 3:
                        System.out.println("Updating task status... (tu dodaj kod)");
                        break;
                    case 4:
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Wrong option. Please choose a number between 1 and 4.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                if (scanner.hasNextLine()) { 
                    scanner.nextLine(); 
                }
            }
        }
    }
}
