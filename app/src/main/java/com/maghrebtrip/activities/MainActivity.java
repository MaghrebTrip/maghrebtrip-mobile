package com.maghrebtrip.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.maghrebtrip.R;
import com.maghrebtrip.adapters.CityAdapter;
import com.maghrebtrip.models.Attraction;
import com.maghrebtrip.models.City;
import com.maghrebtrip.databinding.ActivityMainBinding;
import com.maghrebtrip.retrofit.CityApi;
import com.maghrebtrip.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        RetrofitService retrofitService = new RetrofitService(8082);
        CityApi cityApi = retrofitService.getRetrofit().create(CityApi.class);

        cityApi.getAllCities().enqueue(
            new Callback<List<City>>() {
                @Override
                public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                    binding.popularCities.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    binding.popularCities.setAdapter(new CityAdapter(response.body()));
                    Toast.makeText(MainActivity.this, "Cities loaded successfully!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<List<City>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Failed to load cities!", Toast.LENGTH_SHORT).show();
                    Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                }
            }
        );
    }

}