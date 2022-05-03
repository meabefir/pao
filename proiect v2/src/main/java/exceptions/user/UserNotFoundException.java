package exceptions.user;

public class UserNotFoundException extends UserServiceException{
    public UserNotFoundException() {
        super("User not found!");
    }
}
