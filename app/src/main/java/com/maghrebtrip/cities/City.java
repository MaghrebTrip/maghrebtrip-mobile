package com.maghrebtrip.cities;

public class City {
    private String cityName;
    private String cityImage;
    private double rating;

    public City(String cityName, String cityImage, double rating) {
        this.cityName = cityName;
        this.cityImage = cityImage;
        this.rating = rating;
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
}
