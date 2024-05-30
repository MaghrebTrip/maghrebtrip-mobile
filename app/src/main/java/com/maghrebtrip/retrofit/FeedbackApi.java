package com.maghrebtrip.retrofit;

import com.maghrebtrip.models.Feedback;
import com.maghrebtrip.models.FeedbackRequest;
import com.maghrebtrip.models.FeedbackResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FeedbackApi {

    @POST("/api/v1/feedbacks/new")
    Call<Feedback> addFeedback(@Body FeedbackRequest request);

    @GET("/api/v1/feedbacks/attractionType={attractionType}&attractionId={attractionId}")
    Call<List<FeedbackResponse>> getFeedbacksOfAttraction(
            @Path("attractionType") String attractionType,
            @Path("attractionId") Integer attractionId
    );

    @PUT("/api/v1/feedbacks/update/id={id}")
    Call<Feedback> updateFeedback(@Path("id") Integer id, @Body FeedbackRequest request);

    @DELETE("/api/v1/feedbacks/delete/id={id}")
    Call<Map<String, String>> deleteFeedback(@Path("id") Integer id);
}
