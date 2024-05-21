package com.maghrebtrip.models;

import java.io.Serializable;
import java.util.List;

public class Hotel extends Attraction {

    private String[] amenities;
    private String roomTypes;

    public Hotel(Integer id, Integer cityId, String name, String image, String type, String description, List<Schedule> schedules, String location, float rating, int sponsored, String[] amenities, String roomTypes) {
        super(id, cityId, name, image, type, description, schedules, location, rating, sponsored);
        this.amenities = amenities;
        this.roomTypes = roomTypes;
    }

    public String[] getAmenities() {
        return amenities;
    }

    public void setAmenities(String[] amenities) {
        this.amenities = amenities;
    }

    public String getRoomTypes() {
        return roomTypes;
    }

    public void setRoomTypes(String roomTypes) {
        this.roomTypes = roomTypes;
    }
}
