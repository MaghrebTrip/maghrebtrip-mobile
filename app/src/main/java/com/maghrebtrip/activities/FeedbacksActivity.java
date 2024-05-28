package com.maghrebtrip.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.maghrebtrip.R;
import com.maghrebtrip.adapters.FeedbackAdapter;
import com.maghrebtrip.databinding.ActivityFeedbacksBinding;
import com.maghrebtrip.models.Feedback;
import com.maghrebtrip.models.FeedbackRequest;
import com.maghrebtrip.models.FeedbackResponse;
import com.maghrebtrip.retrofit.FeedbackApi;
import com.maghrebtrip.retrofit.RetrofitService;

import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbacksActivity extends AppCompatActivity {

    ActivityFeedbacksBinding binding;

    int touristId;
    String touristFirstName, touristLastName;

    int attractionId;
    String attractionType;

    Properties properties = new Properties();
    int feedbackApiPort = Integer.parseInt(properties.getProperty("FEEDBACK_API_PORT", "8085"));
    List<FeedbackResponse> feedbacks;
    RetrofitService retrofitService = new RetrofitService(feedbackApiPort);
    FeedbackApi feedbackApi = retrofitService.getRetrofit().create(FeedbackApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeedbacksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getTourist();
        getAttraction();
        getFeedbacks();

        binding.feedbackSubmitBtn.setOnClickListener(v -> addFeedback());
    }

    private void getTourist() {
        // Get tourist ID, first name and last name
        SharedPreferences sharedPreferences = getSharedPreferences("appUser", MODE_PRIVATE);
        touristId = sharedPreferences.getInt("id", 0);
        touristFirstName = sharedPreferences.getString("firstName", "");
        touristLastName = sharedPreferences.getString("lastName", "");
    }

    private void getAttraction() {
        // Get attraction ID and type
        if (getIntent().hasExtra("attractionId") && getIntent().hasExtra("attractionType")) {
            attractionId = getIntent().getIntExtra("attractionId", 0);
            attractionType = getIntent().getStringExtra("attractionType");
        } else {
            Toast.makeText(FeedbacksActivity.this, "Attraction ID and type not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void getFeedbacks() {
        // Get feedbacks of the attraction
        if (attractionId!=0 && attractionType != null && !attractionType.equals("")) {
            feedbackApi.getFeedbacksOfAttraction(attractionType, attractionId).enqueue(
                new Callback<List<FeedbackResponse>>() {
                    @Override
                    public void onResponse(Call<List<FeedbackResponse>> call, Response<List<FeedbackResponse>> response) {
                        feedbacks = response.body();
                        if (feedbacks != null) {
                            RecyclerView recyclerView = binding.getRoot().findViewById(R.id.feedbacksRV);
                            recyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
                            recyclerView.setAdapter(new FeedbackAdapter(feedbacks));

                        } else {
                            Toast.makeText(FeedbacksActivity.this, "Feedbacks not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<FeedbackResponse>> call, Throwable t) {
                        Toast.makeText(FeedbacksActivity.this, "Failed to load feedbacks", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(FeedbacksActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                    }
                }
            );
        } else {
            Toast.makeText(FeedbacksActivity.this, "Attraction ID and type not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void addFeedback() {
        // Get feedback rating and comment from the layout
        float rating = binding.feedbackRatingBar.getRating();
        String comment = binding.feedbackComment.getText().toString();
        FeedbackRequest request = new FeedbackRequest(
                touristId,
                attractionId,
                attractionType,
                rating,
                comment
        );

        // Post a new feedback
        feedbackApi.addFeedback(request).enqueue(
                new Callback<Feedback>() {
                    @Override
                    public void onResponse(Call<Feedback> call, Response<Feedback> response) {
                        getFeedbacks();
                        Toast.makeText(FeedbacksActivity.this, "Feedback registered successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Feedback> call, Throwable t) {
                        Toast.makeText(FeedbacksActivity.this, "Operation failed!", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(FeedbacksActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                    }
                }
        );

        // TODO: add deleteFeedback and updateFeedback
    }
}