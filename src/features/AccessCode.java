package features;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class AccessCode {
    private String expiration;
    private int code;

    public AccessCode() {
        this.expiration = generateDate();
        this.code = generateCode();
    }

    private int generateCode() {
        Random random = new Random();
        return 100_000 + random.nextInt(900_000);
    }

    private String generateDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiration = now.plusHours(5);
        return dtf.format(expiration);
    }

    public void printCode() {
        System.out.println("Your code is: " + code);
        System.out.println("Your code will expire at: " + expiration);
    }

    public static void main(String[] args) {
        AccessCode accessCode = new AccessCode();
        accessCode.printCode();
    }
}
