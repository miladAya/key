//package com.example.keymystery.Adapters;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.RatingBar;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.keymystery.database.Levels;
//import com.example.keymystery.databinding.CustomLevelsItemBinding;
//import com.example.keymystery.model.OnClick;
//
//import java.util.ArrayList;
//
//public class LevelsAdapter extends RecyclerView.Adapter<LevelsAdapter.LevelsHolder> {
//    ArrayList<Levels>levels;
//    OnClick onClick;
//
//    public LevelsAdapter(ArrayList<Levels> levels,OnClick onClick) {
//        this.levels = levels;
//        this.onClick=onClick;
//    }
//
//    @NonNull
//    @Override
//    public LevelsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        CustomLevelsItemBinding binding=CustomLevelsItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
//        return new LevelsHolder(binding);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull LevelsHolder holder, int position) {
//        int poss =position;
//        Levels level=levels.get(poss);
//        holder.levelsNum.setText(String.valueOf(level.getLevel_no()));
//        holder.requiredPoints.setText(String.valueOf(level.getUnlockPoints()));
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onClick.onClick(poss);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return levels.size();
//    }
//
//    class LevelsHolder extends RecyclerView.ViewHolder {
//
//        TextView levelsNum,requiredPoints;
//        RatingBar score;
//        public LevelsHolder(CustomLevelsItemBinding binding) {
//            super(binding.getRoot());
//            levelsNum=binding.levelNum;
//            requiredPoints=binding.levelPoint;
//            score=binding.RatingBar;
//        }
//    }
//
//}
