package classes;

import CSV.CSVSerialize;
import enums.CardType;
import enums.Currency;
import exceptions.card.CardWithNoUserException;
import exceptions.card.NotEnoughFundsException;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Card implements CSVSerialize {
    private User user;
    private String number;
    private String pin;
    private CardType type;
    private Currency currency;
    private Double funds;

    public void buy(Purchasable purchasable) throws CardWithNoUserException, NotEnoughFundsException {
        if (user == null) {
            throw new CardWithNoUserException();
        }
        if (funds - purchasable.price < 0) {
            throw new NotEnoughFundsException();
        }
        funds -= purchasable.price;
        user.addPurchase(purchasable);
    }

    @Override
    public String toString() {
        return "Card{" +
                "user="  +user.getUsername() + '\n' +
                "number='" + number + '\'' +
                ", pin='" + pin + '\'' +
                ", type=" + type +
                ", currency=" + currency +
                ", funds=" + funds +
                '}';
    }

    @Override
    public String serialize() {
        return number + ", " + pin + ", " + type + ", " + currency + ", " + funds + "\n";
    }
}
