package com.innovm.gadsleaderboard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.innovm.gadsleaderboard.activity.LandingPage;
import com.innovm.gadsleaderboard.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        nextPage();
    }

    public void nextPage() {
        new Handler().postDelayed(() -> {
            //Navigate to next page.
            startActivity(new Intent(MainActivity.this, LandingPage.class));
            finish();
        }, 500);
    }
}