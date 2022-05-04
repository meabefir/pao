package CSV;

import classes.Game;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class GameCSVWriter extends CSVWriter {
    public GameCSVWriter(String path) {
        super(path);
    }

    public void write(Game game) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));) {
//            System.out.println(game.serialize());
//            FileWriter fileWriter = new FileWriter(path, true);
            writer.write(game.serialize());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
