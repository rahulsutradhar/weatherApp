package com.recipie.rahulweather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by developers on 23/10/16.
 */

public class Condition {

    /**
     * Date
     */
    private String date;

    /**
     * Temperature
     */
    private String temp;

    /**
     * Weather type
     */
    @SerializedName("text")
    private String weather;

    /**
     * Day
     */
    private String day;

    /**
     * Highest temperature
     */
    private String high;

    /**
     * Lowest Temperature
     */
    private String low;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }
}
