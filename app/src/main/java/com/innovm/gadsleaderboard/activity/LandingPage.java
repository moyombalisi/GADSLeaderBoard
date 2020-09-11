package com.innovm.gadsleaderboard.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.tabs.TabLayoutMediator;
import com.innovm.gadsleaderboard.R;
import com.innovm.gadsleaderboard.databinding.ActivityHomepageBinding;
import com.innovm.gadsleaderboard.fragment.LearningLeadersFragment;
import com.innovm.gadsleaderboard.fragment.SkillLeadersFragment;

public class LandingPage extends AppCompatActivity {
    ActivityHomepageBinding binding;
    //Tab Titles
    private String[] titles = new String[]{"Learning Leaders", "Skill IQ Leaders"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_homepage);

        initTabs();

        binding.btnSubmit.setOnClickListener(view -> startActivity(new Intent(LandingPage.this, SubmitActivity.class)));
    }

    public void initTabs() {
        binding.viewPager.setAdapter(new ViewPagerFragmentAdapter(this));

        //Attach Tab Mediator
        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) -> {
            tab.setText(titles[position]);
        }).attach();
    }


    private class ViewPagerFragmentAdapter extends FragmentStateAdapter {

        public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new LearningLeadersFragment();
                case 1:
                    return new SkillLeadersFragment();
            }
            return new LearningLeadersFragment();
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }
    }
}