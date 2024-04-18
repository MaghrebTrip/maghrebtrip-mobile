package com.maghrebtrip.models;

import java.io.Serializable;

public class City implements Serializable {
    private Integer id;
    private String name;
    private String image;
    private String about;
    private float rating;

    public City(Integer id, String name, String image, String about, float rating) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.about = about;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
