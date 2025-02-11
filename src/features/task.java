package features;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Task {
    public static String taskId;
    public static String taskName;
    public static String taskDescription;
    public static TaskStatus taskStatus;

    public enum TaskStatus {
        WAITING("waiting"),
        IN_PROGRESS("in-progress"),
        DONE("done");

        private final String status;

        TaskStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return this.status;
        }
    }

    public static void gettingDataToTask(){
        taskId = Util.generateRandomId(12);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Task name:");
        taskName = scanner.nextLine();
        System.out.println("Task description:");
        taskDescription = scanner.nextLine();
        taskStatus = TaskStatus.WAITING;
        scanner.close();
        try {
            createTask();
        } catch (IOException e) {
            System.out.println("Write error: " + e.getMessage());
        }
    }

    public static void createTask() throws IOException{
        try (FileWriter writer = new FileWriter("./data/tasks.txt", true)) {
            writer.write(taskId + " " + taskName + " " + taskDescription + " " + taskStatus);
            writer.write("\n");
            writer.close();
            System.out.println("You have successfully created a task!");
        } catch (IOException e) {
            System.out.println("Write error: " + e.getMessage());
        }
    }
}
