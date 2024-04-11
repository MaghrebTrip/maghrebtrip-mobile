package com.maghrebtrip.activities;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.maghrebtrip.R;
import com.maghrebtrip.databinding.ActivityMapsBinding;

import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        statusBarColor();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void statusBarColor() {
        Window window = MapsActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(MapsActivity.this, R.color.cornel_red));
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        final LatLng ATTRACTION1 = new LatLng(34.0258769,-6.8260299);
        final LatLng ATTRACTION2 = new LatLng(34.0242107,-6.8231208);
        final LatLng ATTRACTION3 = new LatLng(34.0222944,-6.8301424);

        Marker markerAttraction1;
        Marker markerAttraction2;
        Marker markerAttraction3;

        markerAttraction1 = mMap.addMarker(new MarkerOptions()
                .position(ATTRACTION1)
                .title("Attraction 1"));
        markerAttraction1.setTag(0);

        markerAttraction2 = mMap.addMarker(new MarkerOptions()
                .position(ATTRACTION2)
                .title("Attraction 2"));
        markerAttraction2.setTag(0);

        markerAttraction3 = mMap.addMarker(new MarkerOptions()
                .position(ATTRACTION3)
                .title("Attraction 3"));
        markerAttraction3.setTag(0);

        PolylineOptions polylineOptions = new PolylineOptions()
                .add(ATTRACTION1)
                .add(ATTRACTION2)
                .add(ATTRACTION3)
                .color(Color.RED); // Set color of the polyline

        mMap.addPolyline(polylineOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(ATTRACTION1));

        final String placeId = "ChIJ8QoUlJFrpw0RXGFGkplsNhM";

        // Specify the fields to return.
        final List<Place.Field> placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME);

        final FetchPlaceRequest request = FetchPlaceRequest.newInstance(placeId, placeFields);

        PlacesClient placesClient = Places.createClient(getBaseContext());

    }
}