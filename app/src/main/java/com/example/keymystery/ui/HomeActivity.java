package com.example.keymystery.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.keymystery.R;
import com.example.keymystery.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHomeBinding.inflate( getLayoutInflater());
        setContentView(binding.getRoot());
        binding.startPlayingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.startPlayingBtn.setBackgroundResource(R.drawable.background);
                startActivity(new Intent(HomeActivity.this,LevelsActivity.class));
            }
        });
        binding.settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.settingsBtn.setBackgroundResource(R.drawable.background);
                startActivity(new Intent(HomeActivity.this,SettingsActivity.class));

            }
        });
        binding.exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.exitBtn.setBackgroundResource(R.drawable.background);
                finish();
            }
        });
    }
}