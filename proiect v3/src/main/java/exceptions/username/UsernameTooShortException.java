package exceptions.username;

public class UsernameTooShortException extends InvalidUsernameException{
    public UsernameTooShortException() {
        super("Username is too short!");
    }
}
