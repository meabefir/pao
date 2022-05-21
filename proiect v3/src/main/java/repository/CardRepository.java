package repository;

import enums.*;
import models.Card;
import models.Game;
import models.GameSpecs;

import java.sql.*;

public class CardRepository implements IRepository<Card> {

    Connection connection;
    public CardRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Card game) throws SQLException {

        PreparedStatement ps = connection.prepareStatement("insert into card (id, number, pin, type, currency, funds)" +
                " values (?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, game.getId());
        ps.setString(2, game.getNumber());
        ps.setString(3, game.getPin());
        ps.setString(4, game.getType().toString());
        ps.setString(5, game.getCurrency().toString());
        ps.setDouble(6, game.getFunds());
        ps.execute();
    }

    @Override
    public Card find(Integer id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from card where id=?;");
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return Card.builder()
                    .id(rs.getInt("id"))
                    .number(rs.getString("number"))
                    .pin(rs.getString("pin"))
                    .type(CardType.valueOf(rs.getString("type")))
                    .currency(Currency.valueOf(rs.getString("currency")))
                    .funds(rs.getDouble("funds"))
                    .build();
        }
        return null;
    }

    @Override
    public void update(Card game) throws SQLException {
        Card toModify = find(game.getId());
        if (toModify == null) return;

        PreparedStatement ps = connection.prepareStatement("update card set id=?, number=?, pin=?," +
                " type=?, currency=?, currency=?, funds=? where id=?;");
        ps.setInt(1, game.getId());
        ps.setString(2, game.getNumber());
        ps.setString(3, game.getPin());
        ps.setString(4, game.getType().toString());
        ps.setString(5, game.getCurrency().toString());
        ps.setDouble(6, game.getFunds());
        ps.setInt(7, game.getId());

        ps.execute();
    }

    @Override
    public void delete(Card game) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("delete from card where id=?;");
        ps.setInt(1, game.getId());
        ps.execute();
    }
}
