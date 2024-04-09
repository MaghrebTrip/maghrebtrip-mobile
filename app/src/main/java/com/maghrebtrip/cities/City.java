package com.maghrebtrip.cities;

import java.io.Serializable;

public class City implements Serializable {
    private String cityName;
    private String cityImage;
    private double rating;
    private String description;

    public City(String cityName, String cityImage, double rating, String description) {
        this.cityName = cityName;
        this.cityImage = cityImage;
        this.rating = rating;
        this.description = description;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityImage() {
        return cityImage;
    }

    public void setCityImage(String cityImage) {
        this.cityImage = cityImage;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
