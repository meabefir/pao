package exceptions.card;

public class CardWithNoUserException extends Exception{
    public CardWithNoUserException() {
        super("Card with no user!");
    }
}
