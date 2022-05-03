package exceptions.store;

public class GameNotFoundException extends StoreServiceException{
    public GameNotFoundException() {
        super("Game not found!");
    }
}
