package com.example.keymystery.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.keymystery.Adapters.LevelsAdapter;
import com.example.keymystery.databinding.ActivityLevelsBinding;
import com.example.keymystery.model.Levels;

import java.util.ArrayList;

public class LevelsActivity extends AppCompatActivity {
ActivityLevelsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLevelsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayList<Levels>levels=new ArrayList<>();
        LevelsAdapter levelsAdapter=new LevelsAdapter(levels);
        binding.rv.setAdapter(levelsAdapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(LevelsActivity.this, RecyclerView.HORIZONTAL,false));
    }
}