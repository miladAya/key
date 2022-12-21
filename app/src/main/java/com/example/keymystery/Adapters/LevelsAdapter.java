package com.example.keymystery.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.keymystery.database.User;
import com.example.keymystery.databinding.CustomLevelsItemBinding;
import com.example.keymystery.model.Levels;

import java.util.ArrayList;

public class LevelsAdapter extends RecyclerView.Adapter<LevelsAdapter.LevelsHolder> {
    ArrayList<Levels>levels;

    public LevelsAdapter(ArrayList<Levels> levels) {
        this.levels = levels;
    }

    @NonNull
    @Override
    public LevelsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomLevelsItemBinding binding=CustomLevelsItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new LevelsHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LevelsHolder holder, int position) {
        int poss =position;
        Levels level=levels.get(poss);
holder.levelsNum.setText(String.valueOf(level.getNumberLevels()));
holder.requiredPoints.setText(String.valueOf(level.getRequiredPoints()));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class LevelsHolder extends RecyclerView.ViewHolder {

        TextView levelsNum,requiredPoints;
        RatingBar score;
        public LevelsHolder(CustomLevelsItemBinding binding) {
            super(binding.getRoot());
            levelsNum=binding.levelNum;
            requiredPoints=binding.levelPoint;
            score=binding.RatingBar;
        }
    }

}
