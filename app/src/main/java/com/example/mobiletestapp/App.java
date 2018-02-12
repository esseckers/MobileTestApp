package com.example.mobiletestapp;

import android.app.Application;

public class App extends Application {

    private static App sApplication;

    public static App getInstance() {
        return sApplication;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }
}
