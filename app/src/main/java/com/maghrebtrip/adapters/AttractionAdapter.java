package com.maghrebtrip.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maghrebtrip.databinding.ViewholderAttractionListBinding;
import com.maghrebtrip.models.Attraction;
import com.maghrebtrip.models.Schedule;

import java.util.List;

public class AttractionAdapter extends RecyclerView.Adapter<AttractionAdapter.Viewholder> {
    List<Attraction> items;
    Context context;
    ViewholderAttractionListBinding binding;

    public AttractionAdapter(List<Attraction> items) {
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

        String imageData = items.get(position).getImage();
        byte[] decodedString = Base64.decode(imageData, Base64.DEFAULT);
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        binding.attractionImage.setImageBitmap(decodedBitmap);

        binding.attractionType.setText(items.get(position).getType());
        List<Schedule> schedules = items.get(position).getSchedules();
        binding.attractionOpeningHours.setText(
                //String.format("%s: %s -> %s", schedules.get(0).getDayOfWeek(), schedules.get(0).getStartTime(), schedules.get(0).getEndTime())
                String.format("%s: %s -> %s", "Monday", "09h00", "16h00")
        );

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
