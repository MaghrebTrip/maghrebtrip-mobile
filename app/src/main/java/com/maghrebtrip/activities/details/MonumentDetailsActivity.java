package com.maghrebtrip.activities.details;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.maghrebtrip.R;
import com.maghrebtrip.databinding.ActivityMonumentDetailsBinding;
import com.maghrebtrip.models.Monument;

public class MonumentDetailsActivity extends AppCompatActivity {

    private ActivityMonumentDetailsBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMonumentDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        statusBarColor();
        getBundles();
    }

    private void statusBarColor() {
        Window window = MonumentDetailsActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(MonumentDetailsActivity.this, R.color.cornel_red));
    }

    private void getBundles() {
        Monument object = (Monument) getIntent().getSerializableExtra("object");

        assert object != null;
        String imageData = object.getImage();
        byte[] decodedString = Base64.decode(imageData, Base64.DEFAULT);
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        binding.monumentImageDetails.setImageBitmap(decodedBitmap);


        binding.monumentNameDetails.setText(object.getName());
        binding.monumentRatingDetails.setText(String.format("%s", object.getRating()));
        binding.monumentDescriptionDetails.setText(object.getDescription());

        binding.backBtn.setOnClickListener(v -> finish());
    }
}
