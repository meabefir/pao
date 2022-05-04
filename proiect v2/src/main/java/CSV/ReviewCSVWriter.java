package CSV;

import classes.Review;
import classes.User;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class ReviewCSVWriter extends CSVWriter {
    public ReviewCSVWriter(String path) {
        super(path);
    }

    public void write(Review review) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));) {
            writer.write(review.serialize());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
