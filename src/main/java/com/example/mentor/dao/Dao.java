package com.example.mentor.dao;

import java.util.List;
import java.util.Optional;

public interface Dao <K, V> {
    void save(V entity);
    Optional<V> findBYId(K id);
    List<V> findAll();
    boolean deleteById(K id);
}
