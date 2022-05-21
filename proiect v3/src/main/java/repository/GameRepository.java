package repository;

import enums.CPU;
import enums.GPU;
import enums.RAM;
import models.Game;
import models.GameSpecs;

import java.sql.*;

public class GameRepository implements IRepository<Game> {

    Connection connection;
    public GameRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Game game) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into game (id, title, rating, min_ram, min_gpu, min_cpu, rec_ram, rec_gpu, rec_cpu, price)" +
                " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, game.getId());
        ps.setString(2, game.getTitle());
        ps.setInt(3, game.getRating());
        ps.setString(4, game.getMinSpecs().getRam().toString());
        ps.setString(5, game.getMinSpecs().getGpu().toString());
        ps.setString(6, game.getMinSpecs().getCpu().toString());
        ps.setString(7, game.getRecommendedSpecs().getRam().toString());
        ps.setString(8, game.getRecommendedSpecs().getGpu().toString());
        ps.setString(9, game.getRecommendedSpecs().getCpu().toString());
        ps.setDouble(10, game.getPrice());
        ps.execute();
    }

    @Override
    public Game find(Integer id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from game where id=?;");
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return Game.builder()
                    .id(rs.getInt("id"))
                    .title(rs.getString("title"))
                    .rating(rs.getInt("rating"))
                    .minSpecs(GameSpecs.builder()
                            .ram(RAM.valueOf(rs.getString("min_ram")))
                            .cpu(CPU.valueOf(rs.getString("min_cpu")))
                            .gpu(GPU.valueOf(rs.getString("min_gpu"))).build())
                    .recommendedSpecs(GameSpecs.builder()
                            .ram(RAM.valueOf(rs.getString("rec_ram")))
                            .cpu(CPU.valueOf(rs.getString("rec_cpu")))
                            .gpu(GPU.valueOf(rs.getString("rec_gpu"))).build())
                    .price(rs.getDouble("price"))
                    .build();
        }
        return null;
    }

    @Override
    public void update(Game game) throws SQLException {
        Game toModify = find(game.getId());
        if (toModify == null) return;

        PreparedStatement ps = connection.prepareStatement("update game set id=?, title=?, rating=?," +
                " min_ram=?, min_gpu=?, min_cpu=?, rec_ram=?, rec_gpu=?, rec_cpu=?, price=? where id=?;");
        ps.setInt(1, game.getId());
        ps.setString(2, game.getTitle());
        ps.setInt(3, game.getRating());
        ps.setString(4, game.getMinSpecs().getRam().toString());
        ps.setString(5, game.getMinSpecs().getGpu().toString());
        ps.setString(6, game.getMinSpecs().getCpu().toString());
        ps.setString(7, game.getRecommendedSpecs().getRam().toString());
        ps.setString(8, game.getRecommendedSpecs().getGpu().toString());
        ps.setString(9, game.getRecommendedSpecs().getCpu().toString());
        ps.setDouble(10, game.getPrice());
        ps.setInt(11, game.getId());

        ps.execute();
    }

    @Override
    public void delete(Game game) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("delete from game where id=?;");
        ps.setInt(1, game.getId());
        ps.execute();
    }
}
