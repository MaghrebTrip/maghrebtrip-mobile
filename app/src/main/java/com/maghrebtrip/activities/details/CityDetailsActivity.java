package com.maghrebtrip.activities.details;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.maghrebtrip.R;
import com.maghrebtrip.activities.ProfileActivity;
import com.maghrebtrip.activities.SelectionActivity;
import com.maghrebtrip.activities.main.MainActivity;
import com.maghrebtrip.databinding.ActivityCityDetailsBinding;
import com.maghrebtrip.models.City;

public class CityDetailsActivity extends AppCompatActivity {

    private ActivityCityDetailsBinding binding;
    private City object;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        statusBarColor();
        bottomAppBar();
        getBundles();
    }

    private void statusBarColor() {
        Window window = CityDetailsActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(CityDetailsActivity.this, R.color.cornel_red));
    }

    private void bottomAppBar() {
        View bottomAppBarHomeBtn = findViewById(R.id.bottomAppBarHomeBtn);
        View bottomAppBarExplorerBtn = findViewById(R.id.bottomAppBarExplorerBtn);
        View bottomAppBarProfileBtn = findViewById(R.id.bottomAppBarProfileBtn);

        bottomAppBarHomeBtn.setOnClickListener(
                v -> {
                    Intent intent = new Intent(CityDetailsActivity.this, MainActivity.class);
                    startActivity(intent);
                }
        );
        bottomAppBarExplorerBtn.setOnClickListener(
                v -> {
                    Intent intent = new Intent(CityDetailsActivity.this, MainActivity.class);
                    startActivity(intent);
                }
        );
        bottomAppBarProfileBtn.setOnClickListener(
                v -> {
                    Intent intent = new Intent(CityDetailsActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
        );
    }

    private void getBundles() {
        object = (City) getIntent().getSerializableExtra("object");

        assert object != null;
        String imageData = object.getImage();
        byte[] decodedString = Base64.decode(imageData, Base64.DEFAULT);
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        binding.cityImageDetails.setImageBitmap(decodedBitmap);


        binding.cityNameDetails.setText(object.getName());
        binding.cityRatingDetails.setText(String.format("%s", object.getRating()));
        binding.cityDescriptionDetails.setText(object.getAbout());

        View backBtn = findViewById(R.id.appBarBookmarkBackBtn);
        backBtn.setOnClickListener(v -> finish());

        binding.goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CityDetailsActivity.this, SelectionActivity.class);
                intent.putExtra("cityId", object.getId().intValue());
                intent.putExtra("cityName", object.getName());
                CityDetailsActivity.this.startActivity(intent);
            }
        });
    }
}
