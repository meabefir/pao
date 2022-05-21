package CSV;

import models.Review;
import enums.ReviewType;

import java.util.ArrayList;
import java.util.List;

public class ReviewCSVReader extends CSVReader implements CSVParse{
    public ReviewCSVReader(String path) {
        super(path);
    }

    @Override
    public List<Object> parseData() {
        List<Object> ret = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            List<String> line = data.get(i);
            Review review = Review.builder().text(line.get(0)).type(ReviewType.valueOf(line.get(1))).build();
            ret.add(review);
        }

        return  ret;
    }
}
