package CSV;

import java.io.FileWriter;
import java.io.PrintWriter;

public class CSVWriter {
    protected String path;

    public CSVWriter(String path) {
        this.path = path;
        try {

            PrintWriter writer = new PrintWriter(path);
            writer.print("");
            writer.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
