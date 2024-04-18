package com.maghrebtrip.models;

import java.util.ArrayList;

public class Attraction {

    private  String image;
    private String name;
    private String type;
    private String description;
    private ArrayList<String> openingHours;
    private String location;

    public Attraction(String image, String name, String type, String description, ArrayList<String> openingHours, String location) {
        this.image = image;
        this.name = name;
        this.type = type;
        this.description = description;
        this.openingHours = openingHours;
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ArrayList<String> getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(ArrayList<String> openingHours) {
        this.openingHours = openingHours;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
