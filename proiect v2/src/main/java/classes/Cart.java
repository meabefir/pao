package classes;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {
    private List<Purchasable> purchasables = new ArrayList<>();
    private User user;

    public Cart(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cart=" + purchasables +
                ", user=" + user.getUsername() +
                '}';
    }
}
