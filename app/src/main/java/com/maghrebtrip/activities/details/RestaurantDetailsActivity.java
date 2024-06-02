package com.maghrebtrip.activities.details;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.maghrebtrip.R;
import com.maghrebtrip.activities.ProfileActivity;
import com.maghrebtrip.activities.main.MainActivity;
import com.maghrebtrip.databinding.ActivityRestaurantDetailsBinding;
import com.maghrebtrip.models.Restaurant;

public class RestaurantDetailsActivity extends AppCompatActivity {

    private ActivityRestaurantDetailsBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestaurantDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        statusBarColor();
        bottomAppBar();
        getBundles();
    }

    private void statusBarColor() {
        Window window = RestaurantDetailsActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(RestaurantDetailsActivity.this, R.color.cornel_red));
    }

    private void bottomAppBar() {
        View bottomAppBarHomeBtn = findViewById(R.id.bottomAppBarHomeBtn);
        View bottomAppBarExplorerBtn = findViewById(R.id.bottomAppBarExplorerBtn);
        View bottomAppBarProfileBtn = findViewById(R.id.bottomAppBarProfileBtn);

        bottomAppBarHomeBtn.setOnClickListener(
                v -> {
                    Intent intent = new Intent(RestaurantDetailsActivity.this, MainActivity.class);
                    startActivity(intent);
                }
        );
        bottomAppBarExplorerBtn.setOnClickListener(
                v -> {
                    Intent intent = new Intent(RestaurantDetailsActivity.this, MainActivity.class);
                    startActivity(intent);
                }
        );
        bottomAppBarProfileBtn.setOnClickListener(
                v -> {
                    Intent intent = new Intent(RestaurantDetailsActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
        );
    }

    private void getBundles() {
        Restaurant object = (Restaurant) getIntent().getSerializableExtra("object");

        assert object != null;
        String imageData = object.getImage();
        byte[] decodedString = Base64.decode(imageData, Base64.DEFAULT);
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        binding.restaurantImageDetails.setImageBitmap(decodedBitmap);


        binding.restaurantNameDetails.setText(object.getName());
        binding.restaurantRatingDetails.setText(String.format("%s", object.getRating()));
        binding.restaurantDescriptionDetails.setText(object.getDescription());

        View backBtn = findViewById(R.id.appBarBookmarkBackBtn);
        backBtn.setOnClickListener(v -> finish());
    }
}
