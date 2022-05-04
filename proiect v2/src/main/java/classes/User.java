package classes;

import CSV.CSVSerialize;
import design_patterns.Observable;
import design_patterns.Observer;
import exceptions.user.NoCardsException;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class User implements Observer, CSVSerialize {
    @Builder.Default
    private List<Purchasable> library = new ArrayList<>();
    private String username;
    private String password;
    private Cart cart;
    @Builder.Default
    private List<Card> cards = new ArrayList<>();
    @Builder.Default
    private List<Game> wishList = new ArrayList<>();
    @Builder.Default
    private boolean loggedIn = false;


    public void addCard(Card newCard) {
        for (Card card: cards) {
            if (card.getNumber().equals(newCard.getNumber())) {
                return;
            }
        }
        cards.add(newCard);
        newCard.setUser(this);
    }

    @Override
    public void notify(Observable o, Object arg) {
        if (o instanceof GameWishlist) {
            wishlistNotifcation(arg);
        }
    }

    public void wishlistNotifcation(Object arg) {
        Game game = (Game)arg;
        System.out.println("User " + getUsername() + " notified for " + game.getTitle() + " sale!");
    }

    public void wishlistGame(Game game) {
        this.wishList.add(game);
    }

    public void removeFromWishlist(Game game) {
        this.wishList.remove(game);
    }

    public Card getMainCard() throws NoCardsException {
        if (cards.size() == 0) {
            throw new NoCardsException();
        }
        return cards.get(0);
    }

    public void addPurchase(Purchasable purchasable) {
        library.add(purchasable);
    }

    @Override
    public String serialize() {
        return username + ", " + password + "\n";
    }
}


//    public User() {}
//
//    public User(UserBuilder ub) {
//        this.library = ub.user.library;
//        this.username = ub.user.username;
//        this.password = ub.user.password;
//        this.cart = ub.user.cart;
//        this.cards = ub.user.cards;
//        this.wishList = ub.user.wishList;
//
//        if (this.cart == null) {
//            this.cart = new Cart(this);
//        }
//    }
//
//    public static UserBuilder builder() {
//        return new UserBuilder();
//    }
//
//    public static class UserBuilder {
//        private User user = new User();
//
//        public UserBuilder username(String username) {
//            this.user.username = username;
//            return this;
//        }
//
//        public UserBuilder password(String password) {
//            this.user.password = password;
//            return this;
//        }
//
//        public User build() {
//            User user = new User(this);
//            return user;
//        }
//    }
