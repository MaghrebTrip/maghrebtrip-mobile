package com.maghrebtrip.cities;

public class PopularCity {
    private String cityName;
    private String cityImage;

    public PopularCity(String cityName, String cityImage) {
        this.cityName = cityName;
        this.cityImage = cityImage;
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
}
