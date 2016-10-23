package com.recipie.rahulweather.apiclient.builder.Interfaces;

import com.recipie.rahulweather.apiclient.builder.response.WeatherResponse;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by developers on 23/10/16.
 */

public interface WeatherService {

    @GET("/v1/public/yql")
    Call<WeatherResponse> getWeather(@Query("q") String q,@Query("format") String format,@Query("env")String env);
}
