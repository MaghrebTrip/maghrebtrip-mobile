package com.maghrebtrip.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.maghrebtrip.R;
import com.maghrebtrip.activities.main.MainActivity;
import com.maghrebtrip.activities.plans.PlansActivity;
import com.maghrebtrip.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        statusBarColor();
        bottomAppBar();

        getTouristInfo();

        View backBtn = findViewById(R.id.appBarOptionsBackBtn);
        backBtn.setOnClickListener(v -> finish());
    }

    private void statusBarColor() {
        Window window = ProfileActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(ProfileActivity.this, R.color.cornel_red));
    }

    private void bottomAppBar() {
        View bottomAppBarHomeBtn = findViewById(R.id.bottomAppBarHomeBtn);
        View bottomAppBarExplorerBtn = findViewById(R.id.bottomAppBarExplorerBtn);
        View bottomAppBarProfileBtn = findViewById(R.id.bottomAppBarProfileBtn);

        bottomAppBarHomeBtn.setOnClickListener(
                v -> {
                    Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                    startActivity(intent);
                }
        );
        bottomAppBarExplorerBtn.setOnClickListener(
                v -> {
                    Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                    startActivity(intent);
                }
        );
        bottomAppBarProfileBtn.setOnClickListener(
                v -> {
                    Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
        );
    }

    private void getTouristInfo() {
        SharedPreferences sharedPreferences = getSharedPreferences("appUser", MODE_PRIVATE);
        binding.touristFirstName.setText(sharedPreferences.getString("firstName", ""));
        binding.touristLastName.setText(sharedPreferences.getString("lastName", ""));
        binding.touristEmail.setText(sharedPreferences.getString("email", ""));
        binding.touristNationality.setText(sharedPreferences.getString("nationality", ""));
    }
}