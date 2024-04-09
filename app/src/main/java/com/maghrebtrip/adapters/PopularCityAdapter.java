package com.maghrebtrip.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.maghrebtrip.cities.PopularCity;
import com.maghrebtrip.databinding.ViewholderPupListBinding;

import java.util.ArrayList;

public class PopularCityAdapter extends RecyclerView.Adapter<PopularCityAdapter.Viewholder> {
    ArrayList<PopularCity> items;
    Context context;
    ViewholderPupListBinding binding;

    public PopularCityAdapter(ArrayList<PopularCity> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public PopularCityAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ViewholderPupListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        context = parent.getContext();
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularCityAdapter.Viewholder holder, int position) {
        binding.cityName.setText(items.get(position).getCityName());

        int drawableResource = holder.itemView.getResources().getIdentifier(
                items.get(position).getCityImage(),
                "drawable",
                holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableResource)
                .transform(new GranularRoundedCorners(30, 30, 0, 0))
                .into(binding.cityImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public Viewholder(@NonNull ViewholderPupListBinding binding) {
            super(binding.getRoot());
        }
    }
}
