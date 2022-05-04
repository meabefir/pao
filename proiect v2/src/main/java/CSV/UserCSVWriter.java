package CSV;

import classes.Game;
import classes.User;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class UserCSVWriter extends CSVWriter {
    public UserCSVWriter(String path) {
        super(path);
    }

    public void write(User user) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));) {
            writer.write(user.serialize());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
