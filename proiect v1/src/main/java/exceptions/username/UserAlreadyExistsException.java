package exceptions.username;

public class UserAlreadyExistsException extends InvalidUsernameException {
    public UserAlreadyExistsException() {
        super("Username already exists!");
    }
}
