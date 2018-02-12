package com.example.mobiletestapp.network.event;

public interface IDataCallback<DATA> {
    void onStart();

    void onResponse(DATA data);

    void onFail(Throwable throwable);
}
