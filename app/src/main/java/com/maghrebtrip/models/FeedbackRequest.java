package com.maghrebtrip.models;


public class FeedbackRequest {
    private Integer touristId;
    private Integer attractionId;
    private String attractionType;
    private float rating;
    private String comment;

    public FeedbackRequest(Integer touristId, Integer attractionId, String attractionType, float rating, String comment) {
        this.touristId = touristId;
        this.attractionId = attractionId;
        this.attractionType = attractionType;
        this.rating = rating;
        this.comment = comment;
    }

    public Integer getTouristId() {
        return touristId;
    }

    public Integer getAttractionId() {
        return attractionId;
    }

    public String getAttractionType() {
        return attractionType;
    }

    public float getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}
