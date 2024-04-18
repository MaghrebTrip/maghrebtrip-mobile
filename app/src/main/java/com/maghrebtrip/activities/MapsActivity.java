package com.maghrebtrip.activities;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.Window;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    private final static int LOCATION_REQUEST_CODE = 23;
    boolean locationPermission = false;

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

        binding.backBtn.setOnClickListener(v -> finish());
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

    private void getRoutes() throws IOException {
        String originLat = "34.0258769";
        String originLng = "-6.8260299";
        String destLat = "34.0222944";
        String destLng = "-6.8301424";
        String API_KEY = "";

        String url = "https://maps.googleapis.com/maps/api/directions/json?"
                + "origin=" + originLat + "," + originLng
                + "&destination=" + destLat + "," + destLng
                + "&key=" + API_KEY;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        InputStream inputStream = connection.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Location location1;
        Location location2;
        Location location3;

        final LatLng attraction1 = new LatLng(34.0258769,-6.8260299);
        final LatLng attraction2 = new LatLng(34.0242107,-6.8231208);
        final LatLng attraction3 = new LatLng(34.0222944,-6.8301424);

        Marker markerAttraction1;
        Marker markerAttraction2;
        Marker markerAttraction3;

        markerAttraction1 = mMap.addMarker(new MarkerOptions()
                .position(attraction1)
                .title("Attraction 1"));
        markerAttraction1.setTag(0);

        markerAttraction2 = mMap.addMarker(new MarkerOptions()
                .position(attraction2)
                .title("Attraction 2"));
        markerAttraction2.setTag(0);

        markerAttraction3 = mMap.addMarker(new MarkerOptions()
                .position(attraction3)
                .title("Attraction 3"));
        markerAttraction3.setTag(0);

        PolylineOptions polylineOptions = new PolylineOptions()
                .add(attraction1)
                .add(attraction2)
                .add(attraction3)
                .color(Color.RED); // Set color of the polyline

        mMap.addPolyline(polylineOptions);
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(attraction1));

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                attraction1, 16f);
        mMap.animateCamera(cameraUpdate);

//        try {
//            this.getRoutes();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        // final String placeId = "ChIJ8QoUlJFrpw0RXGFGkplsNhM";

    }
}