package dao;

public interface Dao<T> {
    public T getById(int id) throws NoSuchRecordException;
}
