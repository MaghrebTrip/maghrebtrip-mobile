package com.maghrebtrip.models;

import java.util.List;

public class Plan {
    private String city;
    private List<Attraction> attractions;

    public Plan(String city, List<Attraction> attractions) {
        this.city = city;
        this.attractions = attractions;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }
}
