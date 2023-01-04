package com.example.keymystery.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.keymystery.database.User;
import com.example.keymystery.database.ViewModel;
import com.example.keymystery.databinding.ActivityRecordBinding;

import java.util.List;

public class RecordActivity extends AppCompatActivity {
ActivityRecordBinding binding;
ViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRecordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        viewModel=new ViewModelProvider(RecordActivity.this).get(ViewModel.class);
        viewModel.getAllUsersData().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                for (int i = 0; i < users.size(); i++) {
                    User user=users.get(i);
                    binding.playerName.setText(user.getUserName());
                    binding.playerEmail.setText(user.getEmail());

                }
            }
        });

    }

    }
