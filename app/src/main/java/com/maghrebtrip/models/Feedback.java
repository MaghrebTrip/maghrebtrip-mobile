package com.maghrebtrip.models;


public class Feedback {
    private Integer id;
    private Integer touristId;
    private Integer attractionId;
    private String attractionType;
    private float rating;
    private String comment;
    private String date;
    private boolean edited;

    public Feedback(Integer id, Integer touristId, Integer attractionId, String attractionType, float rating, String comment, String date, boolean edited) {
        this.id = id;
        this.touristId = touristId;
        this.attractionId = attractionId;
        this.attractionType = attractionType;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
        this.edited = edited;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTouristId() {
        return touristId;
    }

    public void setTouristId(Integer touristId) {
        this.touristId = touristId;
    }

    public Integer getAttractionId() {
        return attractionId;
    }

    public void setAttractionId(Integer attractionId) {
        this.attractionId = attractionId;
    }

    public String getAttractionType() {
        return attractionType;
    }

    public void setAttractionType(String attractionType) {
        this.attractionType = attractionType;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }
}
