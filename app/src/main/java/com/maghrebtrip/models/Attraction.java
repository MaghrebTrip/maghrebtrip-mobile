package com.maghrebtrip.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Attraction implements Serializable {

    private Integer id;
    private Integer cityId;
    private String name;
    private String image;
    private String type;
    private String description;
    private List<Schedule> schedules;
    private String location;
    private float rating;
    private int sponsored;

    public Attraction(Integer id, Integer cityId, String name, String image, String type, String description, List<Schedule> schedules, String location, float rating, int sponsored) {
        this.id = id;
        this.cityId = cityId;
        this.name = name;
        this.image = image;
        this.type = type;
        this.description = description;
        this.schedules = schedules;
        this.location = location;
        this.rating = rating;
        this.sponsored = sponsored;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getSponsored() {
        return sponsored;
    }

    public void setSponsored(int sponsored) {
        this.sponsored = sponsored;
    }
}
