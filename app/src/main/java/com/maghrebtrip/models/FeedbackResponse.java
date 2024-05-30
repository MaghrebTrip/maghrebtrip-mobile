package com.maghrebtrip.models;


import java.time.LocalDateTime;

public class FeedbackResponse {
    private Integer id;
    private String touristFirstName;
    private String touristLastName;
    private float rating;
    private String comment;
    private String date;

    public FeedbackResponse(Integer id, String touristFirstName, String touristLastName, float rating, String comment, String date) {
        this.id = id;
        this.touristFirstName = touristFirstName;
        this.touristLastName = touristLastName;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public String getTouristFirstName() {
        return touristFirstName;
    }

    public String getTouristLastName() {
        return touristLastName;
    }

    public float getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public String getDate() {
        return date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTouristFirstName(String touristFirstName) {
        this.touristFirstName = touristFirstName;
    }

    public void setTouristLastName(String touristLastName) {
        this.touristLastName = touristLastName;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
