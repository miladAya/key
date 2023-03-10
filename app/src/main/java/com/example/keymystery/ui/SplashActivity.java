package com.example.keymystery.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.keymystery.R;
import com.example.keymystery.databinding.ActivitySettingsBinding;
import com.example.keymystery.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.img.setAnimation(
                AnimationUtils.loadAnimation(getBaseContext(), R.anim.animation));
new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();

            }
        },2000);
    }
}