package com.maghrebtrip.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.maghrebtrip.R;
import com.maghrebtrip.models.City;
import com.maghrebtrip.databinding.ActivityDetailsBinding;


public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;
    private City object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        statusBarColor();
        getBundles();
    }

    private void statusBarColor() {
        Window window = DetailsActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(DetailsActivity.this, R.color.cornel_red));
    }

    private void getBundles() {
        object = (City) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getImage(),
                "drawable", this.getPackageName());

        Glide.with(this)
                .load(drawableResourceId)
                .into(binding.cityImage);

        binding.cityName.setText(object.getName());
        binding.cityRating.setText(object.getRating()+"");
        binding.cityDescription.setText(object.getDescription());

        binding.backBtn.setOnClickListener(v -> finish());

        binding.goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, PlansActivity.class);
                DetailsActivity.this.startActivity(intent);
            }
        });
    }

}