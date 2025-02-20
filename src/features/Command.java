package features;

import java.io.IOException;
import java.util.Arrays;

public class Command {
    public final String command;
    public final boolean isValue;
    public static String value;

    public static final Command LOGIN = new Command("-l", false, null);
    public static final Command REGISTER = new Command("-r", false, null);
    public static final Command CODE = new Command("-c", true, value);
    public static final Command HELP = new Command("-h", false, null);
    public static final Command TASK = new Command("-t", false, null);

    private Command(String command, boolean isValue, String value) {
        this.command = command;
        this.isValue = isValue;
    }

    public static void readCommands(String[] args) throws IOException {
        boolean isCorrectCode = AccessCode.isCorrectCode(getCode(args));
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(LOGIN.command)) {
                Login.login();
            }
            if (args[i].equals(REGISTER.command)) {
                Registration.gettingRegistationData();
            }
            if (args[i].equals(CODE.command)) {
                value = args[i + 1];
                isCorrectCode = AccessCode.isCorrectCode(Integer.parseInt(value));
            }
            if (args[i].equals(HELP.command)) {
                help();
                break;
            }
            if (args[i].equals(TASK.command)) {
                if (isCorrectCode) {
                    Task.taskCommands(Arrays.copyOfRange(args, 3, args.length), getCode(args));
                } else {
                    System.out.println("You have to enter correct code!");
                }
            }
        }
    }

    public static void help() {
        System.out.println("Usage: java <jar-file> [options]");
        System.out.println("Options:");
        System.out.println("  -l\t\t\tLogin");
        System.out.println("  -r\t\t\tRegister");
        System.out.println("  -c <code>\t\tCheck access code");
        System.out.println("  -h\t\t\tHelp");
        System.out.println("  -t\t\t\tTask (Check also task command -t -h)");
    }

    public static int getCode(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(CODE.command)) {
                value = args[i + 1];
                return Integer.parseInt(value);
            }
        }
        return 0;
    }
}
