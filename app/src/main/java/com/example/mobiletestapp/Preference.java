package com.example.mobiletestapp;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preference {

    private static final String TOKEN = "token";

    private static SharedPreferences sharedPreferences;

    private static SharedPreferences getPreferences() {
        if (sharedPreferences == null) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        }
        return sharedPreferences;
    }

    public static String getToken() {
        return getPreferences().getString(TOKEN, "");
    }

    public static void saveToken(String token) {
        getPreferences().edit().putString(TOKEN, token).apply();
    }
}
