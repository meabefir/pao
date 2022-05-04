package classes;

import CSV.CSVSerialize;
import enums.ReviewType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Review implements CSVSerialize {
    private User user;
    private String text;
    private ReviewType type;

    @Override
    public String serialize() {
        return text + ", " + type + "\n";
    }
}
