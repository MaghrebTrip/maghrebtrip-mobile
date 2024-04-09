package com.maghrebtrip.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Window;

import com.maghrebtrip.R;
import com.maghrebtrip.adapters.PopularCityAdapter;
import com.maghrebtrip.cities.PopularCity;
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
        ArrayList<PopularCity> items = new ArrayList<>();
        items.add(new PopularCity("Rabat","rabat"));
        items.add(new PopularCity("Casablanca","casablanca"));
        items.add(new PopularCity("Ifrane","ifrane"));
        items.add(new PopularCity("Marrakech","marrakech"));

        binding.popularCities.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.popularCities.setAdapter(new PopularCityAdapter(items));
    }

}