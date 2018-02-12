package com.example.mobiletestapp.database;

import android.util.Log;

import com.example.mobiletestapp.database.dao.CommonDao;
import com.example.mobiletestapp.model.ShotModel;

import java.sql.SQLException;
import java.util.List;


public class DataBaseGateway {

    private static final String TAG = DataBaseGateway.class.getSimpleName();
    private static DataBaseGateway instance;
    private DataBaseHelper databaseHelper = new DataBaseHelper();

    public static DataBaseGateway getInstance() {
        if (instance == null) {
            instance = new DataBaseGateway();
        }
        return instance;
    }

    /**
     * CommonDao calls
     */
    private <T> CommonDao<T> getDao(Class<T> aClass) throws SQLException {
        return new CommonDao<>(databaseHelper.getConnectionSource(), aClass);
    }

    public <T> void createOrUpdateList(Class<T> aClass, List<T> list) {
        try {
            for (T item : list) {
                getDao(aClass).createOrUpdate(item);
            }
        } catch (SQLException e) {
            Log.e(TAG, "createOrUpdateList", e);
        }
    }

    public List<ShotModel> getModels() {
        try {
            return getDao(ShotModel.class).getAll();
        } catch (SQLException e) {
            Log.e(TAG, "getModels", e);
        }
        return null;
    }
}
