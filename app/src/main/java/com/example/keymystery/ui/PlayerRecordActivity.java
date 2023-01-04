package com.example.keymystery.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;

import com.example.keymystery.Adapters.LevelsAdapter;
import com.example.keymystery.Adapters.PlayerRecordAdapter;
import com.example.keymystery.R;
import com.example.keymystery.database.Levels;
import com.example.keymystery.database.Question;
import com.example.keymystery.database.ViewModel;
import com.example.keymystery.databinding.ActivityPlayerRecordBinding;
import com.example.keymystery.model.OnClick;

import java.util.ArrayList;
import java.util.List;

public class PlayerRecordActivity extends AppCompatActivity implements OnClick {
ActivityPlayerRecordBinding binding;
ViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPlayerRecordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        viewModel=new ViewModelProvider(PlayerRecordActivity.this).get(ViewModel.class);
        viewModel.getAllLevelsData().observe(this, new Observer<List<Levels>>() {
            @Override
            public void onChanged(List<Levels> levels) {
                PlayerRecordAdapter adapter=new PlayerRecordAdapter(levels,PlayerRecordActivity.this::onClick);
                binding.rv.setAdapter(adapter);
                binding.rv.setLayoutManager(new LinearLayoutManager(PlayerRecordActivity.this));
            }
        });







    }

    @Override
    public void onClick(int position) {
        startActivity(new Intent(PlayerRecordActivity.this,RecordActivity.class));
    }
}