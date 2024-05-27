package com.maghrebtrip.activities.details;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.maghrebtrip.R;
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
        getBundles();
    }

    private void statusBarColor() {
        Window window = HotelDetailsActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(HotelDetailsActivity.this, R.color.cornel_red));
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

        binding.backBtn.setOnClickListener(v -> finish());
    }
}
