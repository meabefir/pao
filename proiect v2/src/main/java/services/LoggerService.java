package services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerService {
    private static LoggerService instance;
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    private static boolean firstUse = true;

    private static final String FILE_PATH = "logs";

    public static LoggerService getInstance() {
        if (instance == null) {
            instance = new LoggerService();
        }
        return instance;
    }

    private void eraseFile() {
        firstUse = false;
        try (PrintWriter writer = new PrintWriter(FILE_PATH);){
            writer.print("");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void log(String actionName) {
        if (firstUse) {
            eraseFile();
        }

        try(
                BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true));
                ) {
            Date date = new Date(System.currentTimeMillis());

            writer.write(actionName + " " + formatter.format(date) + "\n");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
