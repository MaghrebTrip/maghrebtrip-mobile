package com.maghrebtrip.retrofit;

import com.maghrebtrip.models.Plan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlanApi {
    @GET("/api/v1/plans/all")
    Call<List<Plan>> getAllPlans();

    @GET("/api/v1/plans/{cityId}")
    Call<List<Plan>> getPlansByCity(@Path("cityId") Integer cityId);
}
