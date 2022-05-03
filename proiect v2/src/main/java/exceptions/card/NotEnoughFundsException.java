package exceptions.card;

public class NotEnoughFundsException extends Exception{
    public NotEnoughFundsException() {
        super("Not enough funds exception!");
    }
}
