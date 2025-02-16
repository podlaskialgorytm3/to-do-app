package features;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Task {
    public String taskId;
    public String userId;
    public String taskName;
    public String taskDescription;
    public TaskStatus taskStatus;

    public static ArrayList<Task> tasks = new ArrayList<Task>();

    public Task(String taskId, String userId, String taskName, String taskDescription, String taskStatus) {
        this.taskId = taskId;
        this.userId = userId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStatus = TaskStatus.fromString(taskStatus);
    }

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

        public static TaskStatus fromString(String text) {
            for (TaskStatus ts : TaskStatus.values()) {
                if (ts.status.equalsIgnoreCase(text)) {
                    return ts;
                }
            }
            throw new IllegalArgumentException("No enum constant for value: " + text);
        }
    }

    public void gettingDataToTask() {
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

    public static void gettingAllTask() {
        try (BufferedReader reader = new BufferedReader(new FileReader("./data/tasks.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] taskData = line.split(";");
                if (taskData.length == 5) {
                    System.out.println(TaskStatus.fromString(taskData[4].toString()));
                    Task newTask = new Task(taskData[1], taskData[0], taskData[2], taskData[3], taskData[4]);
                    tasks.add(newTask);
                }
            }
        } catch (IOException e) {
            System.out.println("Read error: " + e.getMessage());
        }
    }

    public static void printTasks() {
        gettingAllTask();
        for (Task task : tasks) {
            System.out.println("#############################");
            System.out.println("Task ID: " + task.taskId);
            System.out.println("Task name: " + task.taskName);
            System.out.println("Task description: " + task.taskDescription);
            System.out.println("User ID: " + task.userId);
            System.out.println("Task status: " + task.taskStatus);
        }
    }

    public static void showTaskByUserId() {

    }

    public void createTask() throws IOException {
        try (FileWriter writer = new FileWriter("./data/tasks.txt", true)) {
            writer.write(userId + ";" + taskId + ";" + taskName + ";" + taskDescription + ";" + taskStatus);
            writer.write("\n");
            writer.close();
            System.out.println("You have successfully created a task!");
        } catch (IOException e) {
            System.out.println("Write error: " + e.getMessage());
        }
    }
}
