package exceptions.user;

public class UserServiceException extends Exception{
    public UserServiceException(String message) {
        super(message);
    }
}
