package com.maghrebtrip.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maghrebtrip.activities.details.MonumentDetailsActivity;
import com.maghrebtrip.databinding.ViewholderMonumentsListBinding;
import com.maghrebtrip.models.Monument;

import java.util.List;

public class MonumentAdapter extends RecyclerView.Adapter<MonumentAdapter.Viewholder> {
    List<Monument> items;
    Context context;
    ViewholderMonumentsListBinding binding;

    public MonumentAdapter(List<Monument> items) {
        this.items = items.subList(0, 3); // just 3 items
    }

    @NonNull
    @Override
    public MonumentAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ViewholderMonumentsListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        context = parent.getContext();
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MonumentAdapter.Viewholder holder, int position) {
        binding.monumentName.setText(items.get(position).getName());

        String imageData = items.get(position).getImage();
        byte[] decodedString = Base64.decode(imageData, Base64.DEFAULT);
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        binding.monumentImage.setImageBitmap(decodedBitmap);

        binding.monumentRating.setText(String.format("%s", items.get(position).getRating()));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MonumentDetailsActivity.class);
            intent.putExtra("object", items.get(position));
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public Viewholder(@NonNull ViewholderMonumentsListBinding binding) {
            super(binding.getRoot());
        }
    }
}
