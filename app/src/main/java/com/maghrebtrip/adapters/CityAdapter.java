package com.maghrebtrip.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.maghrebtrip.activities.DetailsActivity;
import com.maghrebtrip.models.City;
import com.maghrebtrip.databinding.ViewholderCitiesListBinding;

import android.util.Base64;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.Viewholder> {
    List<City> items;
    Context context;
    ViewholderCitiesListBinding binding;

    public CityAdapter(List<City> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CityAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ViewholderCitiesListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        context = parent.getContext();
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.Viewholder holder, int position) {
        binding.cityName.setText(items.get(position).getName());

        String imageData = items.get(position).getImage();
        byte[] decodedString = Base64.decode(imageData, Base64.DEFAULT);
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        binding.cityImage.setImageBitmap(decodedBitmap);

        binding.cityRating.setText(items.get(position).getRating()+"");

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("object", items.get(position));
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public Viewholder(@NonNull ViewholderCitiesListBinding binding) {
            super(binding.getRoot());
        }
    }
}
