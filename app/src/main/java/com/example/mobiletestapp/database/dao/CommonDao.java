package com.example.mobiletestapp.database.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class CommonDao<T> extends BaseDaoImpl<T, String> {

    public CommonDao(ConnectionSource connectionSource, Class<T> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<T> getAll() throws SQLException {
        return queryForAll();
    }
}
