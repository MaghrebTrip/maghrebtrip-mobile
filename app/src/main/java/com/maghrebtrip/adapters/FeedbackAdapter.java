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


import com.maghrebtrip.activities.FeedbacksActivity;
import com.maghrebtrip.databinding.ViewholderFeedbacksListBinding;
import com.maghrebtrip.models.FeedbackResponse;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.Viewholder> {
    List<FeedbackResponse> items;
    Context context;
    ViewholderFeedbacksListBinding binding;

    public FeedbackAdapter(List<FeedbackResponse> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public FeedbackAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ViewholderFeedbacksListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        context = parent.getContext();
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackAdapter.Viewholder holder, int position) {
        binding.feedbackTouristName.setText(String.format("%s %s", items.get(position).getTouristFirstName(), items.get(position).getTouristLastName()));
        binding.feedbackComment.setText(String.format("%s", items.get(position).getComment()));
        binding.feedbackRating.setText(String.format("%s", items.get(position).getRating()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public Viewholder(@NonNull ViewholderFeedbacksListBinding binding) {
            super(binding.getRoot());
        }
    }
}
