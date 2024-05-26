package com.maghrebtrip.activities.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.maghrebtrip.R;
import com.maghrebtrip.adapters.CityAdapter;
import com.maghrebtrip.models.City;
import com.maghrebtrip.retrofit.CityApi;
import com.maghrebtrip.retrofit.RetrofitService;

import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExploreCitiesFragment extends Fragment {

    Properties properties = new Properties();
    int cityApiPort = Integer.parseInt(properties.getProperty("CITY_API_PORT", "8082"));

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_explore_cities, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.popularCities);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        initCitiesRecyclerView(recyclerView);

        return rootView;
    }

    private void initCitiesRecyclerView(RecyclerView recyclerView) {
        RetrofitService retrofitService = new RetrofitService(cityApiPort);
        CityApi cityApi = retrofitService.getRetrofit().create(CityApi.class);

        cityApi.getAllCities().enqueue(
                new Callback<List<City>>() {
                    @Override
                    public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                        List<City> cities = response.body();
                        if (cities != null) {
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                            recyclerView.setAdapter(new CityAdapter(cities));
                        } else {
                            Toast.makeText(getActivity(), "Empty data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<City>> call, Throwable t) {
                        Toast.makeText(getActivity(), "Failed to load cities!", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                    }
                }
        );
    }
}