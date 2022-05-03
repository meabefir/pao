package classes;

import enums.ReviewType;
import lombok.Data;

@Data
public class Review {
    private User user;
    private String text;
    private ReviewType type;
}
