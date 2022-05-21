package repository;

import enums.*;
import models.*;

import java.sql.*;

public class UserRepository implements IRepository<User> {

    Connection connection;
    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User game) throws SQLException {

        PreparedStatement ps = connection.prepareStatement("insert into user (id, username, password)" +
                " values (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, game.getId());
        ps.setString(2, game.getUsername());
        ps.setString(3, game.getPassword());
        ps.execute();
    }

    @Override
    public User find(Integer id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from user where id=?;");
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return User.builder()
                    .id(rs.getInt("id"))
                    .username(rs.getString("username"))
                    .password(rs.getString("password"))
                    .build();
        }
        return null;
    }

    @Override
    public void update(User game) throws SQLException {
        User toModify = find(game.getId());
        if (toModify == null) return;

        PreparedStatement ps = connection.prepareStatement("update user set id=?, username=?, password=?" +
                " where id=?;");
        ps.setInt(1, game.getId());
        ps.setString(2, game.getUsername());
        ps.setString(3, game.getPassword());
        ps.setInt(4, game.getId());

        ps.execute();
    }

    @Override
    public void delete(User game) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("delete from user where id=?;");
        ps.setInt(1, game.getId());
        ps.execute();
    }
}
