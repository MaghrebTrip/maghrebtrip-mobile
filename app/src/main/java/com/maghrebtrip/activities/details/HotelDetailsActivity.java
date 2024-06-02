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
import com.maghrebtrip.databinding.ActivityHotelDetailsBinding;
import com.maghrebtrip.models.Hotel;

public class HotelDetailsActivity extends AppCompatActivity {

    private ActivityHotelDetailsBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHotelDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        statusBarColor();
        bottomAppBar();
        getBundles();
    }

    private void statusBarColor() {
        Window window = HotelDetailsActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(HotelDetailsActivity.this, R.color.cornel_red));
    }

    private void bottomAppBar() {
        View bottomAppBarHomeBtn = findViewById(R.id.bottomAppBarHomeBtn);
        View bottomAppBarExplorerBtn = findViewById(R.id.bottomAppBarExplorerBtn);
        View bottomAppBarProfileBtn = findViewById(R.id.bottomAppBarProfileBtn);

        bottomAppBarHomeBtn.setOnClickListener(
                v -> {
                    Intent intent = new Intent(HotelDetailsActivity.this, MainActivity.class);
                    startActivity(intent);
                }
        );
        bottomAppBarExplorerBtn.setOnClickListener(
                v -> {
                    Intent intent = new Intent(HotelDetailsActivity.this, MainActivity.class);
                    startActivity(intent);
                }
        );
        bottomAppBarProfileBtn.setOnClickListener(
                v -> {
                    Intent intent = new Intent(HotelDetailsActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
        );
    }

    private void getBundles() {
        Hotel object = (Hotel) getIntent().getSerializableExtra("object");

        assert object != null;
        String imageData = object.getImage();
        byte[] decodedString = Base64.decode(imageData, Base64.DEFAULT);
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        binding.hotelImageDetails.setImageBitmap(decodedBitmap);


        binding.hotelNameDetails.setText(object.getName());
        binding.hotelRatingDetails.setText(String.format("%s", object.getRating()));
        binding.hotelDescriptionDetails.setText(object.getDescription());

        View backBtn = findViewById(R.id.appBarBookmarkBackBtn);
        backBtn.setOnClickListener(v -> finish());
    }
}
