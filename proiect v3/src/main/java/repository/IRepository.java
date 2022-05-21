package repository;

import java.sql.SQLException;

public interface IRepository<T> {
    public void create(T t) throws SQLException;
    public T find(Integer id) throws SQLException;
    public void update(T t) throws SQLException;
    public void delete(T t) throws SQLException;
}
