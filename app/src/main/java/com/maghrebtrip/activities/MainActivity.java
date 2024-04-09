package com.maghrebtrip.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Window;

import com.maghrebtrip.R;
import com.maghrebtrip.adapters.CityAdapter;
import com.maghrebtrip.cities.City;
import com.maghrebtrip.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        statusBarColor();
        initRecyclerView();
    }

    private void statusBarColor() {
        Window window = MainActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.cornel_red));
    }

    private void initRecyclerView() {
        ArrayList<City> items = new ArrayList<>();
        items.add(new City("Rabat","rabat", 4.5));
        items.add(new City("Casablanca","casablanca", 4.5));
        items.add(new City("Ifrane","ifrane", 4.5));
        items.add(new City("Marrakech","marrakech", 4.5));

        binding.popularCities.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.popularCities.setAdapter(new CityAdapter(items));
    }

}