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
import com.maghrebtrip.adapters.HotelAdapter;
import com.maghrebtrip.models.Hotel;
import com.maghrebtrip.retrofit.AttractionApi;
import com.maghrebtrip.retrofit.RetrofitService;

import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExploreHotelsFragment extends Fragment {

    Properties properties = new Properties();
    int attractionApiPort = Integer.parseInt(properties.getProperty("ATTRACTION_API_PORT", "8084"));


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_explore_hotels, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.popularHotels);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        initHotelsRecyclerView(recyclerView);

        return rootView;
    }

    private void initHotelsRecyclerView(RecyclerView recyclerView) {
        RetrofitService retrofitService = new RetrofitService(attractionApiPort);
        AttractionApi attractionApi = retrofitService.getRetrofit().create(AttractionApi.class);

        attractionApi.getAllHotels().enqueue(
                new Callback<List<Hotel>>() {
                    @Override
                    public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                        List<Hotel> hotels = response.body();
                        if (hotels != null) {
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                            recyclerView.setAdapter(new HotelAdapter(hotels));
                        } else {
                            Toast.makeText(getActivity(), "Empty data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Hotel>> call, Throwable t) {
                        Toast.makeText(getActivity(), "Failed to load hotels!", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                    }
                }
        );
    }
}