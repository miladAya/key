package com.example.keymystery.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.keymystery.Adapters.LevelsAdapter;
import com.example.keymystery.database.Levels;
import com.example.keymystery.database.Question;
import com.example.keymystery.database.ViewModel;
import com.example.keymystery.databinding.ActivityLevelsBinding;
import com.example.keymystery.model.OnClick;

import java.util.ArrayList;
import java.util.List;

public class LevelsActivity extends AppCompatActivity implements OnClick {
ActivityLevelsBinding binding;
    ViewModel viewModel;
    LevelsAdapter levelsAdapter;
    Levels levels1;
    Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLevelsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        viewModel=new ViewModelProvider(LevelsActivity.this).get(ViewModel.class);
        viewModel.getAllLevelsData().observe(this, new Observer<List<Levels>>() {
            @Override
            public void onChanged(List<Levels> levels) {
                for (int i = 0; i < levels.size(); i++) {
                     levels1=levels.get(i);
                    ArrayList<Levels> levels1=new ArrayList<>(levels);
                    levelsAdapter=new LevelsAdapter(levels1,LevelsActivity.this::onClick );
                    binding.rv.setAdapter(levelsAdapter);
                    binding.rv.setLayoutManager(new LinearLayoutManager(LevelsActivity.this, RecyclerView.HORIZONTAL,false));
                }

            }
        });

    }



    @Override
    public void onClick(int position) {

        int poss =position+1;
        Intent intent=  new Intent(LevelsActivity.this, LevelActivity.class);
        intent.putExtra("levelsNum",poss);
        startActivity(intent);



    }
}