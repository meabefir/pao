package exceptions.user;

public class WrongPasswordException extends UserServiceException {
    public WrongPasswordException() {
        super("Wrong password!");
    }
}
