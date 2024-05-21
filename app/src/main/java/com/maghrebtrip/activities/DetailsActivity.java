package com.maghrebtrip.activities;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
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
    private static final String TAG = "DetailsActivityTag";



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

        String imageData = object.getImage();
        byte[] decodedString = Base64.decode(imageData, Base64.DEFAULT);
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        binding.cityImageDetails.setImageBitmap(decodedBitmap);


        binding.cityNameDetails.setText(object.getName());
        binding.cityRatingDetails.setText(object.getRating()+"");
        binding.cityDescriptionDetails.setText(object.getAbout());

        binding.backBtn.setOnClickListener(v -> finish());

        binding.goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, SelectionActivity.class);
                intent.putExtra("cityId", object.getId().intValue());
                intent.putExtra("cityName", object.getName());
                DetailsActivity.this.startActivity(intent);
            }
        });
    }
}
