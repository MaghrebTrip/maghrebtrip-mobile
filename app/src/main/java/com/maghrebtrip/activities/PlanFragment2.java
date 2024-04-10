package com.maghrebtrip.activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maghrebtrip.R;
import com.maghrebtrip.adapters.AttractionAdapter;
import com.maghrebtrip.models.Attraction;

import java.util.ArrayList;

public class PlanFragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_plan2, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.plan2AttractionList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // TODO: change this fake data
        ArrayList<Attraction> attractions = new ArrayList<>();
        ArrayList<String> openingHours = new ArrayList<>();
        openingHours.add("All days: 15h-17h");
        attractions.add(new Attraction("hotel_farah_rabat", "Hotel Farah Rabat", "Hotel", "Set in a Moorish-inspired whitewashed building, this premium hotel with views of the Bou Regreg River is a 5-minute walk from the 12th-century Hassan Tower.", openingHours, "PLACE 16 NOVEMBRE, Bd Mohamed Lyazidi, Rabat 10000"));
        attractions.add(new Attraction("rabat", "Hassan Tower", "Historical landmark", "This famous red sandstone landmark is a remnant of a 12th-century minaret that was never finished.", openingHours, "Bd Mohamed Lyazidi, Rabat"));
        attractions.add(new Attraction("coq_magic", "Coq Magic", "Restaurant", "lorem ipsum", openingHours, "Angle Rue Mekka et Rue El Mourabitine, Rabat 10020"));

        AttractionAdapter adapter = new AttractionAdapter(attractions);

        recyclerView.setAdapter(adapter);

        rootView.findViewById(R.id.choosePlanBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: show map activity
            }
        });

        return rootView;
    }
}