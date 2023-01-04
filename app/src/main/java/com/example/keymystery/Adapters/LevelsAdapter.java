package com.example.keymystery.Adapters;


import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.keymystery.database.Levels;
import com.example.keymystery.databinding.CustomLevelsItemBinding;
import com.example.keymystery.model.OnClick;

import java.util.ArrayList;

public class LevelsAdapter extends RecyclerView.Adapter<LevelsAdapter.LevelHolder> {
    ArrayList<Levels>levels;
    OnClick onClick;


    public LevelsAdapter(ArrayList<Levels> levels, OnClick onClick) {
        this.levels = levels;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public LevelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomLevelsItemBinding binding=CustomLevelsItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new LevelHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LevelHolder holder, int position) {

        int poss =position;
         Levels level=levels.get(poss);
        holder.levelNum.setText(String.valueOf(level.getLevel_no()));
        holder.levelPoint.setText(String.valueOf(level.getUnlock_points()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onClick(poss);
            }
        });





    }


    @Override
    public int getItemCount() {
        return levels.size();
    }

    class LevelHolder extends RecyclerView.ViewHolder {
        TextView levelNum,levelPoint ,score ;
        ImageView openLevels;
        public LevelHolder(CustomLevelsItemBinding binding) {
            super(binding.getRoot());
            levelNum=binding.levelNum;
            levelPoint=binding.levelPoint;
            score=binding.score;
          openLevels= binding.openIcon;
        }
    }


}
