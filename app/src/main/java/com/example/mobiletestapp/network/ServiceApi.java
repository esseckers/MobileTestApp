package com.example.mobiletestapp.network;


import com.example.mobiletestapp.model.ShotModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ServiceApi {

    @GET("shots")
    Single<List<ShotModel>> fetchShots(@Query("page") int page, @Query("per_page") int perPage, @Query("list") String list);
}