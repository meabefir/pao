package exceptions.user;

public class UserNotLoggedIn extends UserServiceException{
    public UserNotLoggedIn() {
        super("User not logged in!");
    }
}
