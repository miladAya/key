package com.example.keymystery.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;

import com.example.keymystery.Adapters.LevelFragmentAdapter;
import com.example.keymystery.database.Level;
import com.example.keymystery.database.ViewModel;
import com.example.keymystery.databinding.ActivityLevelBinding;
import com.example.keymystery.model.AppUtility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LevelActivity extends AppCompatActivity {
ActivityLevelBinding binding;
ArrayList<Level>arrayLevel;
ViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLevelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        ArrayList<Button> tb=new ArrayList<>();
        ArrayList<Fragment> fragments=new ArrayList<>();
        fragments.add(new ChoseFragment());
        fragments.add(new TorF_Fragment());
        fragments.add(new CompleteFragment());
LevelFragmentAdapter levelFragmentAdapter=new LevelFragmentAdapter(this,fragments);
        binding.VP.setAdapter(levelFragmentAdapter);    }




//        new TabLayoutMediator(binding.TL, binding.VP, new TabLayoutMediator.TabConfigurationStrategy() {
//            @Override
//            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
////                tab.set(tb.get(position));
//            }
//        }).attach();

    }




