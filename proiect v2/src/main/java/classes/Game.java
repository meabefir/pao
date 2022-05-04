package classes;

import CSV.CSVSerialize;
import enums.GameCategory;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
public class Game extends Purchasable implements Comparable<Game>, CSVSerialize {
    private String title;
    private Integer rating;
    private GameSpecs minSpecs;
    private GameSpecs recommendedSpecs;
    @Builder.Default
    private List<GameCategory> categories = new ArrayList<>();
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();
    @Builder.Default
    private GameWishlist wishlist = new GameWishlist();

    @Override
    public int compareTo(Game o) {
        int ratingCmp = o.rating.compareTo(this.rating);
        return ratingCmp == 0 ? o.title.compareTo(this.title) : ratingCmp;
    }

    @Override
    public String toString() {
        return "Game{" +
                "title='" + title + '\'' +
                ", rating=" + rating +
                ", minSpecs=" + minSpecs +
                ", recommendedSpecs=" + recommendedSpecs +
                ", categories=" + categories +
                ", reviews=" + reviews +
                ", price=" + price +
                '}';
    }

    public void addWishlist(User user) {
        wishlist.addObserver(user);
    }

    public void goOnSale() {
        wishlist.notifyObservers(this);
    }

    @Override
    public String serialize() {
        return this.title + ", " + this.rating + ", " +
                this.minSpecs.serialize() + ", " + this.recommendedSpecs.serialize() + ", " + this.price + "\n";
    }
}
