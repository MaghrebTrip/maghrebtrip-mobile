package com.maghrebtrip.activities.plans;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.maghrebtrip.R;
import com.maghrebtrip.activities.MapsActivity;
import com.maghrebtrip.adapters.AttractionAdapter;
import com.maghrebtrip.models.Attraction;
import com.maghrebtrip.models.Plan;
import com.maghrebtrip.retrofit.PlanApi;
import com.maghrebtrip.retrofit.RetrofitService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondPlanFragment extends Fragment {
    private List<Attraction> attractions = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_plan2, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.plan2AttractionList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        RetrofitService retrofitService = new RetrofitService(8083);
        PlanApi planApi = retrofitService.getRetrofit().create(PlanApi.class);

        int cityId = 0;

        Bundle args = getArguments();
        if (args != null && args.containsKey("cityId")) {
            cityId = args.getInt("cityId");
        }

        planApi.getPlansByCity(cityId).enqueue(new Callback<List<Plan>>() {
            @Override
            public void onResponse(Call<List<Plan>> call, Response<List<Plan>> response) {
                List<Plan> plans = response.body();
                if (plans != null) {
                    attractions = plans.get(1).getAttractions();
                    AttractionAdapter adapter = new AttractionAdapter(attractions);
                    recyclerView.setAdapter(adapter);
                    Toast.makeText(getActivity(), "Plans loaded successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Empty data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Plan>> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed to load plans!", Toast.LENGTH_SHORT).show();
                Logger.getLogger(getActivity().getClass().getName()).log(Level.SEVERE, "Error occurred", t);
            }
        });

        rootView.findViewById(R.id.choosePlanBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                intent.putExtra("attractions", (Serializable) attractions);
                startActivity(intent);
            }
        });

        return rootView;
    }
}