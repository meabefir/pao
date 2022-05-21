package repository;

import enums.*;
import models.Card;
import models.Game;
import models.GameSpecs;
import models.Review;

import java.sql.*;

public class ReviewRepository implements IRepository<Review> {

    Connection connection;
    public ReviewRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Review game) throws SQLException {

        PreparedStatement ps = connection.prepareStatement("insert into review (id, text, type)" +
                " values (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, game.getId());
        ps.setString(2, game.getText());
        ps.setString(3, game.getType().toString());
        ps.execute();
    }

    @Override
    public Review find(Integer id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from review where id=?;");
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return Review.builder()
                    .id(rs.getInt("id"))
                    .text(rs.getString("text"))
                    .type(ReviewType.valueOf(rs.getString("type")))
                    .build();
        }
        return null;
    }

    @Override
    public void update(Review game) throws SQLException {
        Review toModify = find(game.getId());
        if (toModify == null) return;

        PreparedStatement ps = connection.prepareStatement("update review set id=?, text=?, type=?" +
                " where id=?;");
        ps.setInt(1, game.getId());
        ps.setString(2, game.getText());
        ps.setString(3, game.getType().toString());
        ps.setInt(4, game.getId());

        ps.execute();
    }

    @Override
    public void delete(Review game) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("delete from review where id=?;");
        ps.setInt(1, game.getId());
        ps.execute();
    }
}
