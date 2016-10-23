package com.recipie.rahulweather.viewmodel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.recipie.rahulweather.R;
import com.recipie.rahulweather.adapter.ForecastAdapter;
import com.recipie.rahulweather.apiclient.builder.api.GetWeatherAPI;
import com.recipie.rahulweather.apiclient.builder.response.WeatherResponse;
import com.recipie.rahulweather.fragment.MainFragment;
import com.recipie.rahulweather.global.Constant;
import com.recipie.rahulweather.model.Astronomy;
import com.recipie.rahulweather.model.Atmosphere;
import com.recipie.rahulweather.model.Channel;
import com.recipie.rahulweather.model.Condition;
import com.recipie.rahulweather.model.Location;
import com.recipie.rahulweather.model.Query;
import com.recipie.rahulweather.model.Units;
import com.recipie.rahulweather.model.Wind;
import com.squareup.okhttp.ResponseBody;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

import retrofit.Callback;
import retrofit.Converter;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by developers on 23/10/16.
 */

public class MainFragmentViewModel extends BaseViewModel {

    /**
     * Main Fragment
     */
    private MainFragment fragment;

    /**
     * Title
     */
    private String title;

    /**
     * Channel
     */
    private Channel channel;

    /**
     * Unit
     */
    private Units units;

    /**
     * Location
     */
    private Location location;

    /**
     * Astronoy
     */
    private Astronomy astronomy;

    /**
     * Condition
     */
    private Condition condition;

    /**
     * Atmosphere
     */
    private Atmosphere atmosphere;

    /**
     * Wind
     */
    private Wind wind;

    /**
     * Recyclerview
     */
    private RecyclerView recyclerView;

    /**
     * Adapter
     */
    private ForecastAdapter adapter;

    /**
     * Constructor
     */
    public MainFragmentViewModel(MainFragment fragment) {
        this.fragment = fragment;
        this.channel = new Channel();
        this.units = new Units();
        this.location = new Location();
        this.condition = new Condition();
        this.astronomy = new Astronomy();
        this.atmosphere = new Atmosphere();
        this.wind = new Wind();
    }

    public void fetchWeather() {

        Callback<WeatherResponse> callback = new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Response<WeatherResponse> response,
                                   Retrofit retrofit) {

                try {
                    if (response.isSuccess()) {

                        WeatherResponse weatherResponse = response.body();
                        Channel channel = weatherResponse.getQuery().getResults().getChannel();
                        setChannel(channel);
                        setCondition(channel.getItem().getCondition());
                        setUnits(channel.getUnits());
                        setLocation(channel.getLocation());
                        setAstronomy(channel.getAstronomy());
                        setWind(channel.getWind());
                        setAtmosphere(channel.getAtmosphere());
                        notifyChange();

                        populateForecast();


                    } else {
                        if (response != null && !response.isSuccess() && response.errorBody() != null) {
                            //DO ERROR HANDLING HERE
                            Toast.makeText(fragment.getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(fragment.getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(fragment.getActivity(), "Something went wrong " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        //parameters
        Bundle params = new Bundle();
        params.putCharSequence("q", "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"chicago\")");
        params.putCharSequence("format", "json");
        params.putCharSequence("env", "store://datatables.org/alltableswithkeys");

        GetWeatherAPI getWeatherAPI = GetWeatherAPI.build(params);
        getWeatherAPI.execute(callback);

    }

    /**
     * populate forecast
     */
    public void populateForecast() {
        try {
            recyclerView = (RecyclerView) fragment.getActivity().findViewById(R.id.weatherForecast);
            adapter = new ForecastAdapter(fragment, channel.getItem().getForecast(), units);
            recyclerView.setLayoutManager(new LinearLayoutManager(fragment.getActivity()));
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {

        }
    }


    /*******************************
     * Getter Setter
     ********************************/
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Units getUnits() {
        return units;
    }

    public void setUnits(Units units) {
        this.units = units;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public MainFragment getFragment() {
        return fragment;
    }

    public void setFragment(MainFragment fragment) {
        this.fragment = fragment;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Astronomy getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(Astronomy astronomy) {
        this.astronomy = astronomy;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    /******************************
     * Bind values
     *******************************/

    public String getCurrentTemperature() {
        String temp = "";
        try {
            if (condition != null && units != null) {
                temp = condition.getTemp() + Constant.getDegreeSymbol() + units.getTemperature();
            }
        } catch (Exception e) {
            temp = " ";
        }
        return temp;
    }

    public String getSkyCondition() {
        String skyType = "";
        try {
            if (condition != null) {
                skyType = condition.getWeather();
            }
        } catch (Exception e) {
            skyType = " ";
        }
        return skyType;
    }

    public String getCurrentDate() {
        String date = "";
        try {
            if (condition != null) {
                date = condition.getDate();
            }
        } catch (Exception e) {
            date = " ";
        }
        return date;
    }

    public String getCurrentLocation() {
        String locationText = " ";
        try {
            if (location != null) {
                locationText = location.getCity() + ", " + location.getCountry();
            }
        } catch (Exception e) {
            locationText = " ";
        }
        return locationText;
    }

    public String getSunrise() {
        String riseTime = " ";
        try {
            if (astronomy != null) {
                riseTime = astronomy.getSunrise().toUpperCase();
            }
        } catch (Exception e) {
            riseTime = " ";
        }
        return riseTime;
    }

    public String getSunset() {
        String setTime = " ";
        try {
            if (astronomy != null) {
                setTime = astronomy.getSunset().toUpperCase();
            }
        } catch (Exception e) {
            setTime = " ";
        }
        return setTime;
    }

    public String getCurrentPressure() {
        String press = " ";
        try {
            if (atmosphere != null && units != null) {
                press = atmosphere.getPressure() + units.getPressure();
            }
        } catch (Exception e) {
            press = " ";
        }
        return press;
    }

    public String getCurrentWind() {
        String windSpeed = " ";
        try {
            if (wind != null && units != null) {
                windSpeed = wind.getSpeed() + units.getSpeed();
            }
        } catch (Exception e) {
            windSpeed = " ";
        }
        return windSpeed;
    }
}
