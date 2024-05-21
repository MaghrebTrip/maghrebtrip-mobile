package com.maghrebtrip.models;

import java.util.List;

public class Monument extends Attraction {
    public Monument(Integer id, Integer cityId, String name, String image, String type, String description, List<Schedule> schedules, String location, float rating, int sponsored) {
        super(id, cityId, name, image, type, description, schedules, location, rating, sponsored);
    }
}
