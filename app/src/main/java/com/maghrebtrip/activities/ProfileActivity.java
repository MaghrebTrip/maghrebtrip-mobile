package com.maghrebtrip.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.maghrebtrip.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getTouristInfo();
    }

    private void getTouristInfo() {
        SharedPreferences sharedPreferences = getSharedPreferences("appUser", MODE_PRIVATE);
        binding.touristFirstName.setText(sharedPreferences.getString("firstName", ""));
        binding.touristLastName.setText(sharedPreferences.getString("lastName", ""));
        binding.touristEmail.setText(sharedPreferences.getString("email", ""));
        binding.touristNationality.setText(sharedPreferences.getString("nationality", ""));
    }
}