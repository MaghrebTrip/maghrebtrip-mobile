package com.maghrebtrip.activities;

import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.maghrebtrip.PlanFragment1;
import com.maghrebtrip.PlanFragment2;
import com.maghrebtrip.R;
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
        getBundles();
    }

    private void getBundles() {
        tabLayout = findViewById(R.id.plansTabLayout);
        viewPager = findViewById(R.id.plansViewPager);

        tabLayout.setupWithViewPager(viewPager);

        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new PlanFragment1(), "First Plan");
        vpAdapter.addFragment(new PlanFragment2(), "Second Plan");

        viewPager.setAdapter(vpAdapter);
    }

    private void statusBarColor() {
        Window window = PlansActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(PlansActivity.this, R.color.cornel_red));
    }
}
