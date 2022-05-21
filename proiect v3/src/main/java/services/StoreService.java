package services;

import models.Card;
import models.Game;
import models.User;
import enums.GameCategory;
import exceptions.store.GameNotFoundException;
import exceptions.store.StoreServiceException;
import exceptions.user.UserNotLoggedIn;

import java.util.*;


public class StoreService {
    Set<Game> games = new TreeSet<>();

    private static StoreService instance;

    public static StoreService getInstance() {
        if (instance == null) {
            instance = new StoreService();
        }
        return instance;
    }

    private StoreService() {}

    public void addGame(Game game) {
        System.out.println("add game");
        games.add(game);
        LoggerService.getInstance().log("Game " + game.toString() + " added to store.");
    }

    public List<Game> getMostPopular() {
        LoggerService.getInstance().log("Fetched most popular games.");
        return new ArrayList<>(games);
    }

    public List<Game> getByCategory(GameCategory gc) {
        LoggerService.getInstance().log("Fetched games in category " + gc);
        return getByCategory(Arrays.asList(new GameCategory[]{gc}));
    }

    public List<Game> getByCategory(List<GameCategory> categories) {
        List<Game> ret = new ArrayList<>();
        for (Game game: games) {
            for (GameCategory cg: game.getCategories()) {
                if (categories.contains(cg)) {
                    ret.add(game);
                    break;
                }
            }
        }
        return ret;
    }

    public void wishlistGame(User user, Game game) {
        try {
            if (user.isLoggedIn() == false) {
                throw new UserNotLoggedIn();
            }

            game.addWishlist(user);
            user.wishlistGame(game);

            LoggerService.getInstance().log("User " + user.getUsername() + " wishlisted " + game.getTitle());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void goOnSale(Game game) {
        try {
            if (!games.contains(game)) {
                throw new GameNotFoundException();
            }
            game.goOnSale();
            LoggerService.getInstance().log("Game " + game.getTitle() + " is on sale.");
        } catch (StoreServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    public void buyGame(User user, Game game) {
//        check if logged, game exists, check account, add to library, remove from wishlist
        try {
            if (!user.isLoggedIn()) {
                throw new UserNotLoggedIn();
            }
            if (!games.contains(game)) {
                throw new GameNotFoundException();
            }

            Card mainCard = user.getMainCard();
            mainCard.buy(game);
            user.removeFromWishlist(game);
            LoggerService.getInstance().log("User " + user.getUsername() + " bought " + game.getTitle());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
