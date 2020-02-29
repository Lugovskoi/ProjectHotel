package by.lugovskoi.dao;

public interface Dao <T> {
    T save(T entity);
    T find(int id);
    boolean delete(int id);
}
