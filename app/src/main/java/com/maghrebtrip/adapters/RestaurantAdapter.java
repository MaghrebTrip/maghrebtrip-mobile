package com.maghrebtrip.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maghrebtrip.databinding.ViewholderRestaurantsListBinding;
import com.maghrebtrip.models.Restaurant;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.Viewholder> {
    List<Restaurant> items;
    Context context;
    ViewholderRestaurantsListBinding binding;

    public RestaurantAdapter(List<Restaurant> items) {
        this.items = items.subList(0, 10);
    }

    @NonNull
    @Override
    public RestaurantAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ViewholderRestaurantsListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        context = parent.getContext();
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantAdapter.Viewholder holder, int position) {
        binding.restaurantName.setText(items.get(position).getName());

        String imageData = items.get(position).getImage();
        byte[] decodedString = Base64.decode(imageData, Base64.DEFAULT);
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        binding.restaurantImage.setImageBitmap(decodedBitmap);

        binding.restaurantRating.setText(items.get(position).getRating()+"");

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
        public Viewholder(@NonNull ViewholderRestaurantsListBinding binding) {
            super(binding.getRoot());
        }
    }
}
