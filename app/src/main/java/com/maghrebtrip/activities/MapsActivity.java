package com.maghrebtrip.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.maghrebtrip.R;
import com.maghrebtrip.databinding.ActivityMapsBinding;
import com.maghrebtrip.models.CustomAttraction;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    private final static int LOCATION_REQUEST_CODE = 23;
    boolean locationPermission = false;

    class MapsInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final View mWindow;

        MapsInfoWindowAdapter() {
            mWindow = getLayoutInflater().inflate(R.layout.maps_info_window, null);
        }

        @Nullable
        @Override
        public View getInfoContents(@NonNull Marker marker) {
            CustomAttraction attraction = (CustomAttraction) marker.getTag();
            if (attraction != null) {
                TextView attractionName = mWindow.findViewById(R.id.attractionName);
                attractionName.setText(attraction.getName());
                TextView attractionType = mWindow.findViewById(R.id.attractionType);
                attractionType.setText(attraction.getType());
                TextView attractionRating = mWindow.findViewById(R.id.attractionRating);
                attractionRating.setText(String.format("%s", attraction.getRating()));
                TextView attractionOpeningHours = mWindow.findViewById(R.id.attractionOpeningHours);
                attractionOpeningHours.setText(String.format("%s - %s", "09:00", "16:00"));
            }
            return mWindow;
        }

        @Nullable
        @Override
        public View getInfoWindow(@NonNull Marker marker) {
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        statusBarColor();

        requestPermision();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        View backBtn = findViewById(R.id.appBarOptionsBackBtn);
        backBtn.setOnClickListener(v -> finish());
    }

    private void statusBarColor() {
        Window window = MapsActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(MapsActivity.this, R.color.cornel_red));
    }

    private void requestPermision() {
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_REQUEST_CODE);
        }
        else{
            locationPermission=true;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        List<CustomAttraction> attractions = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            if (getIntent().hasExtra("attraction_"+i)) {
                attractions.add((CustomAttraction) getIntent().getSerializableExtra("attraction_"+i));
            } else {
                Toast.makeText(MapsActivity.this, "Attraction " + i + " not found", Toast.LENGTH_SHORT).show();
            }
        }

        List<LatLng> coordinatesList = new ArrayList<>();
        for (CustomAttraction attraction : attractions) {
            String location = attraction.getLocation();
            String[] coordinates = location.split(", ");
            double latitude = Double.parseDouble(coordinates[0]);
            double longitude = Double.parseDouble(coordinates[1]);
            LatLng latLng = new LatLng(latitude, longitude);
            Marker marker;
            marker = mMap.addMarker(new MarkerOptions().position(latLng));
            assert marker != null;
            marker.setTag(attraction);
            coordinatesList.add(latLng);
        }

        PolylineOptions polylineOptions = new PolylineOptions()
                .add(coordinatesList.get(0))
                .add(coordinatesList.get(1))
                .add(coordinatesList.get(2))
                .color(Color.RED);
        mMap.addPolyline(polylineOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                coordinatesList.get(0), 16f);
        mMap.animateCamera(cameraUpdate);

        mMap.setInfoWindowAdapter(new MapsInfoWindowAdapter());

        mMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent intent = new Intent(MapsActivity.this, FeedbacksActivity.class);
        CustomAttraction attraction = (CustomAttraction) marker.getTag();
        Integer attractionId = attraction.getId();
        String attractionType = attraction.getType();
        intent.putExtra("attractionId", attractionId);
        intent.putExtra("attractionType", attractionType);
        startActivity(intent);
    }
}