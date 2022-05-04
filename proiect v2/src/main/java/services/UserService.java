package services;

import classes.Card;
import classes.User;
import exceptions.user.UserNotFoundException;
import exceptions.user.UserNotLoggedIn;
import exceptions.user.UserServiceException;
import exceptions.user.WrongPasswordException;
import exceptions.username.InvalidUsernameException;
import exceptions.username.UserAlreadyExistsException;
import exceptions.username.UsernameTooShortException;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private static UserService instance;

    private Map<String, User> users = new HashMap<>();

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    private UserService() {}

    public void addCard(User user, Card card) {
        try {
            if (!users.containsKey(user.getUsername())) {
                throw new UserNotFoundException();
            }
            if (!user.isLoggedIn()) {
                throw new UserNotLoggedIn();
            }

            user.addCard(card);
            LoggerService.getInstance().log("Added card with number " + card.getNumber() + " for user " + user.getUsername());
        } catch (UserServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    private void validateUsername(String username) throws InvalidUsernameException {
        if (username.length() < 5) {
            throw new UsernameTooShortException();
        }
    }

    public void registerUser(User user) {
        try {
            validateUsername(user.getUsername());

            if (users.containsKey(user.getUsername())) {
                throw new UserAlreadyExistsException();
            }
            users.put(user.getUsername(), user);
            user.setLoggedIn(true);

            LoggerService.getInstance().log("Registered user " + user.getUsername());
        } catch (InvalidUsernameException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loginUser(User user, String password) {
        try {
            if (!users.containsKey(user.getUsername())) {
                throw new UserNotFoundException();
            }
            if (!user.getPassword().equals(password)) {
                throw new WrongPasswordException();
            }

            user.setLoggedIn(true);
            LoggerService.getInstance().log("Logged user " + user.getUsername());
        } catch (UserServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    public void logoutUser(User user) {
        try {
            if (!users.containsKey(user.getUsername())) {
                throw new UserNotFoundException();
            }
            if (!user.isLoggedIn()) {
                throw new UserNotLoggedIn();
            }

            user.setLoggedIn(false);
            LoggerService.getInstance().log("Logged out user " + user.getUsername());
        } catch (UserServiceException e) {
            System.out.println(e.getMessage());
        }
    }
}
