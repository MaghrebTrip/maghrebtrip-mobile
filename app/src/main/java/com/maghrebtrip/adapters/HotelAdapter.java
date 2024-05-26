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

import com.maghrebtrip.activities.DetailsActivity;
import com.maghrebtrip.databinding.ViewholderHotelsListBinding;
import com.maghrebtrip.models.Hotel;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.Viewholder> {
    List<Hotel> items;
    Context context;
    ViewholderHotelsListBinding binding;

    public HotelAdapter(List<Hotel> items) {
        this.items = items.subList(0, 10);
    }

    @NonNull
    @Override
    public HotelAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ViewholderHotelsListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        context = parent.getContext();
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelAdapter.Viewholder holder, int position) {
        binding.hotelName.setText(items.get(position).getName());

        String imageData = items.get(position).getImage();
        byte[] decodedString = Base64.decode(imageData, Base64.DEFAULT);
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        binding.hotelImage.setImageBitmap(decodedBitmap);

        binding.hotelRating.setText(items.get(position).getRating()+"");

//        holder.itemView.setOnClickListener(v -> {
//            Intent intent = new Intent(context, DetailsActivity.class);
//            intent.putExtra("object", items.get(position));
//            context.startActivity(intent);
//        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public Viewholder(@NonNull ViewholderHotelsListBinding binding) {
            super(binding.getRoot());
        }
    }
}
