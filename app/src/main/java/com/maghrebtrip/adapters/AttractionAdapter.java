package com.maghrebtrip.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.maghrebtrip.activities.DetailsActivity;
import com.maghrebtrip.activities.PlanFragment1;
import com.maghrebtrip.databinding.ViewholderAttractionListBinding;
import com.maghrebtrip.models.Attraction;

import java.util.ArrayList;

public class AttractionAdapter extends RecyclerView.Adapter<AttractionAdapter.Viewholder> {
    ArrayList<Attraction> items;
    Context context;
    ViewholderAttractionListBinding binding;

    public AttractionAdapter(ArrayList<Attraction> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public AttractionAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ViewholderAttractionListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        context = parent.getContext();
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AttractionAdapter.Viewholder holder, int position) {
        binding.attractionName.setText(items.get(position).getName());

        int drawableResourceId = holder.itemView.getResources().getIdentifier(
                items.get(position).getImage(),
                "drawable",
                holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableResourceId)
                .transform(new GranularRoundedCorners(30, 30, 0, 0))
                .into(binding.attractionImage);

        binding.attractionType.setText(items.get(position).getType());
        binding.attractionOpeningHours.setText(items.get(position).getOpeningHours().get(0)); // TODO: change this code

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public Viewholder(@NonNull ViewholderAttractionListBinding binding) {
            super(binding.getRoot());
        }
    }
}
