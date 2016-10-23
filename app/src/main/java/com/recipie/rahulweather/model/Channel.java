package com.recipie.rahulweather.model;

/**
 * Created by developers on 23/10/16.
 */

public class Channel {

    /**
     * Units
     */
    private Units units;

    /**
     * Location
     */
    private Location location;

    /**
     * Atmosphere
     */
    private Atmosphere atmosphere;

    /**
     * Wind
     */
    private Wind wind;

    /**
     * Astronomy
     */
    private Astronomy astronomy;

    /**
     * Item
     */
    private Item item;


    /**********************************
     * Getter Setter
     **********************************/
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

    public Astronomy getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(Astronomy astronomy) {
        this.astronomy = astronomy;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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
}
