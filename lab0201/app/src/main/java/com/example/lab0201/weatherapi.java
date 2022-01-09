package com.example.lab0201;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface weatherapi {
        @GET("weather")
        Call<Example> getweather(@Query("q") String city, @Query("appid") String key);
    }

