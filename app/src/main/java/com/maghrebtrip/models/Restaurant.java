package com.maghrebtrip.models;

import java.util.List;

public class Restaurant extends Attraction {

    private String cuisineType;
    private String affordability;

    public Restaurant(Integer id, Integer cityId, String name, String image, String type, String description, List<Schedule> schedules, String location, float rating, int sponsored, String cuisineType, String affordability) {
        super(id, cityId, name, image, type, description, schedules, location, rating, sponsored);
        this.cuisineType = cuisineType;
        this.affordability = affordability;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getAffordability() {
        return affordability;
    }

    public void setAffordability(String affordability) {
        this.affordability = affordability;
    }
}
