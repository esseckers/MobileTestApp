package com.example.mobiletestapp.network;


import com.example.mobiletestapp.model.ShotModel;

import java.util.List;

import io.reactivex.Single;

public class NetworkManager implements ICommonProvider {

    private static NetworkManager instance;
    private ServiceApi api;

    private NetworkManager() {
        this.api = ApiConnection.provideServiceApi();
    }

    public static NetworkManager getInstance() {
        NetworkManager localInstance = instance;
        if (localInstance == null) {
            synchronized (NetworkManager.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new NetworkManager();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Single<List<ShotModel>> fetchShots() {
        return api.fetchShots(1, 50, "attachments, debuts, playoffs, rebounds, teams");
    }
}