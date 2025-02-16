package features;

import java.io.IOException;

public class Command {
    public final String command;
    public final boolean isValue;
    public static String value;

    public static final Command LOGIN = new Command("-l", false, null);
    public static final Command REGISTER = new Command("-r", false, null);
    public static final Command CODE = new Command("-c", true, value);
    public static final Command HELP = new Command("-h", false, null);
    
    private Command(String command, boolean isValue, String value) {
        this.command = command;
        this.isValue = isValue;
    }

    public static void readCommands(String[] args) throws IOException{
        for(int i = 0; i < args.length; i++){
            if(args[i].equals(LOGIN.command)){
                Login.login();
            }
            if(args[i].equals(REGISTER.command)){
                Registration.gettingRegistationData();
            }
            if(args[i].equals(CODE.command)){
                value = args[i+1];
                AccessCode.printCorrectness(Integer.parseInt(value));
            }
            if(args[i].equals(HELP.command)){
                help();
            }
        }
    }

    public static void help(){
        System.out.println("Usage: java <jar-file> [options]");
        System.out.println("Options:");
        System.out.println("  -l\t\t\tLogin");
        System.out.println("  -r\t\t\tRegister");
        System.out.println("  -c <code>\t\tCheck access code");
        System.out.println("  -h\t\t\tHelp");
    }
}
