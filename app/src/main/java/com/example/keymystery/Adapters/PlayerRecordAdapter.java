package com.example.keymystery.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.keymystery.database.Levels;
import com.example.keymystery.database.Question;
import com.example.keymystery.databinding.CustomPlayerRecordBinding;
import com.example.keymystery.model.OnClick;

import java.util.ArrayList;
import java.util.List;

public class PlayerRecordAdapter extends RecyclerView.Adapter<PlayerRecordAdapter.PlayerHolder> {
  List<Levels> record;
  OnClick onClick;

    public PlayerRecordAdapter(List<Levels> record,OnClick onClick) {
        this.record = record;
        this.onClick=onClick;
    }

    @NonNull
    @Override
    public PlayerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomPlayerRecordBinding binding=CustomPlayerRecordBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PlayerHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerHolder holder, int position) {
        int poss =position;
        Levels levels=record.get(poss);
            holder.levelNum.setText( "LEVEL " + String.valueOf(levels.getLevel_no()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClick.onClick(poss);
                }
            });
    }

    @Override
    public int getItemCount() {
        return record.size();
    }

    class  PlayerHolder extends RecyclerView.ViewHolder {
        TextView levelNum;
        public PlayerHolder(CustomPlayerRecordBinding binding) {
            super(binding.getRoot());
            levelNum=binding.levelNum;
        }
    }
}
