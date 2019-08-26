package pl.sda.mp3analysis.dao;

import java.util.List;

public interface DaoInterface<T> {
    T save(T entity);
    List<T> findAll();
    List<T> findByTitle(String title);
    List<T> findByArtist(String title);
}
