package CSV;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CSVReader {
    private String content;
    protected List<List<String>> data = new ArrayList<>();

    public CSVReader(String path) {
        try {
            content = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
            List<String> lines = new ArrayList<>();
            lines = Arrays.asList(content.split("\n"));

            for (int i = 0; i < lines.size(); i++) {
                var row = Arrays.asList(lines.get(i).trim().split(","));
                for (int j = 0; j < row.size(); j++) {
                    row.set(j, row.get(j).trim());
                }
                data.add(row);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void test() {
        System.out.println();
        System.out.println(data.size());
        for (var line: data) {
            for (String s: line) {
                System.out.print(s + " " + s.length() + " ");
            }
            System.out.println();
        }
    }
}
