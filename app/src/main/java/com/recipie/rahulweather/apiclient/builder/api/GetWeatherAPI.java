package com.recipie.rahulweather.apiclient.builder.api;

import android.os.Bundle;

import com.google.gson.Gson;
import com.recipie.rahulweather.apiclient.builder.APIBuilder;
import com.recipie.rahulweather.apiclient.builder.Interfaces.API;
import com.recipie.rahulweather.apiclient.builder.Interfaces.WeatherService;
import com.recipie.rahulweather.apiclient.builder.request.WeatherRequest;
import com.recipie.rahulweather.apiclient.builder.response.WeatherResponse;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by developers on 23/10/16.
 */

public class GetWeatherAPI extends APIBuilder implements API<WeatherRequest, WeatherResponse, WeatherService> {

    /**
     * Retrofit builder used for networking
     */
    private Retrofit retrofit;

    /**
     * Query
     */
    private String q;
    private String format;
    private String env;

    /**
     * Constructor
     */
    public GetWeatherAPI() {
        super();
    }

    public static GetWeatherAPI build(Bundle param) {
        GetWeatherAPI getWeatherAPI = new GetWeatherAPI();
        getWeatherAPI.buildParams(param);
        getWeatherAPI.buildRetrofit();
        return getWeatherAPI;
    }

    /**
     * Builds and sets the retrofit object out of converter factory
     */
    @Override
    public void buildRetrofit() {
        super.buildRetrofit();
        this.retrofit = super.getRetrofit();
    }

    /**
     * Validates and creates the parameters requested for this request
     */
    @Override
    public void buildParams(Bundle params) {
        q = params.getString("q");
        format = params.getString("format");
        env = params.getString("env");
    }

    /**
     * Creates the custom GsonConverterFactory
     *
     * @return GsonConverterFactory
     */
    private GsonConverterFactory createConverterFactory() {
        Gson gson = new Gson();
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

        return gsonConverterFactory;
    }

    @Override
    public void execute(Callback<WeatherResponse> callback) {
        WeatherRequest weatherRequest = createRequest();
        WeatherService weatherService = createService();

        Call<WeatherResponse> call = weatherService.getWeather(q, format, env);
        call.enqueue(callback);
    }

    @Override
    public WeatherRequest createRequest() {
        WeatherRequest weatherRequest = new WeatherRequest();
        return weatherRequest;
    }

    @Override
    public WeatherService createService() {
        WeatherService weatherService = retrofit.create(WeatherService.class);
        return weatherService;
    }
}
