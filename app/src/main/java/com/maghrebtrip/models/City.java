package com.maghrebtrip.models;

import java.io.Serializable;

public class City implements Serializable {
    private String name;
    private String image;
    private double rating;
    private String description;

    public City(String name, String image, double rating, String description) {
        this.name = name;
        this.image = image;
        this.rating = rating;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
