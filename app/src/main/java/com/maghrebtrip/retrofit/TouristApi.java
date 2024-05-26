package com.maghrebtrip.retrofit;

import com.maghrebtrip.models.City;
import com.maghrebtrip.models.Tourist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TouristApi {
    @GET("/api/v1/tourists/tourist/id={id}")
    Call<Tourist> getTouristById(@Path("id") Integer id);

    @GET("/api/v1/tourists/tourist/email={email}")
    Call<Tourist> getTouristByEmail(@Path("email") String email);
}
