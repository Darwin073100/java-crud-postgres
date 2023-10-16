package com.connectiondb.repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
    List<T> findAll() throws SQLException;
    
    T findById(Integer id) throws SQLException;

    void save(T t);

    void delete(Integer id);
}
