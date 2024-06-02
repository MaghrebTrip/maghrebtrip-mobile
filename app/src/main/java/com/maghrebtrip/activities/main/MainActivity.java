package com.maghrebtrip.activities.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.maghrebtrip.R;
import com.maghrebtrip.activities.ProfileActivity;
import com.maghrebtrip.adapters.VPAdapter;
import com.maghrebtrip.databinding.ActivityMainBinding;
import com.maghrebtrip.models.Tourist;
import com.maghrebtrip.retrofit.RetrofitService;
import com.maghrebtrip.retrofit.TouristApi;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    String userEmail;

    Properties properties = new Properties();
    int touristApiPort = Integer.parseInt(properties.getProperty("TOURIST_API_PORT", "8080"));
    RetrofitService retrofitService = new RetrofitService(touristApiPort);
    TouristApi touristApi = retrofitService.getRetrofit().create(TouristApi.class);

    private TabLayout tabLayout;
    private ViewPager viewPager;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get the user email from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("appUser", MODE_PRIVATE);
        userEmail = sharedPreferences.getString("email", "");

        statusBarColor();
        bottomAppBar();
        getTourist();
        getBundles();
    }

    private void statusBarColor() {
        Window window = MainActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.cornel_red));
    }

    private void bottomAppBar() {
        View bottomAppBarHomeBtn = findViewById(R.id.bottomAppBarHomeBtn);
        View bottomAppBarExplorerBtn = findViewById(R.id.bottomAppBarExplorerBtn);
        View bottomAppBarProfileBtn = findViewById(R.id.bottomAppBarProfileBtn);

        bottomAppBarHomeBtn.setOnClickListener(
                v -> {
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                }
        );
        bottomAppBarExplorerBtn.setOnClickListener(
                v -> {
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                }
        );
        bottomAppBarProfileBtn.setOnClickListener(
                v -> {
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
        );
    }

    private void getTourist() {
        if (!userEmail.equals("")) {
            touristApi.getTouristByEmail(userEmail).enqueue(
                new Callback<Tourist>() {
                    @Override
                    public void onResponse(Call<Tourist> call, Response<Tourist> response) {
                        Tourist tourist = response.body();
                        if (tourist != null) {
                            // Store the tourist info
                            SharedPreferences sharedPreferences = getSharedPreferences("appUser", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("id", tourist.getId());
                            editor.putString("firstName", tourist.getFirstName());
                            editor.putString("lastName", tourist.getLastName());
                            editor.putString("nationality", tourist.getNationality());
                            editor.apply();

                            // Set the username
                            TextView textView = findViewById(R.id.userName);
                            textView.setText(String.format("Hey %s,", tourist.getFirstName()));
                        } else {
                            Toast.makeText(MainActivity.this, "Empty data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Tourist> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Failed to load tourist!", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                    }
                }
            );
        } else {
            Toast.makeText(MainActivity.this, "User not found!", Toast.LENGTH_SHORT).show();
        }
    }

    private void getBundles() {
        tabLayout = findViewById(R.id.exploreTabLayout);
        viewPager = findViewById(R.id.exploreViewPager);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();

    }

    private void setupViewPager(ViewPager viewPager) {
        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new ExploreCitiesFragment(), "Cities");
        vpAdapter.addFragment(new ExploreHotelsFragment(), "Hotels");
        vpAdapter.addFragment(new ExploreRestaurantsFragment(), "Restaurants");
        vpAdapter.addFragment(new ExploreMonumentsFragment(), "Monuments");
        viewPager.setAdapter(vpAdapter);
    }

    private void setupTabIcons() {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(createTabView(tab.getText().toString()));
            }
        }
    }

    private View createTabView(String title) {
        View view = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        TextView textView = view.findViewById(R.id.tab_text);
        ImageView imageView = view.findViewById(R.id.tab_icon);

        textView.setText(title);

        switch (title) {
            case "Cities":
                imageView.setImageResource(R.drawable.city_white_24dp);
                break;
            case "Hotels":
                imageView.setImageResource(R.drawable.baseline_bed_24);
                break;
            case "Restaurants":
                imageView.setImageResource(R.drawable.restaurant_white_24dp);
                break;
            case "Monuments":
                imageView.setImageResource(R.drawable.monument_white_24dp);
                break;
        }

        return view;
    }

}