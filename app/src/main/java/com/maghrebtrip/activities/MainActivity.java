package com.maghrebtrip.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Window;

import com.maghrebtrip.R;
import com.maghrebtrip.adapters.CityAdapter;
import com.maghrebtrip.models.Attraction;
import com.maghrebtrip.models.City;
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
        items.add(new City("Rabat","rabat", 4.5, "Rabat is the capital city of Morocco and the country's seventh-largest city with an urban population of approximately 580,000 (2014) and a metropolitan population of over 1.2 million. It is also the capital city of the Rabat-Salé-Kénitra administrative region. Rabat is located on the Atlantic Ocean at the mouth of the river Bou Regreg, opposite Salé, the city\\'s main commuter town."));
        items.add(new City("Casablanca","casablanca", 4.5, "lorem ipsum"));
        items.add(new City("Ifrane","ifrane", 4.5, "lorem ipsum"));
        items.add(new City("Marrakech","marrakech", 4.5, "lorem ipsum"));

        binding.popularCities.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.popularCities.setAdapter(new CityAdapter(items));

        ArrayList<Attraction> attractions = new ArrayList<>();
        ArrayList<String> openingHours = new ArrayList<>();
        openingHours.add("All days: 15h-17h");

        attractions.add(new Attraction("Hotel Farah", "Hotel", "lorem ipsum", openingHours, "PLACE 16 NOVEMBRE, Bd Mohamed Lyazidi, Rabat 10000"));
        attractions.add(new Attraction("Hassan Tower", "Historical landmark", "This famous red sandstone landmark is a remnant of a 12th-century minaret that was never finished.", openingHours, "Bd Mohamed Lyazidi, Rabat"));
        attractions.add(new Attraction("Espace Hassan", "Restaurant", "lorem ipsum", openingHours, "25F9+64X, Av. Chellah, Rabat"));
    }

}