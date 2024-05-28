package com.maghrebtrip.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.maghrebtrip.R;
import com.maghrebtrip.activities.plans.PlansActivity;
import com.maghrebtrip.databinding.ActivityCityDetailsBinding;
import com.maghrebtrip.databinding.ActivityFeedbacksBinding;
import com.maghrebtrip.models.Feedback;

import java.util.List;

public class FeedbacksActivity extends AppCompatActivity {

    ActivityFeedbacksBinding binding;

    List<Feedback> feedbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeedbacksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void getBundles() {
        // TODO: get feedbacks of a certain attraction using getExtra() and retrofit
        SharedPreferences sharedPreferences = getSharedPreferences("appUser", MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("email", "");

        int attractionId;
        String attractionType;
        if (getIntent().hasExtra("attractionId") && getIntent().hasExtra("attractionType")) {
            attractionId = getIntent().getIntExtra("attractionId", 0);
            attractionType = getIntent().getStringExtra("attractionType");
            Toast.makeText(FeedbacksActivity.this, attractionType, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(FeedbacksActivity.this, "City ID not found", Toast.LENGTH_SHORT).show();
        }
    }
}