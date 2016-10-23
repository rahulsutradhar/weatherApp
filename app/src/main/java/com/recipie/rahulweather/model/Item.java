package com.recipie.rahulweather.model;

import java.util.ArrayList;

/**
 * Created by developers on 23/10/16.
 */

public class Item {

    /**
     * Condition
     */
    private Condition condition;

    /**
     * Forecast
     */
    private ArrayList<Condition> forecast;

    /**
     * Description
     */
    private String description;

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public ArrayList<Condition> getForecast() {
        return forecast;
    }

    public void setForecast(ArrayList<Condition> forecast) {
        this.forecast = forecast;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
