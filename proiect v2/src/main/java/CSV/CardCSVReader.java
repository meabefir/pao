package CSV;

import classes.Card;
import classes.Review;
import enums.CardType;
import enums.Currency;
import enums.ReviewType;

import java.util.ArrayList;
import java.util.List;

public class CardCSVReader extends CSVReader implements CSVParse {

    public CardCSVReader(String path) {
        super(path);
    }

    @Override
    public List<Object> parseData() {

        List<Object> ret = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            List<String> line = data.get(i);
            Card card = Card.builder().number(line.get(0)).pin(line.get(1))
                    .type(CardType.valueOf(line.get(2))).currency(Currency.valueOf(line.get(3))).funds(Double.valueOf(line.get(4))).build();
            ret.add(card);
        }

        return  ret;

    }
}
