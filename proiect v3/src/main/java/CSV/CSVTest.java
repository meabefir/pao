package CSV;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class CSVTest {
    @CsvBindByPosition(position = 0)
    private String title;
    @CsvBindByPosition(position = 1)
    private String title2;
    private String title3;
}
