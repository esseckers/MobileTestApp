package com.example.mobiletestapp.network;


import com.example.mobiletestapp.model.ShotModel;

import java.util.List;

import io.reactivex.Single;

public interface ICommonProvider {

    Single<List<ShotModel>> fetchShots();


}
