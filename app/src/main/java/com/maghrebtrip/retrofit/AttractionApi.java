package com.maghrebtrip.retrofit;

import com.maghrebtrip.models.Hotel;
import com.maghrebtrip.models.Monument;
import com.maghrebtrip.models.Restaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AttractionApi {
    @GET("/api/v1/attractions/hotels/all")
    Call<List<Hotel>> getAllHotels();

    @GET("/api/v1/attractions/restaurants/all")
    Call<List<Restaurant>> getAllRestaurants();

    @GET("/api/v1/attractions/monuments/all")
    Call<List<Monument>> getAllMonuments();
}
