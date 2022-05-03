package exceptions.user;

public class NoCardsException extends UserServiceException{
    public NoCardsException() {
        super("No cards on account!");
    }
}
