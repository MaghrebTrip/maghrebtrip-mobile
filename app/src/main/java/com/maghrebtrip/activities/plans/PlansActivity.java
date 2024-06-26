package com.maghrebtrip.activities.plans;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.maghrebtrip.R;
import com.maghrebtrip.activities.ProfileActivity;
import com.maghrebtrip.activities.details.RestaurantDetailsActivity;
import com.maghrebtrip.activities.main.MainActivity;
import com.maghrebtrip.adapters.VPAdapter;
import com.maghrebtrip.databinding.ActivityPlansBinding;

public class PlansActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    ActivityPlansBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlansBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        statusBarColor();
        bottomAppBar();
        getBundles();
    }

    private void getBundles() {
        tabLayout = findViewById(R.id.plansTabLayout);
        viewPager = findViewById(R.id.plansViewPager);

        tabLayout.setupWithViewPager(viewPager);

        int cityId = 0;
        if (getIntent().hasExtra("cityId")) {
            cityId = getIntent().getIntExtra("cityId", 0);
        } else {
            Toast.makeText(PlansActivity.this, "City ID not found", Toast.LENGTH_SHORT).show();
        }

        Fragment firstPlanFragment = new FirstPlanFragment();
        Fragment secondPlanFragment = new SecondPlanFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("cityId", cityId);

        firstPlanFragment.setArguments(bundle);
        secondPlanFragment.setArguments(bundle);

        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(firstPlanFragment, "First Plan");
        vpAdapter.addFragment(secondPlanFragment, "Second Plan");

        viewPager.setAdapter(vpAdapter);

        View backBtn = findViewById(R.id.appBarOptionsBackBtn);
        backBtn.setOnClickListener(v -> finish());
    }

    private void statusBarColor() {
        Window window = PlansActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(PlansActivity.this, R.color.cornel_red));
    }

    private void bottomAppBar() {
        View bottomAppBarHomeBtn = findViewById(R.id.bottomAppBarHomeBtn);
        View bottomAppBarExplorerBtn = findViewById(R.id.bottomAppBarExplorerBtn);
        View bottomAppBarProfileBtn = findViewById(R.id.bottomAppBarProfileBtn);

        bottomAppBarHomeBtn.setOnClickListener(
                v -> {
                    Intent intent = new Intent(PlansActivity.this, MainActivity.class);
                    startActivity(intent);
                }
        );
        bottomAppBarExplorerBtn.setOnClickListener(
                v -> {
                    Intent intent = new Intent(PlansActivity.this, MainActivity.class);
                    startActivity(intent);
                }
        );
        bottomAppBarProfileBtn.setOnClickListener(
                v -> {
                    Intent intent = new Intent(PlansActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
        );
    }
}
