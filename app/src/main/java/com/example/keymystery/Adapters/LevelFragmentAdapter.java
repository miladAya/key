package com.example.keymystery.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class LevelFragmentAdapter extends FragmentStateAdapter {
    ArrayList<Fragment>level;

    public LevelFragmentAdapter(@NonNull FragmentActivity fragmentActivity , ArrayList<Fragment>level) {
        super(fragmentActivity);
        this.level=level;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return level.get(position);
    }

    @Override
    public int getItemCount() {
        return level.size();
    }
}
