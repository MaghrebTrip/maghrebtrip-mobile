package com.maghrebtrip.retrofit;

import com.maghrebtrip.models.City;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CityApi {
    @GET("/api/v1/cities/all")
    Call<List<City>> getAllCities();
}
