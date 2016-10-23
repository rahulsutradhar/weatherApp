package com.recipie.rahulweather.viewmodel;

import com.recipie.rahulweather.fragment.MainFragment;
import com.recipie.rahulweather.global.Constant;
import com.recipie.rahulweather.model.Condition;
import com.recipie.rahulweather.model.Units;

/**
 * Created by developers on 23/10/16.
 */

public class ItemWeatherForecastViewModel extends BaseViewModel {

    /**
     * MainFragment
     */
    private MainFragment fragment;

    /**
     * Condition
     */
    private Condition condition;

    /**
     * Unit
     */
    private Units units;

    /**
     * Constructor
     */
    public ItemWeatherForecastViewModel(MainFragment fragment, Condition condition, Units units) {
        this.fragment = fragment;
        this.condition = condition;
        this.units = units;
    }

    public String getSkyType() {
        String text = " ";

        try {
            if (condition != null) {
                text = condition.getWeather();
            }
        } catch (Exception e) {
            text = " ";
        }
        return text;
    }

    public String getDate() {
        String text = " ";
        try {
            if (condition != null) {
                text = condition.getDay() + ", " + condition.getDate();
            }
        } catch (Exception e) {
            text = " ";
        }
        return text;
    }

    public String getLowestTemperature() {
        String text = " ";
        try {
            if (condition != null && units != null) {
                text = condition.getLow() + Constant.getDegreeSymbol() + units.getTemperature();
            }
        } catch (Exception e) {
            text = " ";
        }
        return text;
    }

    public String getHighestTemperature() {
        String text = " ";
        try {
            if (condition != null && units != null) {
                text = condition.getHigh() + Constant.getDegreeSymbol() + units.getTemperature();
            }
        } catch (Exception e) {
            text = " ";
        }
        return text;
    }
}
