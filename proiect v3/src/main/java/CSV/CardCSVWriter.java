package CSV;

import models.Card;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class CardCSVWriter extends CSVWriter {

    public CardCSVWriter(String path) {
        super(path);
    }

    public void write(Card card) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));) {
            writer.write(card.serialize());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
