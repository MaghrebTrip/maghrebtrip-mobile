package com.maghrebtrip.activities.details;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.maghrebtrip.R;
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
        getBundles();
    }

    private void statusBarColor() {
        Window window = RestaurantDetailsActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(RestaurantDetailsActivity.this, R.color.cornel_red));
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

        binding.backBtn.setOnClickListener(v -> finish());
    }
}
