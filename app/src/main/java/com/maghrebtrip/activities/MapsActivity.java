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
import com.maghrebtrip.models.Attraction;
import com.maghrebtrip.utils.LocationParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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

//    private void getRoutes() throws IOException {
//        String originLat = "34.0258769";
//        String originLng = "-6.8260299";
//        String destLat = "34.0222944";
//        String destLng = "-6.8301424";
//        String API_KEY = "";
//
//        String url = "https://maps.googleapis.com/maps/api/directions/json?"
//                + "origin=" + originLat + "," + originLng
//                + "&destination=" + destLat + "," + destLng
//                + "&key=" + API_KEY;
//
//        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//        InputStream inputStream = connection.getInputStream();
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//        StringBuilder response = new StringBuilder();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            response.append(line);
//        }
//        reader.close();
//    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        List<Attraction> attractions = new ArrayList<>();
        if (getIntent().hasExtra("attractions")) {
            attractions = (List<Attraction>) getIntent().getSerializableExtra("attractions");
        } else {
            Toast.makeText(MapsActivity.this, "Attractions not found", Toast.LENGTH_SHORT).show();
        }

        List<LatLng> coordinatesList = new ArrayList<>();
        for (Attraction attraction : attractions) {
            String location = attraction.getLocation();
            double[] coordinates = LocationParser.parseLocation(location);
            LatLng latLng = new LatLng(coordinates[0], coordinates[1]);
            Marker marker;
            marker = mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(attraction.getName()));
            marker.setTag(0);
            coordinatesList.add(latLng);
        }

        PolylineOptions polylineOptions = new PolylineOptions()
                .add(coordinatesList.get(0))
                .add(coordinatesList.get(1))
                .add(coordinatesList.get(2))
                .color(Color.RED);
        mMap.addPolyline(polylineOptions);

        // mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinatesList.get(0)));

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                coordinatesList.get(0), 16f);
        mMap.animateCamera(cameraUpdate);
    }
}