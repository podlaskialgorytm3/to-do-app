package features;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AccessCode {
    private String userId;
    private String expiration;
    private int code;

    public AccessCode(String userId) {
        this.userId = userId;
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
        saveCode(userId,expiration,code);
    }

    public void saveCode(String userId, String expiration, int code){ {
        try(FileWriter writer = new FileWriter("./data/access-code.txt", true)){
            writer.write(userId + " " + expiration + " " + code);
            writer.write("\n");
            writer.close();
            System.out.println("Access code has been saved!");
        }catch(IOException e){
            System.out.println("Write error: " + e.getMessage());
        }
    }
    }

    public void  deleteCodeByUserId(String userId){
            System.out.println("Deleting access code...");
            try(BufferedReader reader = new BufferedReader(new FileReader("./data/access-code.txt"))){
                String line;
                String data = "";
                while((line = reader.readLine()) != null){
                    String[] accessCodeData = line.split(" ");
                    if(accessCodeData.length == 3){
                        if(!accessCodeData[0].equals(userId)){
                            data += line + "\n";
                        }
                    }
                }
                FileWriter writer = new FileWriter("./data/access-code.txt");
                writer.write(data);
                writer.close();
                System.out.println("Access code has been deleted!");
                
            }
            catch(IOException e){
                System.out.println("Read error: " + e.getMessage());
            }
        }
}
