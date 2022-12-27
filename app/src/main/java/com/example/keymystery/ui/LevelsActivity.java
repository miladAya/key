package com.example.keymystery.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

//import com.example.keymystery.Adapters.LevelsAdapter;
import com.example.keymystery.database.ViewModel;
import com.example.keymystery.databinding.ActivityLevelsBinding;
import com.example.keymystery.model.OnClick;

public class LevelsActivity extends AppCompatActivity implements OnClick {
ActivityLevelsBinding binding;
    ViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLevelsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel=new ViewModelProvider(LevelsActivity.this).get(ViewModel.class);




    }









    @Override
    public void onClick(int position) {
        startActivity(new Intent(LevelsActivity.this, LevelActivity.class));

    }
}