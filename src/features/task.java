package features;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Task {
    public static String taskId;
    public static String userId;
    public static String taskName;
    public static String taskDescription;
    public static TaskStatus taskStatus;

    public static ArrayList<Task> tasks = new ArrayList<Task>();

    @SuppressWarnings("static-access")
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

    public static void taskCommands(String[] args, int code) {
        String userId = User.getUserIdByCode(code);
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-a")) {
                gettingDataToTask(userId);
            }
            if (args[i].equals("-s")) {
                printTasks();
            }
        }
    }

    public static void gettingDataToTask(String userId) {
        taskId = Util.generateRandomId(12);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Task name:");
        taskName = scanner.nextLine();
        System.out.println("Task description:");
        taskDescription = scanner.nextLine();
        taskStatus = TaskStatus.WAITING;
        scanner.close();
        try {
            createTask(userId);
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
                    Task newTask = new Task(taskData[1], taskData[0], taskData[2], taskData[3], taskData[4]);
                    tasks.add(newTask);
                }
            }
        } catch (IOException e) {
            System.out.println("Read error: " + e.getMessage());
        }
    }

    @SuppressWarnings("static-access")
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

    @SuppressWarnings("static-access")
    public static void showTaskByUserId() {
        gettingAllTask();
        System.out.println("Your taskId: " + userId);
        for (Task task : tasks) {
            if (userId.equals(task.userId)) {
                System.out.println("#############################");
                System.out.println("Task ID: " + task.taskId);
                System.out.println("Task name: " + task.taskName);
                System.out.println("Task description: " + task.taskDescription);
                System.out.println("User ID: " + task.userId);
                System.out.println("Task status: " + task.taskStatus);
            }
        }

    }

    public static void createTask(String userId) throws IOException {
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
