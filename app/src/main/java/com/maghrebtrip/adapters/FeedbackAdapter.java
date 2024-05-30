package com.maghrebtrip.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.maghrebtrip.R;
import com.maghrebtrip.activities.FeedbacksActivity;
import com.maghrebtrip.databinding.ViewholderFeedbacksListBinding;
import com.maghrebtrip.models.Feedback;
import com.maghrebtrip.models.FeedbackRequest;
import com.maghrebtrip.models.FeedbackResponse;
import com.maghrebtrip.retrofit.FeedbackApi;
import com.maghrebtrip.retrofit.RetrofitService;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.Viewholder> {

    Properties properties = new Properties();
    int feedbackApiPort = Integer.parseInt(properties.getProperty("FEEDBACK_API_PORT", "8085"));
    RetrofitService retrofitService = new RetrofitService(feedbackApiPort);
    FeedbackApi feedbackApi = retrofitService.getRetrofit().create(FeedbackApi.class);

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
        binding.feedbackRating.setRating(items.get(position).getRating());
        String[] datetime = items.get(position).getDate().split("T");
        String date = datetime[0];
        String time = datetime[1];
        binding.feedbackDate.setText(date);
        binding.feedbackOptionsBtn.setOnClickListener(
                v -> showPopupMenu(v, position)
        );
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

    private void showPopupMenu(View view, int position) {
        // Create a PopupMenu
        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
        // Inflate the menu from xml resource
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.feedback_options_menu, popupMenu.getMenu());
        // Set a listener for menu item clicks
        popupMenu.setOnMenuItemClickListener(
            item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.feedback_action_edit) {// Handle edit feedback action
                    Toast.makeText(view.getContext(), "Edit clicked for item " + position, Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemId == R.id.feedback_action_delete) {// Handle delete feedback action
                    deleteFeedback(items.get(position).getId(), position);
                    return true;
                }
                return false;
            }
        );
        // Show the popup menu
        popupMenu.show();
    }

    private void updateFeedback(Integer feedbackId, FeedbackRequest request, int position) {
        feedbackApi.updateFeedback(feedbackId, request).enqueue(
            new Callback<Feedback>() {
                @Override
                public void onResponse(Call<Feedback> call, Response<Feedback> response) {
                    Feedback feedback = response.body();
                    items.get(position).setComment(feedback.getComment());
                    items.get(position).setRating(feedback.getRating());
                    items.get(position).setDate(feedback.getDate());
                }

                @Override
                public void onFailure(Call<Feedback> call, Throwable t) {
                    Toast.makeText(context.getApplicationContext(), "Failed to update feedback!", Toast.LENGTH_SHORT).show();
                    Logger.getLogger(FeedbacksActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                }
            }
        );
    }

    private void deleteFeedback(Integer feedbackId, int position) {
        feedbackApi.deleteFeedback(feedbackId).enqueue(
                new Callback<Map<String, String>>() {
                    @Override
                    public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                        Map<String, String> res = response.body();
                        items.remove(position);
                        Toast.makeText(context.getApplicationContext(), "Feedback deleted successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Map<String, String>> call, Throwable t) {
                        Toast.makeText(context.getApplicationContext(), "Failed to delete feedback!", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(FeedbacksActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                    }
                }
        );
    }
}
